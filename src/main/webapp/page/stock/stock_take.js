var isStart=false;
var curClothingBarcode;
var realQua;
var items=new Array();
layui.use(['form','layer','laydate','table','laytpl'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    console.log(layer);

    //已经盘点的列表
    table.render({
        elem: '#takeList',
        data: items,
        height: "full-125",
        id: "takeListTable",
        title:"仓库盘点报表",
        cols: [[
            {field: 'shelf', title: '货架', align: "center"},
            {field: 'slot', title: '货位', align: "center"},
            {field: 'barcode', title: '服装ID',width: '30%', align:'center'},
            {field: 'qua', title: '账面数量', align:'center'},
            {field: 'realQua', title: '盘点数量', align:'center'},
            {field: 'earn', title: '盘盈', align:'center'},
            {field: 'lose', title: '盘亏', align:'center'},
            //{title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}
        ]]

    });

    //搜索点击事件
    $('#search').on("click", function () {
        if(isStart==false){
            layer.msg("请先点击开始盘点，盘点结果会记录到下面的表格中");
            return;
        }
        var shelf=$('#shelf').val();
        var slot=$('#slot').val();
        if(shelf==""||slot==""||shelf==null||slot==null||isNaN(slot)||shelf.length>1||shelf.charAt(0)<65||shelf.charAt(0)>90){
            layer.msg("请输入正确的货位");
            return;
        }
        $.ajax({
            url: "/WareHouseManagement/stock/findClothingByShelfAndSlot",
            contentType: "application/json;charset=UTF-8",
            data: '{"shelf":"' + shelf + '","slot":"' + slot + '"}',
            dataType: "json",
            type: "post",
            success:function (data) {
                console.log(data);
                if(data==null){
                    layer.msg("该货位没有服装");
                    return;
                }
                curClothingBarcode=data.barcode;
                layer.open({
                    title:"盘点"+ shelf+ "-" + slot +"中...",
                    type: 2,
                    area: ['700px', '400px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: 'page/stock/taking.html',
                    end:function () {

                        console.log("添加一行");
                        console.log(realQua);

                        var item = {
                            shelf:shelf,
                            slot:slot,
                            barcode:curClothingBarcode,
                            qua:data.quantity,
                            realQua:realQua,
                            earn:realQua>data.quantity?realQua-data.quantity:0,
                            lose:realQua>data.quantity?0:data.quantity-realQua,
                        };

                        items.push(item);
                        console.log(items);
                        table.reload('takeListTable', {
                            data: items
                        });

                        if(realQua==0){
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
                            data.quantity=realQua;
                            $.ajax({
                                url:"/WareHouseManagement/stock/updateClothing",
                                type:"post",
                                data: JSON.stringify(data),
                                contentType:"application/json;charset=UTF-8",
                                dataType: "json",
                            });
                        }
                    }
                });
            },
            error:function () {
                layer.msg("该货位没有服装");
            }
        });

    });

    $('#output').on("click",function () {
        console.log("打印");
        if (isStart==false){
            layer.msg("没有盘点记录，请先点击开始盘点，盘点结果会记录到下面的表格中");
            return;
        }
        if (items.length==0){
            layer.msg("没有盘点记录,前添加盘点的货位");
            return;
        }
        table.exportFile('takeListTable', items);
    })

    //是否开始盘点
    $('#start').on("click", function () {
        if (isStart==false) {
            layer.confirm('通过左边搜索要盘点的货位，记录好盘点数量，就可以生成报表', {
                time: 20000, //20s后自动关闭
                btn: ['明白了']
            });
            isStart=true;

        } else {
            layer.msg("已经开始盘点，在左边搜索盘点的货位");
        }
    });
})