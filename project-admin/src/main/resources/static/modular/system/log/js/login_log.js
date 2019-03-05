LoginLog = {}
/**
 * 初始化数据
 * @param table
 */

LoginLog.initLoinLog = function (table) {
    table.render({
        elem: '#login_logList'
        , url: MyObject.ctxPath + '/loginLog/list'
        , method: 'post'
        , title: '登录日志'
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
    LoginLog.initLoinLog(table);

})