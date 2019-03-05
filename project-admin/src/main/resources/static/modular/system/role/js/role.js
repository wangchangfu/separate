var Role = {
    id: "roleList",
    table: null,
    seItem: null
}


/**
 * 初始化角色列表
 */
Role.initRole = function () {
    this.table.render({
        elem: '#roleList'
        , url: MyObject.ctxPath + '/role/getPageRole'
        , toolbar: '#toolbarRole'
        , method: 'post'
        , title: '角色列表'
        , page: true
        , cols: [[
            {type: 'radio'}
            , {field: 'name', title: '角色名'}
            , {field: 'pName', title: '上级角色'}
            , {field: 'deptName', title: '所在部门'}
            , {field: 'tips', title: '别名'}
            , {title: '操作', toolbar: '#rolebar'}
        ]]
    });
}
/**
 * 验证是否选中
 * @param obj
 */
Role.check = function (obj) {
    var checkStatus = this.table.checkStatus(obj.config.id);
    var data = checkStatus.data;
    if (data.length == 0) {
        MyObject.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Role.seItem = data[0];
        return true;
    }
}


/**
 * 表格工具条单击事件
 */
Role.tableToolbar = function () {
    //工具栏事件
    Role.table.on('toolbar(roleList)', function (obj) {
        switch (obj.event) {
            case 'role_add':
                Role.layerOpen("添加角色", MyObject.ctxPath + "/role/role_add?id=", 800, 500);
                break;
            case 'refresh':
                break;
            case 'auth_setting':
                if (Role.check(obj)) {
                    var url = MyObject.ctxPath + "/role/role_assign/" + Role.seItem.id;
                    layerOpen("权限配置", url, 400, 500);
                }
                break;
            case 'resetpwd':
                break;
        };
    });

}

//表格行按钮
Role.tablebar = function () {
    Role.table.on('tool(roleList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'edit') {
            var id = obj.data.id;
            Role.layerOpen("编辑角色", MyObject.ctxPath + "/role/role_add?id="+id, 800, 500);
        } else if (layEvent === 'del') {
            var operation = function () {
                var ajax = new $ax(MyObject.ctxPath + "/role/delete", function (data) {
                    MyObject.success("删除成功！");
                    var table = layui.table;
                    table.reload("roleList",{
                        url: MyObject.ctxPath + '/role/getPageRole',
                        where:{
                        }
                    });
                }, function (data) {
                    MyObject.error("删除失败!" + data.responseJSON.msg + "!");
                })
                ajax.set("id", data.id);
                ajax.start();
            }

            //询问是否删除
            MyObject.confirm("删除时将同时删除该角色下的子角色，是否删除角色:" + data.name + "?", operation)
        }
    });
}

/**
 * 初始化数据
 */
layui.use('table', function () {
    Role.table = layui.table;
    Role.initRole();
    Role.tableToolbar();
    Role.tablebar()
})

//查询按钮
Role.search = function(){
    var name = $('#name').val();
    var table = layui.table;
    table.reload("roleList",{
        url: MyObject.ctxPath + '/role/getPageRole',
        page:{
            curr: 1
        },
        where:{
            'roleName':name
        }
    });
}

//打开弹窗，关闭时刷新表格
Role.layerOpen = function(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "404.jsp";
    }
    ;
    if (w == null || w == '') {
        w = ($(window).width() * 0.9);
    }
    ;
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }
    ;
    layer.open({
        type : 2,
        area : [ w + 'px', h + 'px' ],
        fix : false, // 不固定
        maxmin : true,
        shadeClose : true,
        shade : 0.4,
        title : title,
        content : url,
        end:function () {
            var table = layui.table;
            table.reload("roleList",{
                url: MyObject.ctxPath + '/role/getPageRole',
                where:{
                }
            });
        }
    });
}
