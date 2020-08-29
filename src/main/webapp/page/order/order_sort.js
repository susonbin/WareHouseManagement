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

    var worker;

    console.log(parent.window.window[1]);
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

                worker = new Worker('working.js');
                worker.postMessage([items,i]);
                worker.onmessage=function (e){
                    i=e.data[4];
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
                    items=e.data[3];
                    $('#cid').val((e.data)[0]);
                    $('#qua').val((e.data)[1]);
                    $('#pos').val((e.data)[2]);
                    $('#confirm_input').val("");

                };
            }
        });
    },0);

    $('#confirm').on("click",function () {
        console.log("ccc");
        if($('#confirm_input').val()==$('#cid').val()){
            if(i<items.length){

                console.log(items[i].clothing+"出仓");
                var qua=items[i].quantity;
                console.log(items[i]);
                console.log(items[i].isPick);
                items[i].isPick=true;
                order.items[i].isPick=true;
                console.log(items[i].isPick);

                //控制后台出仓

                if($('#pos').val()=="该服装产品仓库无货"||$('qua').val()=="该服装产品库存数量不足"){
                    layer.msg("该产品库存状态异常，可能是没货或者库存不足，快找老板进货");
                    return;
                }
                else if($('#pos').val()=="该订单已经拣选过该产品"){
                    layer.msg("已经说了该产品拣选过了，赃货小心被开除哦");
                    return;
                }
                else{

                    $.ajax({
                        url:"/WareHouseManagement/stock/findClothingByBarcode",
                        contentType:"application/json;charset=UTF-8",
                        data:'{"barcode":"'+ items[i].clothing +'"}',
                        dataType:"json",
                        type:"post",
                        success:function (data) {
                            console.log(data);
                            console.log(qua);

                            data.quantity-=qua;
                            if(data.quantity==0){
                                console.log("删除");
                                //库存为零删除
                                $.ajax({
                                    url:"/WareHouseManagement/stock/removeClothingById",
                                    type:"get",
                                    data:"id="+data.id,
                                });
                            }
                            else{
                                console.log("更新库存数量");
                                //更新库存数量
                                $.ajax({
                                    url:"/WareHouseManagement/stock/updateClothing",
                                    type:"post",
                                    data: JSON.stringify(data),
                                    contentType:"application/json;charset=UTF-8",
                                    dataType: "json",
                                });
                            }

                        }
                    })
                }

                layer.msg("拣货成功，系统已将其出仓");
                worker.postMessage([items,i+1]);
            }
            else {
                layer.msg("没有更多，去看看别的订单吧");
            }

        }
        else layer.msg("拣货失败，请输入正确的产品条形码");
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