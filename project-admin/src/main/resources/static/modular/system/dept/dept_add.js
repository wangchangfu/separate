var deptAdd = {

}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(adddept)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/dept/add", function (data) {
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
    if(pid != null && pid != "") {
        $("#pid").val(pid);
        form.render('select');
    }
})

