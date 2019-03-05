operationLog = {}
/**
 * 初始化数据
 * @param table
 */

operationLog.operationLog = function (table) {
    table.render({
        elem: '#opretion_logList'
        , url: MyObject.ctxPath + '/log/list'
        , method: 'post'
        , title: '业务日志'
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID'}
            , {field: 'logtype', title: '日志类型'}
            , {field: 'logname', title: '日志名称'}
            , {field: 'userName', title: '用户名称'}
            , {field: 'classname', title: '类名'}
            , {field: 'method', title: '方法名'}
            , {field: 'createtime', title: '时间'}
            , {field: 'message', title: '具体消息'}
        ]]

    });
}


layui.use('table', function () {
    var table = layui.table;
    operationLog.operationLog(table);

})