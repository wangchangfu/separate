LayTable = {}

/**
 * 重载数据表格
 * @param table 需要重载的表格对象
 * @param elem  表格元素
 * @param queryData 重载参数 键值对形式
 */
LayTable.refresh = function (table, elem, queryData) {
    debugger
    table.reload(elem, {
        page: {
            curr: 1 //重新从第 1 页开始
        }
        , where: queryData

    });
}