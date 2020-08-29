var curOrderId;
var orderState;
layui.config({
	base : "../../js/"
}).use(['flow','form','layer','upload','table'],function(){
    var flow = layui.flow,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        table = layui.table,
        $ = layui.jquery;

    //流加载订单表
    var orderNums = 15;  //单页显示订单的数量
    flow.load({
        elem: '#Orders', //流加载容器
        done: function(page, next){ //加载下一页
            $.get("/WareHouseManagement/order/findAllOrders",function(data){
                //模拟插入
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(data.length));
                console.log(JSON.stringify(data[0].content));
                var orderList = [];
                var maxPage = orderNums*page < data.length ? orderNums*page : data.length;
                setTimeout(function(){

                    var isPick=0;//0-->未开始，1-->未完成，2-->已经完成
                    var state0='<a class="layui-btn layui-btn-primary layui-btn-sm" aria-disabled="false">未开始</a>';
                    var state1='<a class="layui-btn layui-btn-sm layui-btn-danger batchDel" aria-disabled="false">未完成</a>';
                    var state2='<a class="layui-btn layui-btn-sm" aria-disabled="false">已完成</a>';


                    for(var i=orderNums*(page-1); i<maxPage; i++){
                        var state;
                        if(data[i].state==0)state=state0;
                        if(data[i].state==1)state=state1;
                        if(data[i].state==2)state=state2;


                        //创建一个table
                        orderList.push('<li class="layui-card" style="width: 49%">' +
                                            '<div class="layui-card-header">' +
                                                '<a style="float: left"> ' + '订单' + data[i].name +'</a>' +
                                                '<div style="float: right">' +
                                                    '<button  id="state'+ data[i].id +'"  class="layui-input" disabled="disabled" style="border:none">' +
                                                        state +
                                                    '</button>' +
                                                '</div>' +
                                            '</div>' +
                                            '<div style="padding:5px" class="layui-card-body">' +
                                                '<table class="layui-table" id="order'+ data[i].id +'"></table>' +
                                                '<hr>' +
                                                '<div class="layui-inline layui-checkbox-disbaled"> ' +
                                                    '<a id="btn'+ data[i].id +'" class="layui-btn layui-btn-sm" style="float: right" >开始拣货</a>' +
                                                '</div>' +
                                            '</div>' +
                                        '</li>');

                        console.log("#order"+i);
                        console.log("order"+i+"table");
                        (function (i) {
                            console.log(JSON.stringify(data[i].items));
                            var orderElem='#order'+data[i].id;
                            var orderId="order"+data[i].id+"table";
                            setTimeout(function(){

                                console.log(JSON.stringify(data[i].items));
                                table.render({
                                    elem: orderElem,
                                    data: data[i].items,
                                    id: orderId,
                                    cols: [[
                                        {field: 'clothing', title: '服装ID', align: "center"},
                                        {field: 'quantity', title: '数量', align: "center"},

                                        /*{title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}*/
                                    ]]
                                });

                                $("#btn"+data[i].id).on("click",function () {

                                    curOrderId=data[i].id;

                                    layer.open({
                                        title:"拣货中...",
                                        type: 2,
                                        area: ['700px', '400px'],
                                        fixed: false, //不固定
                                        maxmin: true,
                                        content: 'page/order/order_sort.html',
                                        cancel:function (index, layero) {
                                            console.log("刷新订单状态");
                                            var body = window.parent;
                                            var iframe=body.document.getElementsByTagName("iframe")[1];
                                            iframe.contentWindow.location.reload(true);
                                        }
                                    });
                                })
                                }
                                ,100);
                        })(i);


                    }

                    next(orderList.join(''), page < (data.length/orderNums));
                    //form.render();
                }, 0);
            });
        }
    });

    //设置图片的高度
    $(window).resize(function(){
        console.log($("#Orders li img").width());
       $("#Orders li img").height($("#Orders li img").width());
        console.log($("#Orders li img").width());
    })

})