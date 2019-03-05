var Dict = {}
/**
 * 初始化数据
 * @param table
 */
Dict.initDict = function (table) {
    table.render({
        elem: '#dictList'
        , url: MyObject.ctxPath + '/dict/getPageList'
        , method: 'post'
        , title: '部门列表'
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID',align: 'center'}
            , {field: 'name', title: '名称',align: 'center'}
            , {field: 'detail', title: '详情',align: 'center'}
            , {field: 'tips', title: '备注',align: 'center'}
        ]]

    });
}

layui.use('table', function () {
    var table = layui.table;
    Dict.initDict(table)
})