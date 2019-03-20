layui.define(["form"], function (exports) {
    var MOD_NAME = "treetable",
        o = layui.jquery,
        form = layui.form,
        tree = function () {
        };
    tree.prototype.cinfig = function (e) {
        this.c = o.extend({
            elem: "#tree-table",
            field: "id",
            icon_class: "down",
            icon_val: {
                open: "&#xe623;",
                close: "&#xe625;"
            },
            space: 4,
            new_data: [],
            childs: [],
            is_open: false,
        }, e)
    };
    tree.prototype.on = function (events, callback) {
        return layui.onevent.call(this, MOD_NAME, events, callback)
    };


    tree.prototype.template = function(data) {
        var t = this,
            level = [],
            tbody = "",
            thead = t.c.is_checkbox ? '<td><input type="checkbox" lay-skin="primary" lay-filter="lay-t"></td>' : '';
        o.each(t.c.cols, function(idx, obj) {
            thead += '<th style="width:' + obj.width + '">' + obj.title + "</th>"
        });
        o.each(data, function(index, item) {
            var hide_class = 'class="' + (item.pid == 0 || item.pid == t.cache(item.pid) || t.c.is_open ? "" : "hide") + '"',
                tr = '<tr data-id="' + item.id + '" data-pid="' + item.pid + '" ' + hide_class + ">" +
                    (t.c.is_checkbox ? '<td><div><input type="checkbox" lay-skin="primary" lay-filter="lay-t"></div></td>' : "");
            level[item.id] = item.pid > 0 ? (level[item.pid] + 1) : 0;
            o.each(t.c.cols, function(idx, obj) {
                tr += '<td style="width:' + obj.width + '">';
                if(obj.field == t.c.field) {
                    tr += ("&nbsp;".repeat(level[item.id] * t.c.space));
                    if(t.c.childs[item.id]) {
                        tr += '<i class="layui-icon ' + t.c.icon_class + '">' + (item.id == t.cache(item.id) || t.c.is_open ? t.c.icon_val.close : t.c.icon_val.open) + "</i>"
                    }
                }
                tr += (item[obj.field] !== undefined ? item[obj.field] : (obj.template ? obj.template(item) : "")) + "</td>"
            });
            tbody += tr + "</tr>";
        });
        return '<thead><tr data-id="0" data-pid="-1">' + thead + "</tr></thead><tbody>" + tbody + "</tbody>"
    };
})