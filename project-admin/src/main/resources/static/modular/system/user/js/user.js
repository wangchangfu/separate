/**
 * 系统管理--用户管理的单例对象
 * @type {{}}
 */
User = {
    table: null,
    deptid: 0,
    seItem: null//选中的条目
}
/**
 * 初始化数据
 * @param tabel
 */
User.initUser = function () {
    User.table.render({
        elem: '#userList'
        , url:MyObject.ctxPath+'/user/getUserList'
        , toolbar: '#toolbarUser'
        , method: 'post'
        , title: '用户数据表'
        , page: true
       // , headers: {'Authorization': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjdXJyZW50VGltZU1pbGxpcyI6IjE1NDcxNzUzMTIzNjMiLCJleHAiOjE1NDcxNzU2MTIsImFjY291bnQiOiJ3YW5nIn0.uYcbwdWnVa3bEeibTVm3jEbc_TF7pTJvrUIOzM1WZ9U'}
        , limit: 10
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {type: 'radio'}
            /*, {field: 'id', title: 'ID'}*/
            , {field: 'name', title: '姓名'}
            , {field: 'sexName', title: '性别'}
            , {field: 'roleName', title: '角色'}
            , {field: 'deptName', title: '部门'}
            , {field: 'createtime', title: '创建时间'}
            , {field: 'statusName', title: '状态'}
            , {title: '操作', toolbar: '#userbar'}
        ]]
    });
};

/**
 * 验证是否选中
 * @param obj 当前表格对象
 * @returns {boolean}
 */
User.check = function (obj) {

    var checkStatus = User.table.checkStatus(obj.config.id);
    var data = checkStatus.data;
    if (data.length == 0) {
        MyObject.info("请先选中表格中的某一记录！");
        return false;
    } else {
        User.seItem = data[0];
        return true;
    }
}

/**
 * table工具栏时间
 */
User.tableToolbar = function () {
    //工具栏事件
    User.table.on('toolbar(userList)', function (obj) {

        switch (obj.event) {
            case 'user_add':
                //$(".addBtn").trigger("click");
                User.layerOpen("添加用户", MyObject.ctxPath + "/user/user_add", 800, 600);
                break;
            case 'refresh':
                User.search();
                break;
            case 'role':
                if (User.check(obj)) {
                    var url = MyObject.ctxPath + "/user/role_assign/" + User.seItem.id;
                    layerOpen("分配角色", url, 300, 400);
                }
                break;
            case 'resetpwd':
                User.resetPwd(obj)
                break;
        }
        ;
    });
}
/**
 * 重置密码
 * @param obj
 */
User.resetPwd = function (obj) {
    if (User.check(obj)) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为111111?', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(MyObject.ctxPath + "/user/reset", function (data) {
                MyObject.success("重置密码成功!");
            }, function (data) {
                MyObject.error("重置密码失败!");
            });
            ajax.set("userId", userId);
            ajax.start();
        })
    }
}

/**
 * 冻结/启用账户
 */
User.freezeAccount = function () {
    User.table.on('tool(userList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'forzen') {
            var ajax = new $ax(MyObject.ctxPath + "/user/freeze", function (data) {
                MyObject.success("冻结成功！");
                User.refresh();
            }, function (data) {
                MyObject.error("冻结失败!" + data.responseJSON.msg + "!");
            });
            ajax.set("userId", data.id);
            ajax.set("userStatus", data.statusName);
            ajax.start();
        } else if (layEvent === 'edit') {
            User.layerOpen("编辑用户", MyObject.ctxPath + "/user/user_edit?id="+data.id, 800, 600);
        } else if (layEvent === 'del') {
            var operation = function () {
                var ajax = new $ax(MyObject.ctxPath + "/user/delete", function (data) {
                    MyObject.success("删除成功！");
                    //LayTable.refresh(User)
                    User.refresh();
                }, function (data) {
                    MyObject.error("删除失败!" + data.responseJSON.msg + "!");
                });
                ajax.set("userId", data.id);
                ajax.start();
            };

            //询问是否删除
            MyObject.confirm("是否删除用户:" + data.name + "?", operation)
        }
    });
};

/**
 * 树菜单单击事件
 * @param e
 * @param treeId
 * @param treeNode
 */
User.onClickDept = function (e, treeId, treeNode) {
    var queryData = {};
    queryData['deptid'] = treeNode.id;
    LayTable.refresh(User.table, 'userList', queryData)
}

layui.use('table', function () {
    User.table = layui.table;
    User.initUser();
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(User.onClickDept)
    ztree.init();

    //解除/冻结账户
    User.freezeAccount();

    //表格工具栏单击事件
    User.tableToolbar();
})

//查询按钮
User.search = function(){
    var name = $('#name').val();
    var roleid = $('#roleid').val();
    var table = layui.table;
    table.reload("userList",{
        url: MyObject.ctxPath + '/user/getUserList',
        page:{
            curr: 1
        },
        where:{
            'name':name,
            'roleid':roleid
        }
    });
}

/**
 * 表格刷新-当前页
 */
User.refresh = function () {
    var name = $("#name").val();
    User.table.reload('userList', {
        where: {
            "name":name
        }
    });
}

//打开弹窗，关闭时刷新表格
User.layerOpen = function(title, url, w, h) {
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
            User.refresh();
        }
    });
}

$(function(){
    User.setUserDept();
})
User.setUserDept = function() {
    var ajax = new $ax(MyObject.ctxPath + "/user/getPieForUserDept", function (data) {
        //员工总数
        var total = 0;
        $.each(data,function(i,val){
            total += val.value;
        })
        $("#total").text(total);
        //饼图
        var myChart = echarts.init(document.getElementById('chartUserDept'));
        myChart.setOption({
            title: {
                text: '部门人员占比',
                left: 'left'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '部门',
                    type: 'pie',
                    radius : '52%',
                    center: ['45%', '60%'],
                    data:data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    }, function (data) {
        MyObject.error("图表加载异常!");
    });
    ajax.start();
}
