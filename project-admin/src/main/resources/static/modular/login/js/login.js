/**
 * 登陆
 */
$(function() {
    //点击登陆
    $('#btnSubmit').click(function() {
        login();
    });

});

function login() {

    //alert(ctx);
    $('.messagepass').html("");
    var account = $("input[name='account']").val();
    var password = $("input[name='password']").val();
    var data = {
        account: account,
        password: password,
        rememberMe:true
    }
    $.ajax({
        type: 'POST',
        url: rootPath + "ajaxlogin",
        dataType: "json",
        async:true,
        data: data,
        success: function(result){

            if(result.code == 200){
               var cur= window.document.location.href;
               var path =window.document.location.pathname;
                var s=cur.indexOf(path);
                window.location =cur.substring(0,s)+"/home";
            }else{
                $('.messagepass').html(result.msg);

            }
        }
    });
}
