/**
 * 登陆
 */
$(function() {
    login();

});

function login() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var account = $.common.trim($("input[name='account']").val());
    var password = $.common.trim($("input[name='password']").val());
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "account": account,
            "password": password

        },
        success: function(r) {
            if (r.code == 0) {
                location.href = ctx + 'index';   //跳转到主页
            } else {
                $.modal.closeLoading();
                $(".code").val("");
                $.modal.msg(r.msg);
            }
        }
    });
}
