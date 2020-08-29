layui.use(['form','layer','laydate','upload'],function(){
    var form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        $ = layui.jquery;

    var barcode=parent.window.window[1].curClothingBarcode;

    var curClothing;

    setTimeout(function(){
        $.ajax({
            url: "/WareHouseManagement/stock/findClothingByBarcode",
            contentType: "application/json;charset=UTF-8",
            data: '{"barcode":"' + barcode + '"}',
            dataType: "json",
            type: "post",
            success: function (data) {
                curClothing=data;
                $('#cBar').val(data.barcode);
                $('#pos').val(data.slot.shelf.name+"-"+data.slot.name);
                $('#qua').val(data.quantity);
            }
        })
    },0);

    $('#confirm').on("click",function () {
        console.log("点击确认");
        var realQua=$('#confirm_input').val();
        parent.window.window[1].realQua=realQua;

        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);

    })

})