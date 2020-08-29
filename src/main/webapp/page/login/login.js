layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //登录按钮
    form.on("submit(login)",function(data){
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        setTimeout(function(){
            console.log($('#username').val());
            console.log($('#password').val());
            var username=$('#username').val();
            var password=$('#password').val();
            $.ajax({
                url: "/WareHouseManagement/user/login",
                contentType: "application/json;charset=UTF-8",
                data: '{"username":"' + username + '","password":"' + password + '"}',
                dataType: "json",
                type: "post",
                success: function (data) {
                    //window.location.href = "/WareHouseManagement/index.html";
                    layer.msg("登录成功");
                    user=data;
                    window.location.href = "/WareHouseManagement/";
                },
                error:function () {
                    layer.msg("用户名或密码错误");
                    return;
                }
            });
            $.ajax({

            })

        },1);

    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
