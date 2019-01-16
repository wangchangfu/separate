/**
 * 封装公共常用方法
 * 弹框使用layui弹框
 * @type {{ctxPath: string, addCtx: MyObject.addCtx, confirm: MyObject.confirm}}
 */
var MyObject = {
    ctxPath: "",
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    /**
     * 询问框
     * @param tip
     * @param ensure
     */
    confirm: function (tip, ensure) {
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    /**
     * 输出日志
     * @param info
     */
    log: function (info) {
        console.log(info);
    },
    /**
     * 向控制台输出
     * @param info
     */
    console: function (info) {
        console.info(info);
    },
    /**
     * 消息提示框
     * @param info 内容
     * @param iconIndex 图标值
     */
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    /**
     * 提示提示框
     * @param info
     */
    info: function (info) {
        MyObject.alert(info, 0);
    },
    /**
     * 成功提示框
     * @param info
     */
    success: function (info) {
        MyObject.alert(info, 1);
    },
    /**
     * 错误提示框
     * @param info
     */
    error: function (info) {
        MyObject.alert(info, 2);
    },
    /**
     * 详情弹框
     * @param title
     * @param info
     */
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    /**
     * 输入弹框
     * @param obj
     */
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    /**
     * ajax请求弹框
     * @param url
     * @param tip
     * @returns {$ax|*}
     */
    baseAjax: function (url, tip) {
        var ajax = new $ax(MyObject.ctxPath + url, function (data) {
            debugger
            console.info(data)
            MyObject.success(tip + "成功!");
        }, function (data) {
            MyObject.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    /**
     * 修改ajax
     * @param url
     * @returns {*|$ax}
     */
    changeAjax: function (url) {
        return MyObject.baseAjax(url, "修改");
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    },
    /**
     * session超时执行动作
     */
    sessionTimeoutRegistry: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                if (sessionstatus == "timeout") {
                    //如果超时就处理 ，指定要跳转的页面
                    window.location = MyObject.ctxPath + "/global/sessionError";
                }
            }
        });
    },
    /**
     * 验证
     * @param formId 容器id
     * @param fields 参数
     */
    initValidator: function (formId, fields) {
        $('#' + formId).bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields,
            live: 'enabled',
            message: '该字段不能为空'
        });
    },
    underLineToCamel: function (str) {
        var strArr = str.split('_');
        for (var i = 1; i < strArr.length; i++) {
            strArr[i] = strArr[i].charAt(0).toUpperCase() + strArr[i].substring(1);
        }
        var result = strArr.join('');
        return result.charAt(0).toUpperCase() + result.substring(1);
    },
    randomNum: function (minNum, maxNum) {
        switch (arguments.length) {
            case 1:
                return parseInt(Math.random() * minNum + 1, 10);
                break;
            case 2:
                return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
                break;
            default:
                return 0;
                break;
        }
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    /**
     * 创建标签
     * @param href
     * @param menuName
     */
    newCrontab: function (href, menuName) {
        var dataUrl = href;
        var needCreateCrontab = true;

        // 轮询已有的标签，判断是否已经存在标签
        parent.$('.J_menuTab').each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                    parent.MyCrontab.scrollToTab(this);
                    parent.MyCrontab.$('.J_mainContent .J_iframe').each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.J_iframe').hide();
                            $(this).attr('src', $(this).attr('src'));
                            return false;
                        }
                    });
                }
                needCreateCrontab = false;
                return false;
            }
        });

        //创建标签
        if (needCreateCrontab) {
            var tabLink = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            parent.$('.J_menuTab').removeClass('active');
            parent.$('.J_menuTabs .page-tabs-content').append(tabLink);

            var iframeContent = '<iframe class="J_iframe" name="iframe' + MyObject.randomNum(100, 999) + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            parent.$('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(iframeContent);
            parent.MyCrontab.scrollToTab($('.J_menuTab.active'));
        }
    }
};