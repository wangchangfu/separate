Menu = {
    table: null
}

/**
 * 初始化数据
 * @param treeTable
 */

Menu.initMenu = function (treetable, data) {
    treetable.render({
        elem: '#test-tree-table',
        data: data,
        field: 'title',
        is_checkbox: true,
        cols: [
            {
                field: 'id',
                title: 'id',
            },
            {
                field: 'field',
                title: '菜单名称',
            },
            {
                field: 'code',
                title: '菜单编号',
            },
            {
                field: 'pcode',
                title: '菜单父编号',
            },
            {
                field: 'url',
                title: '请求地址',
            },
            {
                field: 'num',
                title: '排序',
            },
            {
                field: 'levels',
                title: '层级',
            },
            {
                field: 'isMenuName',
                title: '是否是菜单',
            },
            {
                field: 'statusName',
                title: '状态',
            },
            {
                field: 'actions',
                title: '操作',
                width: '30%',
                template: function (item) {
                    var tem = [];
                    if (item.pid == 0) {
                        tem.push('<a class="add-child" lay-filter="add">添加子级</a>');
                    }
                    tem.push('<a lay-filter="edit">编辑</a>');
                    if (item.pid > 0) {
                        tem.push('<a class="set-attr">设置属性</a>');
                    }
                    return tem.join(' <font>|</font> ')
                },
            },
        ]
    });
}
/**
 * 初始化数据
 */
/*Menu.initMenu = function () {
    debugger
    this.table.render({
        elem: '#menuList '
        , url: MyObject.ctxPath + '/menu/getPageList'
        , method: 'post'
        , title: '用户数据表'
        , page: true
        , limit: 10
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {type: 'radio'},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
            {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true},
            {title: '菜单父编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true},
            {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true},
            {title: '排序', field: 'num', align: 'center', valign: 'middle', sortable: true},
            {title: '层级', field: 'levels', align: 'center', valign: 'middle', sortable: true},
            {title: '是否是菜单', field: 'isMenuName', align: 'center', valign: 'middle', sortable: true},
            {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: true}
        ]]

    });
}*/

layui.use(['treetable'], function () {
    var table = layui.treetable;
    /*Menu.table = layui.table;
    Menu.initMenu()*/
    var ajax = new $ax(MyObject.ctxPath + "/menu/getPageList", function (data) {
        MyObject.console(data)
        Menu.initMenu(table, data)
    }, function (data) {

    });
    ajax.set("limit", 1);
    ajax.set("page", 10)
    ajax.start();


})