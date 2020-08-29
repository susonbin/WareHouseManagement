var order;
var items;
var i=0;

layui.use(['form','layer','laydate','upload'],function(){
    var form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        $ = layui.jquery;

    var id=parent.window.window[1].curOrderId;
    var curcur=parent.window.window[1].curcur;
    console.log(curcur);

    var worker;

    console.log(parent.window.window[2]);
    console.log(this.title);
    this.cancel=function(){
        console.log("来自子窗口的退出");
    };

    setTimeout(function(){
        $.ajax({
            url: "/WareHouseManagement/order/findOrderById",
            contentType: "application/json;charset=UTF-8",
            data: '{"id":"' + id + '"}',
            dataType: "json",
            type: "post",
            success: function (data) {
                order=data;
                console.log(data);
                items=order.items;

                worker = new Worker('return_working.js');
                worker.postMessage([items,i]);
                worker.onmessage=function (e){
                    i=e.data[3];
                    console.log("我来自子线程"+i+"-----"+items);

                    //更新后台订单
                    console.log("更新后台订单")
                    $.ajax({
                        url:"/WareHouseManagement/order/updateOrder",//注意需要修改数据库的json
                        type:"post",
                        data:JSON.stringify(order),
                        contentType:"application/json;charset=UTF-8",
                        dataType:"json",
                    });

                    console.log(e.data);
                    items=e.data[2];
                    $('#cid').val((e.data)[0]);
                    $('#qua').val((e.data)[1]);
                    $('#shelf').val("");
                    $('#slot').val("");
                    $('#confirm_input').val("");

                };
            }
        });
    },0);

    $('#confirm').on("click",function () {
        console.log("ccc");
        if($('#confirm_input').val()==$('#cid').val()){
            if(i<items.length){

                console.log(items[i].clothing+"入库");
                var qua=items[i].quantity;
                var shelf = $('#shelf').val();
                var slot = $('#slot').val();
                console.log(shelf+slot);
                console.log(items[i]);
                console.log(items[i].isPick);
                items[i].isPick=true;
                order.items[i].isPick=true;
                console.log(items[i].isPick);

                //控制后台入库

                if($('#qua').val()=="该退货订单的该产品已经入库"){
                    layer.msg("处理过的不用再处理");
                    return;
                }
                else {
                    console.log("找一下");
                    console.log(items[i].clothing);
                    var error=false;
                    $.ajax({
                        url:"/WareHouseManagement/stock/findClothingByBarcode",
                        contentType:"application/json;charset=UTF-8",
                        data:'{"barcode":"'+ items[i].clothing +'"}',
                        dataType:"json",
                        type:"post",
                        success:function (data) {

                            console.log(data);
                            var shelf=data.slot.shelf.name;
                            var slot=data.slot.name;
                            layer.open({
                                title: '库存存在'
                                , content: '请直接将该产品放到' + shelf + "-" + slot
                            });
                            data.quantity += Number(qua);
                            console.log("更新库存数量");
                            //更新库存数量
                            $.ajax({
                                url: "/WareHouseManagement/stock/updateClothing",
                                type: "post",
                                data: JSON.stringify(data),
                                contentType: "application/json;charset=UTF-8",
                                dataType: "json",
                            });

                        },
                        error:function () {
                            console.log("没找到");

                            var clothing = {
                                barcode: items[i].clothing,
                                slotId: "",
                                quantity: qua,
                            };


                            console.log(shelf+" "+slot);

                            $.ajax({
                                url: "/WareHouseManagement/stock/findSlotIdByShelfAndSlot",
                                contentType: "application/json;charset=UTF-8",
                                data: '{"shelf":"' + shelf + '","slot":"' + slot + '"}',
                                dataType: "json",
                                type: "post",
                                success: function (data) {
                                    clothing.slotId = data;
                                    console.log(clothing);
                                    $.ajax({
                                        url: "/WareHouseManagement/stock/addClothing",
                                        contentType: "application/json;charset=UTF-8",
                                        data: JSON.stringify(clothing),
                                        dataType: "json",
                                        type: "post",
                                    });
                                },
                                error:function () {
                                    console.log("没找到");
                                    layer.msg("货位不存在/出错");
                                    error=true;
                                }
                            });
                        }
                    })
                    if(error){
                        return;
                    }

                }

                setTimeout(function() {
                    layer.msg("入库成功");
                    console.log("进去了");
                    worker.postMessage([items, i + 1]);
                },0);
            }
            else {
                layer.msg("没有更多，去看看别的订单吧");
            }

        }
        else layer.msg("入库失败，请输入正确的产品条形码");
    });

    $('#next').on("click",function () {
        console.log("下一件");
        if(i<items.length){
            console.log("下一件");
            worker.postMessage([items,i+1]);
        }
        else {
            layer.msg("没有更多，去看看别的订单吧");
        }
    });

})