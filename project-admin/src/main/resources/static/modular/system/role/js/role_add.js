var RoleInfo = {

}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(addrole)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/role/add", function (data) {
            MyObject.success("提交成功！");
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }, function (data) {
            MyObject.error("提交失败!" + data.responseJSON.msg + "!");
        });
        ajax.setData(data.field);
        ajax.start();
        //console.info(data.field)
        return false;
    })
    var pid = $("#pidval").val();
    var deptid = $("#deptidval").val();
    if(pid != null && pid != "") {
        $("#pid").val(pid);
    }
    if(deptid != null && deptid != "") {
        $("#deptid").val(deptid);
    }
    form.render('select');
})