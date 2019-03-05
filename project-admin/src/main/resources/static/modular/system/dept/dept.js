var Dept={
    table: null,
    seItem: null
}

Dept.initDept = function () {
    Dept.table.render({
        elem: '#deptList'
        , url: MyObject.ctxPath + '/dept/getList'
        , toolbar: '#toolbarDept'
        , method: 'post'
        , title: '部门列表'
        , page: true
        , cols: [[
            {type: 'radio'}
            , {field: 'simplename', title: '部门简称'}
            , {field: 'fullname', title: '部门全称'}
            , {field: 'tips', title: '备注'}
            , {title: '操作', toolbar: '#deptbar'}
        ]]
    });
}

/**
 * 表格工具条单击事件
 */
Dept.tableToolbar = function () {
    //工具栏事件
    Dept.table.on('toolbar(deptList)', function (obj) {
        switch (obj.event) {
            case 'dept_add':
                Dept.layerOpen("添加部门", MyObject.ctxPath + "/dept/dept_add?id=", 800, 500);
                break;
        };
    });
}

//表格行按钮
Dept.tablebar = function () {
    Dept.table.on('tool(deptList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'edit') {
            var id = obj.data.id;
            Dept.layerOpen("编辑部门", MyObject.ctxPath + "/dept/dept_add?id="+id, 800, 500);
        } else if (layEvent === 'del') {
            var operation = function () {
                var ajax = new $ax(MyObject.ctxPath + "/dept/delete", function (data) {
                    MyObject.success("删除成功！");
                    var table = layui.table;
                    table.reload("deptList",{
                        url: MyObject.ctxPath + '/dept/getList',
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
            MyObject.confirm("删除时将同时删除该部门下的子部门，是否删除部门:" + data.fullname + "?", operation)
        }
    });
}

layui.use('table', function () {
    Dept.table = layui.table;
    Dept.initDept();
    Dept.tableToolbar();
    Dept.tablebar();
})

//查询按钮
Dept.search = function(){
    var fullname = $('#fullname').val();
    var table = layui.table;
    table.reload("deptList",{
        url: MyObject.ctxPath + '/dept/getList',
        page:{
            curr: 1
        },
        where:{
            'fullname':fullname
        }
    });
}

//打开弹窗，关闭时刷新表格
Dept.layerOpen = function(title, url, w, h) {
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
            table.reload("deptList",{
                url: MyObject.ctxPath + '/dept/getList',
                where:{
                }
            });
        }
    });
}
$(function(){
    Dept.setUserDept();
})
Dept.setUserDept = function() {
    var ajax = new $ax(MyObject.ctxPath + "/user/getPieForUserDept", function (data) {
        //部门总数
        var total = Object.keys(data).length;
        $("#total").text(total);
        //饼图
        var myChart = echarts.init(document.getElementById('chartUserDept'));
        myChart.setOption({
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '部门',
                    type: 'pie',
                    radius : '52%',
                    center: ['50%', '40%'],
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
