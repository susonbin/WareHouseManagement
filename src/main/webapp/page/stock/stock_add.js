layui.use(['form','layer','laydate','upload'],function(){
    var form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        $ = layui.jquery;

    $('#confirm').on("click",function () {
        console.log("入库成功");
        window.alert("入库成功");
        var clothing={
            barcode:"",
            slotId:"",
            quantity:"",
        };

        clothing.quantity=$('#qua').val();
        clothing.barcode=$('#cid').val();
        var shelf=$('#shelf').val();
        var slot=$('#slot').val();

        var exist=false;
        $.ajax({
            url: "/WareHouseManagement/stock/findClothingByShelfAndSlot",
            contentType: "application/json;charset=UTF-8",
            data: '{"shelf":"' + shelf + '","slot":"' + slot + '"}',
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data != null) {
                    layer.msg("该货位已有服装");
                    exist=true;
                }
            }
        });
        if(exist)return ;

        $.ajax({
            url: "/WareHouseManagement/stock/findSlotIdByShelfAndSlot",
            contentType: "application/json;charset=UTF-8",
            data: '{"shelf":"' + shelf + '","slot":"' + slot + '"}',
            dataType: "json",
            type: "post",
            success: function (data) {
                clothing.slotId=data;
                console.log(clothing);
                $.ajax({
                    url: "/WareHouseManagement/stock/addClothing",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(clothing),
                    dataType: "json",
                    type: "post",
                });
            }
        });


    });
})