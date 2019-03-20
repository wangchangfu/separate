'use strict';

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

/**
 * name: formSelects
 * 基于Layui Select多选
 * version: 4.0.0.0706
 * http://sun.faysunshine.com/layui/formSelects-v4/dist/formSelects-v4.js
 */
(function (layui, window, factory) {
	if ((typeof exports === 'undefined' ? 'undefined' : _typeof(exports)) === 'object') {
		// 支持 CommonJS
		module.exports = factory();
	} else if (typeof define === 'function' && define.amd) {
		// 支持 AMD
		define(factory);
	} else if (window.layui && layui.define) {
		//layui加载
		layui.define(['jquery'], function (exports) {
			exports('formSelects', factory());
		});
	} else {
		window.formSelects = factory();
	}
})(typeof layui == 'undefined' ? null : layui, window, function () {
	var v = '4.0.0.0704',
	    NAME = 'xm-select',
	    PNAME = 'xm-select-parent',
	    INPUT = 'xm-select-input',
	    TDIV = 'xm-select--suffix',
	    THIS = 'xm-select-this',
	    LABEL = 'xm-select-label',
	    SEARCH = 'xm-select-search',
	    SEARCH_TYPE = 'xm-select-search-type',
	    SHOW_COUNT = 'xm-select-show-count',
	    CREATE = 'xm-select-create',
	    CREATE_LONG = 'xm-select-create-long',
	    MAX = 'xm-select-max',
	    SKIN = 'xm-select-skin',
	    DIRECTION = "xm-select-direction",
	    HEIGHT = 'xm-select-height',
	    DISABLED = 'xm-dis-disabled',
	    DIS = 'xm-select-dis',
	    TEMP = 'xm-select-temp',
	    RADIO = 'xm-select-radio',
	    LINKAGE = 'xm-select-linkage',
	    DL = 'xm-select-dl',
	    DD_HIDE = 'xm-select-hide',
	    HIDE_INPUT = 'xm-hide-input',
	    SANJIAO = 'xm-select-sj',
	    ICON_CLOSE = 'xm-icon-close',
	    FORM_TITLE = 'xm-select-title',
	    FORM_SELECT = 'xm-form-select',
	    FORM_SELECTED = 'xm-form-selected',
	    FORM_NONE = 'xm-select-none',
	    FORM_EMPTY = 'xm-select-empty',
	    FORM_INPUT = 'xm-input',
	    FORM_DL_INPUT = 'xm-dl-input',
	    FORM_SELECT_TIPS = 'xm-select-tips',
	    CHECKBOX_YES = 'xm-icon-yes',
	    CZ = 'xm-cz',
	    CZ_GROUP = 'xm-cz-group',
	    TIPS = '请选择',
	    data = {},
	    events = {
		on: {},
		filter: {},
		maxTips: {}
	},
	    ajax = {
		type: 'get',
		header: {},
		first: true,
		data: {},
		searchUrl: '',
		searchName: 'keyword',
		searchVal: null,
		keyName: 'name',
		keyVal: 'value',
		keySel: 'selected',
		keyDis: 'disabled',
		keyChildren: 'children',
		dataType: '',
		delay: 500,
		beforeSuccess: null,
		success: null,
		error: null,
		beforeSearch: null,
		clearInput: false
	},
	    quickBtns = [{ icon: 'iconfont icon-quanxuan', name: '全选', click: function click(id, cm) {
			cm.selectAll(id, true, true);
		} }, { icon: 'iconfont icon-qingkong', name: '清空', click: function click(id, cm) {
			cm.removeAll(id, true, true);
		} }, { icon: 'iconfont icon-fanxuan', name: '反选', click: function click(id, cm) {
			cm.reverse(id, true, true);
		} }, { icon: 'iconfont icon-pifu', name: '换肤', click: function click(id, cm) {
			cm.skin(id);
		} }],
	    $ = window.$ || window.layui && window.layui.jquery,
	    $win = $(window),
	    ajaxs = {},
	    FormSelects = function FormSelects(options) {
		var _this = this;

		this.config = {
			name: null, //xm-select="xxx"
			max: null,
			maxTips: function maxTips(vals, val, max) {
				var ipt = $('[xid="' + _this.config.name + '"]').prev().find('.' + NAME);
				if (ipt.parents('.layui-form-item[pane]').length) {
					ipt = ipt.parents('.layui-form-item[pane]');
				}
				ipt.attr('style', 'border-color: red !important');
				setTimeout(function () {
					ipt.removeAttr('style');
				}, 300);
			},
			init: null, //初始化的选择值,
			on: null, //select值发生变化
			filter: function filter(id, inputVal, val, isDisabled) {
				return val.name.indexOf(inputVal) == -1;
			},
			clearid: -1,
			direction: 'auto',
			height: null,
			isEmpty: false,
			btns: [quickBtns[0], quickBtns[1], quickBtns[2]],
			searchType: 0,
			create: function create(id, name) {
				return Date.now();
			},
			template: function template(name, value, selected, disabled) {
				return name;
			},
			showCount: 0,
			isCreate: false,
			placeholder: TIPS
		};
		this.select = null;
		this.values = [];
		$.extend(true, this.config, options);
	};

	//一些简单的处理方法
	var Common = function Common() {
		this.appender();
		this.init();
		this.on();
		this.initVal();
		this.onreset();
	};

	Common.prototype.appender = function () {
		//针对IE做的一些拓展
		//拓展Array map方法
		if (!Array.prototype.map) {
			Array.prototype.map = function (i, h) {
				var b,
				    a,
				    c,
				    e = Object(this),
				    f = e.length >>> 0;if (h) {
					b = h;
				}a = new Array(f);c = 0;while (c < f) {
					var d, g;if (c in e) {
						d = e[c];g = i.call(b, d, c, e);a[c] = g;
					}c++;
				}return a;
			};
		};

		//拓展Array foreach方法
		if (!Array.prototype.forEach) {
			Array.prototype.forEach = function forEach(g, b) {
				var d, c;if (this == null) {
					throw new TypeError("this is null or not defined");
				}var f = Object(this);var a = f.length >>> 0;if (typeof g !== "function") {
					throw new TypeError(g + " is not a function");
				}if (arguments.length > 1) {
					d = b;
				}c = 0;while (c < a) {
					var e;if (c in f) {
						e = f[c];g.call(d, e, c, f);
					}c++;
				}
			};
		};

		//jquery横向滚动, https://github.com/jquery/jquery-mousewheel	
		(function (a) {
			if (typeof define === "function" && define.amd) {
				define(["jquery"], a);
			} else {
				if ((typeof exports === 'undefined' ? 'undefined' : _typeof(exports)) === "object") {
					module.exports = a;
				} else {
					a($);
				}
			}
		})(function (c) {
			var d = ["wheel", "mousewheel", "DOMMouseScroll", "MozMousePixelScroll"],
			    k = "onwheel" in document || document.documentMode >= 9 ? ["wheel"] : ["mousewheel", "DomMouseScroll", "MozMousePixelScroll"],
			    h = Array.prototype.slice,
			    j,
			    b;if (c.event.fixHooks) {
				for (var e = d.length; e;) {
					c.event.fixHooks[d[--e]] = c.event.mouseHooks;
				}
			}var f = c.event.special.mousewheel = { version: "3.1.12", setup: function setup() {
					if (this.addEventListener) {
						for (var m = k.length; m;) {
							this.addEventListener(k[--m], l, false);
						}
					} else {
						this.onmousewheel = l;
					}c.data(this, "mousewheel-line-height", f.getLineHeight(this));c.data(this, "mousewheel-page-height", f.getPageHeight(this));
				}, teardown: function teardown() {
					if (this.removeEventListener) {
						for (var m = k.length; m;) {
							this.removeEventListener(k[--m], l, false);
						}
					} else {
						this.onmousewheel = null;
					}c.removeData(this, "mousewheel-line-height");c.removeData(this, "mousewheel-page-height");
				}, getLineHeight: function getLineHeight(m) {
					var i = c(m),
					    n = i["offsetParent" in c.fn ? "offsetParent" : "parent"]();if (!n.length) {
						n = c("body");
					}return parseInt(n.css("fontSize"), 10) || parseInt(i.css("fontSize"), 10) || 16;
				}, getPageHeight: function getPageHeight(i) {
					return c(i).height();
				}, settings: { adjustOldDeltas: true, normalizeOffset: true } };c.fn.extend({ mousewheel: function mousewheel(i) {
					return i ? this.bind("mousewheel", i) : this.trigger("mousewheel");
				}, unmousewheel: function unmousewheel(i) {
					return this.unbind("mousewheel", i);
				} });function l(i) {
				var o = i || window.event,
				    u = h.call(arguments, 1),
				    w = 0,
				    q = 0,
				    p = 0,
				    t = 0,
				    s = 0,
				    r = 0;i = c.event.fix(o);i.type = "mousewheel";if ("detail" in o) {
					p = o.detail * -1;
				}if ("wheelDelta" in o) {
					p = o.wheelDelta;
				}if ("wheelDeltaY" in o) {
					p = o.wheelDeltaY;
				}if ("wheelDeltaX" in o) {
					q = o.wheelDeltaX * -1;
				}if ("axis" in o && o.axis === o.HORIZONTAL_AXIS) {
					q = p * -1;p = 0;
				}w = p === 0 ? q : p;if ("deltaY" in o) {
					p = o.deltaY * -1;w = p;
				}if ("deltaX" in o) {
					q = o.deltaX;if (p === 0) {
						w = q * -1;
					}
				}if (p === 0 && q === 0) {
					return;
				}if (o.deltaMode === 1) {
					var v = c.data(this, "mousewheel-line-height");w *= v;p *= v;q *= v;
				} else {
					if (o.deltaMode === 2) {
						var n = c.data(this, "mousewheel-page-height");w *= n;p *= n;q *= n;
					}
				}t = Math.max(Math.abs(p), Math.abs(q));if (!b || t < b) {
					b = t;if (a(o, t)) {
						b /= 40;
					}
				}if (a(o, t)) {
					w /= 40;q /= 40;p /= 40;
				}w = Math[w >= 1 ? "floor" : "ceil"](w / b);q = Math[q >= 1 ? "floor" : "ceil"](q / b);p = Math[p >= 1 ? "floor" : "ceil"](p / b);if (f.settings.normalizeOffset && this.getBoundingClientRect) {
					var m = this.getBoundingClientRect();s = i.clientX - m.left;r = i.clientY - m.top;
				}i.deltaX = q;i.deltaY = p;i.deltaFactor = b;i.offsetX = s;i.offsetY = r;i.deltaMode = 0;u.unshift(i, w, q, p);if (j) {
					clearTimeout(j);
				}j = setTimeout(g, 200);return (c.event.dispatch || c.event.handle).apply(this, u);
			}function g() {
				b = null;
			}function a(m, i) {
				return f.settings.adjustOldDeltas && m.type === "mousewheel" && i % 120 === 0;
			}
		});
	};

	Common.prototype.init = function (target) {
		var _this2 = this;

		//初始化页面上已有的select
		$(target ? target : 'select[' + NAME + ']').each(function (index, select) {
			var othis = $(select),
			    id = othis.attr(NAME),
			    hasRender = othis.next('.layui-form-select'),
			    disabled = select.disabled,
			    max = othis.attr(MAX) - 0,
			    isSearch = othis.attr(SEARCH) != undefined,
			    searchUrl = isSearch ? othis.attr(SEARCH) : null,
			    isCreate = othis.attr(CREATE) != undefined,
			    isRadio = othis.attr(RADIO) != undefined,
			    skin = othis.attr(SKIN),
			    direction = othis.attr(DIRECTION),
			    optionsFirst = select.options[0],
			    height = othis.attr(HEIGHT),
			    formname = othis.attr('name'),
			    layverify = othis.attr('lay-verify'),
			    layverType = othis.attr('lay-verType'),
			    searchtype = othis.attr(SEARCH_TYPE) == 'dl' ? 1 : 0,
			    showCount = othis.attr(SHOW_COUNT) - 0,
			    placeholder = optionsFirst ? optionsFirst.value ? TIPS : optionsFirst.innerHTML || TIPS : TIPS,
			    value = othis.find('option[selected]').toArray().map(function (option) {
				//获取已选中的数据
				return {
					name: option.innerHTML,
					val: option.value
				};
			}),
			    fs = new FormSelects();
			if (isNaN(showCount) || showCount <= 0) {
				showCount = 19921012;
			}

			var hisFs = data[id];
			data[id] = fs;

			fs.values = value;
			fs.config.init = value.concat([]);
			fs.config.name = id;
			fs.config.disabled = disabled;
			fs.config.max = max;
			fs.config.isSearch = isSearch;
			fs.config.searchUrl = searchUrl;
			fs.config.isCreate = isCreate;
			fs.config.radio = isRadio;
			fs.config.skin = skin;
			fs.config.direction = direction;
			fs.config.height = height;
			fs.config.searchType = searchtype;
			fs.config.formname = formname;
			fs.config.layverify = layverify;
			fs.config.layverType = layverType;
			fs.config.searchType = searchtype;
			fs.config.showCount = showCount;
			fs.config.placeholder = placeholder;

			if (hisFs) {
				$.extend(true, fs.config, hisFs.config);
				disabled = fs.config.disabled;
				isSearch = fs.config.isSearch;
				searchUrl = fs.config.searchUrl;
				skin = fs.config.skin;
				height = fs.config.height;
				max = fs.config.max;
				formname = fs.config.formname;
				layverify = fs.config.layverify;
				layverType = fs.config.layverType;
				placeholder = fs.config.placeholder;
				isRadio = fs.config.radio;

				if (hisFs.config.init) {
					fs.values = hisFs.config.init.map(function (item) {
						if ((typeof item === 'undefined' ? 'undefined' : _typeof(item)) == 'object') {
							return item;
						}
						return {
							name: othis.find('option[value="' + item + '"]').text(),
							val: item
						};
					}).filter(function (item) {
						return item.name;
					});
					fs.config.init = fs.values.concat([]);
				}
			}

			//先取消layui对select的渲染
			hasRender[0] && hasRender.remove();

			//构造渲染div
			var dinfo = _this2.renderSelect(id, placeholder, select);
			var heightStyle = !height || height == 'auto' ? '' : 'xm-hg style="height: 34px;"';
			var inputHtml = ['<div class="' + LABEL + '">', '<input type="text" fsw class="' + FORM_INPUT + ' ' + INPUT + '" ' + (isSearch ? '' : 'style="display: none;"') + ' autocomplete="off" debounce="0" />', '</div>'];
			var reElem = $('<div class="' + FORM_SELECT + '" ' + SKIN + '="' + skin + '">\n\t\t\t\t\t<input class="' + HIDE_INPUT + '" value="" name="' + formname + '" lay-verify="' + layverify + '" lay-verType="' + layverType + '" type="text" style="position: absolute;bottom: 0; z-index: -1;width: 100%; height: 100%; border: none; opacity: 0;"/>\n\t\t\t\t\t<div class="' + FORM_TITLE + ' ' + (disabled ? DIS : '') + '">\n\t\t\t\t\t\t<div class="' + FORM_INPUT + ' ' + NAME + '" ' + heightStyle + '>\n\t\t\t\t\t\t\t' + inputHtml.join('') + '\n\t\t\t\t\t\t\t<i class="' + SANJIAO + '"></i>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="' + TDIV + '">\n\t\t\t\t\t\t\t<input type="text" autocomplete="off" placeholder="' + placeholder + '" readonly="readonly" unselectable="on" class="' + FORM_INPUT + '">\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div></div>\n\t\t\t\t\t</div>\n\t\t\t\t\t<dl xid="' + id + '" class="' + DL + ' ' + (isRadio ? RADIO : '') + '">' + dinfo + '</dl>\n\t\t\t\t</div>');

			if (hisFs) {
				$('dl[xid="' + id + '"]').parents('.' + PNAME).html(reElem);
				fs.select = othis;
			} else {
				//包裹一个div
				othis.wrap('<div class="' + PNAME + '" FS_ID="' + id + '"></div>');
				othis.after(reElem);
				fs.select = othis.remove();
			}

			//如果可搜索, 加上事件
			if (isSearch) {
				ajaxs[id] = $.extend(true, {}, ajax, {
					searchUrl: searchUrl
				});
				$(document).on('input', 'div.' + PNAME + '[FS_ID="' + id + '"] .' + INPUT, function (e) {
					_this2.search(id, e, searchUrl);
				});
				if (searchUrl) {
					//触发第一次请求事件
					_this2.triggerSearch(reElem, true);
				}
			} else {
				//隐藏第二个dl
				reElem.find('dl dd.' + FORM_DL_INPUT).css('display', 'none');
			}
		});
	};

	Common.prototype.search = function (id, e, searchUrl, call) {
		var _this3 = this;

		var input = void 0;
		if (call) {
			input = call;
		} else {
			input = e.target;
			var keyCode = e.keyCode;
			if (keyCode === 9 || keyCode === 13 || keyCode === 37 || keyCode === 38 || keyCode === 39 || keyCode === 40) {
				return false;
			}
		}
		var inputValue = $.trim(input.value);
		//过滤一下tips
		this.changePlaceHolder($(input));

		var ajaxConfig = ajaxs[id] ? ajaxs[id] : ajax;
		searchUrl = ajaxConfig.searchUrl || searchUrl;
		var fs = data[id],
		    isCreate = fs.config.isCreate,
		    reElem = $('dl[xid="' + id + '"]').parents('.' + FORM_SELECT);
		//如果开启了远程搜索
		if (searchUrl) {
			if (ajaxConfig.searchVal) {
				inputValue = ajaxConfig.searchVal;
				ajaxConfig.searchVal = '';
			}
			if (!ajaxConfig.beforeSearch || ajaxConfig.beforeSearch && ajaxConfig.beforeSearch instanceof Function && ajaxConfig.beforeSearch(id, searchUrl, inputValue)) {
				var delay = ajaxConfig.delay;
				if (ajaxConfig.first) {
					ajaxConfig.first = false;
					delay = 10;
				}
				clearTimeout(fs.clearid);
				fs.clearid = setTimeout(function () {
					reElem.find('dl > *:not(.' + FORM_SELECT_TIPS + ')').remove();
					reElem.find('dd.' + FORM_NONE).addClass(FORM_EMPTY).text('请求中');
					_this3.ajax(id, searchUrl, inputValue, false, null, true);
				}, delay);
			}
		} else {
			reElem.find('dl .' + DD_HIDE).removeClass(DD_HIDE);
			//遍历选项, 选择可以显示的值
			reElem.find('dl dd:not(.' + FORM_SELECT_TIPS + ')').each(function (idx, item) {
				var _item = $(item);
				var searchFun = data[id].config.filter || events.filter[id];
				if (searchFun && searchFun(id, inputValue, {
					name: _item.find('span').attr('name'),
					val: _item.attr('lay-value')
				}, _item.hasClass(DISABLED)) == true) {
					_item.addClass(DD_HIDE);
				}
			});
			//控制分组名称
			reElem.find('dl dt').each(function (index, item) {
				if (!$(item).nextUntil('dt', ':not(.' + DD_HIDE + ')').length) {
					$(item).addClass(DD_HIDE);
				}
			});
			//动态创建
			this.create(id, isCreate, inputValue);
			var shows = reElem.find('dl dd:not(.' + FORM_SELECT_TIPS + '):not(.' + DD_HIDE + ')');
			if (!shows.length) {
				reElem.find('dd.' + FORM_NONE).addClass(FORM_EMPTY).text('无匹配项');
			} else {
				reElem.find('dd.' + FORM_NONE).removeClass(FORM_EMPTY);
			}
		}
	};

	Common.prototype.isArray = function (obj) {
		return Object.prototype.toString.call(obj) == "[object Array]";
	};

	Common.prototype.triggerSearch = function (div, isCall) {
		var _this4 = this;

		(div ? [div] : $('.' + FORM_SELECT).toArray()).forEach(function (reElem, index) {
			reElem = $(reElem);
			var id = reElem.find('dl').attr('xid');
			if (id && data[id] && data[id].config.isEmpty || isCall) {
				_this4.search(id, null, null, data[id].config.searchType == 0 ? reElem.find('.' + LABEL + ' .' + INPUT) : reElem.find('dl .' + FORM_DL_INPUT + ' .' + INPUT));
			}
		});
	};

	Common.prototype.clearInput = function (id) {
		var div = $('.' + PNAME + '[fs_id="' + id + '"]');
		var input = data[id].config.searchType == 0 ? div.find('.' + LABEL + ' .' + INPUT) : div.find('dl .' + FORM_DL_INPUT + ' .' + INPUT);
		input.val('');
	};

	Common.prototype.ajax = function (id, searchUrl, inputValue, isLinkage, linkageWidth, isSearch) {
		var _this5 = this;

		var reElem = $('.' + PNAME + ' dl[xid="' + id + '"]').parents('.' + FORM_SELECT);
		if (!reElem[0] || !searchUrl) {
			return;
		}
		var ajaxConfig = ajaxs[id] ? ajaxs[id] : ajax;
		var ajaxData = $.extend(true, {}, ajaxConfig.data);
		ajaxData[ajaxConfig.searchName] = inputValue;
		//是否需要对ajax添加随机时间
		//ajaxData['_'] = Date.now();
		$.ajax({
			type: ajaxConfig.type,
			headers: ajaxConfig.header,
			url: searchUrl,
			data: ajaxConfig.dataType == 'json' ? JSON.stringify(ajaxData) : ajaxData,
			success: function success(res) {
				if (typeof res == 'string') {
					res = JSON.parse(res);
				}
				ajaxConfig.beforeSuccess && ajaxConfig.beforeSuccess instanceof Function && (res = ajaxConfig.beforeSuccess(id, searchUrl, inputValue, res));
				if (_this5.isArray(res)) {
					res = {
						code: 0,
						msg: "",
						data: res
					};
				}
				if (res.code != 0) {
					reElem.find('dd.' + FORM_NONE).addClass(FORM_EMPTY).text(res.msg);
				} else {
					reElem.find('dd.' + FORM_NONE).removeClass(FORM_EMPTY);
					//获得已选择的values
					_this5.renderData(id, res.data, isLinkage, linkageWidth, isSearch);
					data[id].config.isEmpty = res.data.length == 0;
				}
				ajaxConfig.success && ajaxConfig.success instanceof Function && ajaxConfig.success(id, searchUrl, inputValue, res);
			},
			error: function error(err) {
				reElem.find('dd[lay-value]:not(.' + FORM_SELECT_TIPS + ')').remove();
				reElem.find('dd.' + FORM_NONE).addClass(FORM_EMPTY).text('服务异常');
				ajaxConfig.error && ajaxConfig.error instanceof Function && ajaxConfig.error(id, searchUrl, inputValue, err);
			}
		});
	};

	Common.prototype.renderData = function (id, dataArr, linkage, linkageWidth, isSearch) {
		var _this6 = this;

		if (linkage) {
			var _ret = function () {
				//渲染多级联动
				var result = [],
				    index = 0,
				    temp = { "0": dataArr },
				    ajaxConfig = ajaxs[id] ? ajaxs[id] : ajax;

				var _loop = function _loop() {
					var group = result[index++] = [],
					    _temp = temp;
					temp = {};
					$.each(_temp, function (pid, arr) {
						$.each(arr, function (idx, item) {
							var val = {
								pid: pid,
								name: item[ajaxConfig.keyName],
								val: item[ajaxConfig.keyVal]
							};
							group.push(val);
							var children = item[ajaxConfig.keyChildren];
							if (children && children.length) {
								temp[val.val] = children;
							}
						});
					});
				};

				do {
					_loop();
				} while (Object.getOwnPropertyNames(temp).length);

				var reElem = $('.' + PNAME + ' dl[xid="' + id + '"]').parents('.' + FORM_SELECT);
				var html = ['<div class="xm-select-linkage">'];

				$.each(result, function (idx, arr) {
					var groupDiv = ['<div style="left: ' + (linkageWidth - 0) * idx + 'px;" class="xm-select-linkage-group xm-select-linkage-group' + (idx + 1) + ' ' + (idx != 0 ? 'xm-select-linkage-hide' : '') + '">'];
					$.each(arr, function (idx2, item) {
						var span = '<li title="' + item.name + '" pid="' + item.pid + '" value="' + item.val + '"><span>' + item.name + '</span></li>';
						groupDiv.push(span);
					});
					groupDiv.push('</div>');
					html = html.concat(groupDiv);
				});
				html.push('<div style="clear: both; height: 288px;"></div>');
				html.push('</div>');
				reElem.find('dl').html(html.join(''));
				reElem.find('.' + INPUT).css('display', 'none'); //联动暂时不支持搜索
				return {
					v: void 0
				};
			}();

			if ((typeof _ret === 'undefined' ? 'undefined' : _typeof(_ret)) === "object") return _ret.v;
		}

		var reElem = $('.' + PNAME + ' dl[xid="' + id + '"]').parents('.' + FORM_SELECT);
		var ajaxConfig = ajaxs[id] ? ajaxs[id] : ajax;
		var pcInput = reElem.find('.' + TDIV + ' input');

		var values = [];
		reElem.find('dl').html(this.renderSelect(id, pcInput.attr('placeholder') || pcInput.attr('back'), dataArr.map(function (item) {
			if (item[ajaxConfig.keySel]) {
				values.push({
					name: item[ajaxConfig.keyName],
					val: item[ajaxConfig.keyVal]
				});
			}
			return {
				innerHTML: item[ajaxConfig.keyName],
				value: item[ajaxConfig.keyVal],
				sel: item[ajaxConfig.keySel],
				disabled: item[ajaxConfig.keyDis],
				type: item.type,
				name: item.name
			};
		})));

		var label = reElem.find('.' + LABEL);
		var dl = reElem.find('dl[xid]');
		if (isSearch) {
			//如果是远程搜索, 这里需要判重
			var oldVal = data[id].values;
			oldVal.forEach(function (item, index) {
				dl.find('dd[lay-value="' + item.val + '"]').addClass(THIS);
			});
			values.forEach(function (item, index) {
				if (_this6.indexOf(oldVal, item) == -1) {
					_this6.addLabel(id, label, item);
					dl.find('dd[lay-value="' + item.val + '"]').addClass(THIS);
					oldVal.push(item);
				}
			});
		} else {
			values.forEach(function (item, index) {
				_this6.addLabel(id, label, item);
				dl.find('dd[lay-value="' + item.val + '"]').addClass(THIS);
			});
			data[id].values = values;
		}
		this.commonHanler(id, label);
	};

	Common.prototype.create = function (id, isCreate, inputValue) {
		if (isCreate && inputValue) {
			var fs = data[id],
			    dl = $('[xid="' + id + '"]'),
			    tips = dl.find('dd.' + FORM_SELECT_TIPS + '.' + FORM_DL_INPUT),
			    tdd = null,
			    temp = dl.find('dd.' + TEMP);
			dl.find('dd:not(.' + FORM_SELECT_TIPS + '):not(.' + TEMP + ')').each(function (index, item) {
				if (inputValue == $(item).find('span').attr('name')) {
					tdd = item;
				}
			});
			if (!tdd) {
				//如果不存在, 则创建
				var val = fs.config.create(id, name);
				if (temp[0]) {
					temp.attr('lay-value', val);
					temp.find('span').text(inputValue);
					temp.removeClass(DD_HIDE);
				} else {
					tips.after($(this.createDD(id, {
						innerHTML: inputValue,
						value: val
					}, TEMP + ' ' + CREATE_LONG)));
				}
			}
		} else {
			$('[xid=' + id + '] dd.' + TEMP).remove();
		}
	};

	Common.prototype.createDD = function (id, item, clz) {
		var name = $.trim(item.innerHTML);
		var template = data[id].config.template(name, item.value, item.selected, item.disabled);
		return '<dd lay-value="' + item.value + '" class="' + (item.disabled ? DISABLED : '') + ' ' + (clz ? clz : '') + '">\n\t\t\t\t\t<div class="xm-unselect xm-form-checkbox ' + (item.disabled ? DISABLED : '') + '">\n\t\t\t\t\t\t<i class="' + CHECKBOX_YES + '"></i>\n\t\t\t\t\t\t<span name="' + name + '">' + template + '</span>\n\t\t\t\t\t</div>\n\t\t\t\t</dd>';
	};

	Common.prototype.createQuickBtn = function (obj, right) {
		return '<div class="' + CZ + '" method="' + obj.name + '" title="' + obj.name + '" ' + (right ? 'style="margin-right: ' + right + '"' : '') + '><i class="' + obj.icon + '"></i><span>' + obj.name + '</span></div>';
	};

	Common.prototype.renderBtns = function (id, show, right) {
		var _this7 = this;

		var quickBtn = [];
		var dl = $('dl[xid="' + id + '"]');
		quickBtn.push('<div class="' + CZ_GROUP + '" show="' + show + '" style="max-width: ' + (dl.prev().width() - 54) + 'px;">');
		$.each(data[id].config.btns, function (index, item) {
			quickBtn.push(_this7.createQuickBtn(item, right));
		});
		quickBtn.push('</div>');
		quickBtn.push(this.createQuickBtn({ icon: 'iconfont icon-caidan', name: '' }));
		return quickBtn.join('');
	};

	Common.prototype.renderSelect = function (id, tips, select) {
		var _this8 = this;

		var arr = [];
		if (data[id].config.btns.length) {
			setTimeout(function () {
				var dl = $('dl[xid="' + id + '"]');
				dl.parents('.' + FORM_SELECT).attr(SEARCH_TYPE, data[id].config.searchType);
				dl.find('.' + CZ_GROUP).css('max-width', dl.prev().width() - 54 + 'px');
			}, 10);
			arr.push(['<dd lay-value="" class="' + FORM_SELECT_TIPS + '" style="background-color: #FFF!important;">', this.renderBtns(id, null, '30px'), '</dd>', '<dd lay-value="" class="' + FORM_SELECT_TIPS + ' ' + FORM_DL_INPUT + '" style="background-color: #FFF!important;">', '<i class="iconfont icon-sousuo"></i>', '<input type="text" class="' + FORM_INPUT + ' ' + INPUT + '" placeholder="\u8BF7\u641C\u7D22"/>', '</dd>'].join(''));
		} else {
			arr.push('<dd lay-value="" class="' + FORM_SELECT_TIPS + '">' + tips + '</dd>');
		}
		if (this.isArray(select)) {
			$(select).each(function (index, item) {
				if (item) {
					if (item.type && item.type === 'optgroup') {
						arr.push('<dt>' + item.name + '</dt>');
					} else {
						arr.push(_this8.createDD(id, item));
					}
				}
			});
		} else {
			$(select).find('*').each(function (index, item) {
				if (item.tagName.toLowerCase() == 'option' && index == 0 && !item.value) {
					return;
				}
				if (item.tagName.toLowerCase() === 'optgroup') {
					arr.push('<dt>' + item.label + '</dt>');
				} else {
					arr.push(_this8.createDD(id, item));
				}
			});
		}
		arr.push('<dt style="display:none;"> </dt>');
		arr.push('<dd class="' + FORM_SELECT_TIPS + ' ' + FORM_NONE + ' ' + (arr.length === 2 ? FORM_EMPTY : '') + '">\u6CA1\u6709\u9009\u9879</dd>');
		return arr.join('');
	};

	Common.prototype.on = function () {
		var _this9 = this;

		//事件绑定
		this.one();

		$(document).on('click', function (e) {
			if (!$(e.target).parents('.' + FORM_TITLE)[0]) {
				//清空input中的值
				$('.' + PNAME + ' dl .' + DD_HIDE).removeClass(DD_HIDE);
				$('.' + PNAME + ' dl dd.' + FORM_EMPTY).removeClass(FORM_EMPTY);
				$('.' + PNAME + ' dl dd.' + TEMP).remove();
				$.each(data, function (key, fs) {
					_this9.clearInput(key);
					if (!fs.values.length) {
						_this9.changePlaceHolder($('div[FS_ID="' + key + '"] .' + LABEL));
					}
				});
			}
			$('.' + PNAME + ' .' + FORM_SELECTED).removeClass(FORM_SELECTED);
		});

		$(document).on('mousewheel', '.xm-select-label', function (e, w) {
			var target = $(e.target);
			_this9.calcLabelLeft(target.is('.' + LABEL) ? target : target.parents('.' + LABEL), w);
		});
		$(document).on('mouseover', '.xm-select-label', function () {
			var tops = $(document).scrollTop(); //当页面滚动时，把当前距离赋值给页面，这样保持页面滚动条不动
			$(document).bind("scroll", function () {
				$(document).scrollTop(tops);
			});
		});
		$(document).on('mouseout', '.xm-select-label', function () {
			$(document).unbind("scroll");
		});
	};

	Common.prototype.scroll = function (top) {
		$(window).scroll(function () {
			$(window).scrollTop(top);
		});
	};

	Common.prototype.calcLabelLeft = function (label, w, call) {
		var pos = this.getPosition(label[0]);
		pos.y = pos.x + label[0].clientWidth;
		var left = label[0].offsetLeft;
		if (!label.find('span').length) {
			left = 0;
		} else if (call) {
			//校正归位
			var span = label.find('span:last');
			span.css('display') == 'none' ? span = span.prev()[0] : span = span[0];
			var spos = this.getPosition(span);
			spos.y = spos.x + span.clientWidth;

			if (spos.y > pos.y) {
				left = left - (spos.y - pos.y) - 5;
			} else {
				left = 0;
			}
		} else {
			if (w < 0) {
				var _span = label.find(':last');
				_span.css('display') == 'none' ? _span = _span.prev()[0] : _span = _span[0];
				var _spos = this.getPosition(_span);
				_spos.y = _spos.x + _span.clientWidth;
				if (_spos.y > pos.y) {
					left -= 10;
				}
			} else {
				if (left < 0) {
					left += 10;
				}
				if (left > 0) {
					left = 0;
				}
			}
		}
		label.css('left', left + 'px');
	};

	Common.prototype.one = function (target) {
		var _this10 = this;

		//一次性事件绑定
		$(target ? target : document).find('.' + FORM_TITLE).on('click', function (e) {
			var othis = $(e.target),
			    title = othis.is(FORM_TITLE) ? othis : othis.parents('.' + FORM_TITLE),
			    dl = title.next(),
			    id = dl.attr('xid');

			//清空非本select的input val
			$('dl[xid]').not(dl).each(function (index, item) {
				_this10.clearInput($(item).attr('xid'));
			});
			$('dl[xid]').not(dl).find('dd.' + DD_HIDE).removeClass(DD_HIDE);

			//如果是disabled select
			if (title.hasClass(DIS)) {
				return false;
			}
			//如果点击的是右边的三角或者只读的input
			if (othis.is('.' + SANJIAO) || othis.is('.' + INPUT + '[readonly]')) {
				_this10.changeShow(title, !title.parents('.' + FORM_SELECT).hasClass(FORM_SELECTED));
				return false;
			}
			//如果点击的是input的右边, focus一下
			if (title.find('.' + INPUT + ':not(readonly)')[0]) {
				var input = title.find('.' + INPUT),
				    epos = { x: e.pageX, y: e.pageY },
				    pos = _this10.getPosition(title[0]),
				    width = title.width();
				while (epos.x > pos.x) {
					if ($(document.elementFromPoint(epos.x, epos.y)).is(input)) {
						input.focus();
						_this10.changeShow(title, true);
						return false;
					}
					epos.x -= 50;
				}
			}

			//如果点击的是可搜索的input
			if (othis.is('.' + INPUT)) {
				_this10.changeShow(title, true);
				return false;
			}
			//如果点击的是x按钮
			if (othis.is('i[fsw="' + NAME + '"]')) {
				var val = {
					name: othis.prev().text(),
					val: othis.parent().attr("value")
				},
				    dd = dl.find('dd[lay-value=\'' + val.val + '\']');
				if (dd.hasClass(DISABLED)) {
					//如果是disabled状态, 不可选, 不可删
					return false;
				}
				_this10.handlerLabel(id, dd, false, val);
				return false;
			}

			_this10.changeShow(title, !title.parents('.' + FORM_SELECT).hasClass(FORM_SELECTED));
			return false;
		});
		$(target ? target : document).find('dl.' + DL).off('click').on('click', function (e) {
			var othis = $(e.target);
			if (othis.is('.' + LINKAGE) || othis.parents('.' + LINKAGE)[0]) {
				//linkage的处理
				othis = othis.is('li') ? othis : othis.parents('li');
				var _group = othis.parents('.xm-select-linkage-group'),
				    _id = othis.parents('dl').attr('xid');
				//激活li
				_group.find('.xm-select-active').removeClass('xm-select-active');
				othis.addClass('xm-select-active');
				//激活下一个group, 激活前显示对应数据
				_group.nextAll('.xm-select-linkage-group').addClass('xm-select-linkage-hide');
				var nextGroup = _group.next('.xm-select-linkage-group');
				nextGroup.find('li').addClass('xm-select-linkage-hide');
				nextGroup.find('li[pid="' + othis.attr('value') + '"]').removeClass('xm-select-linkage-hide');
				//如果没有下一个group, 或没有对应的值
				if (!nextGroup[0] || nextGroup.find('li:not(.xm-select-linkage-hide)').length == 0) {
					var vals = [],
					    index = 0,
					    _isAdd = !othis.hasClass('xm-select-this');
					if (data[_id].config.radio) {
						othis.parents('.xm-select-linkage').find('.xm-select-this').removeClass('xm-select-this');
					}
					do {
						vals[index++] = {
							name: othis.find('span').text(),
							val: othis.attr('value')
						};
						othis = othis.parents('.xm-select-linkage-group').prev().find('li[value="' + othis.attr('pid') + '"]');
					} while (othis.length);
					vals.reverse();
					var val = {
						name: vals.map(function (item) {
							return item.name;
						}).join('/'),
						val: vals.map(function (item) {
							return item.val;
						}).join('/')
					};
					_this10.handlerLabel(_id, null, _isAdd, val);
				} else {
					nextGroup.removeClass('xm-select-linkage-hide');
				}
				return false;
			}

			if (othis.is('dl')) {
				return false;
			}
			if (othis.is('dt')) {
				othis.nextUntil('dt').each(function (index, item) {
					item = $(item);
					if (item.hasClass(DISABLED) || item.hasClass(THIS)) {} else {
						item.click();
					}
				});
				return false;
			}
			var dd = othis.is('dd') ? othis : othis.parents('dd');
			var id = dd.parent('dl').attr('xid');
			if (dd.hasClass(DISABLED)) {
				//被禁用选项的处理
				return false;
			}
			if (dd.hasClass(FORM_SELECT_TIPS)) {
				//tips的处理
				var btn = othis.is('.' + CZ) ? othis : othis.parents('.' + CZ);
				if (!btn[0]) {
					return false;
				}
				var method = btn.attr('method');
				var obj = data[id].config.btns.filter(function (bean) {
					return bean.name == method;
				})[0];
				obj && obj.click && obj.click instanceof Function && obj.click(id, _this10);
				return false;
			}
			var isAdd = !dd.hasClass(THIS);
			_this10.handlerLabel(id, dd, isAdd);
			return false;
		});
	};

	Common.prototype.linkageAdd = function (id, val) {
		var dl = $('dl[xid="' + id + '"]');
		dl.find('.xm-select-active').removeClass('xm-select-active');
		var vs = val.val.split('/');
		var pid = void 0,
		    li = void 0,
		    index = 0;
		var lis = [];
		do {
			pid = vs[index];
			li = dl.find('.xm-select-linkage-group' + (index + 1) + ' li[value="' + pid + '"]');
			li[0] && lis.push(li);
			index++;
		} while (li.length && pid != undefined);
		if (lis.length == vs.length) {
			$.each(lis, function (idx, item) {
				item.addClass('xm-select-this');
			});
		}
	};

	Common.prototype.linkageDel = function (id, val) {
		var dl = $('dl[xid="' + id + '"]');
		var vs = val.val.split('/');
		var pid = void 0,
		    li = void 0,
		    index = vs.length - 1;
		do {
			pid = vs[index];
			li = dl.find('.xm-select-linkage-group' + (index + 1) + ' li[value="' + pid + '"]');
			if (!li.parent().next().find('li[pid=' + pid + '].xm-select-this').length) {
				li.removeClass('xm-select-this');
			}
			index--;
		} while (li.length && pid != undefined);
	};

	Common.prototype.valToName = function (id, val) {
		var dl = $('dl[xid="' + id + '"]');
		var vs = (val + "").split('/');
		var names = [];
		$.each(vs, function (idx, item) {
			var name = dl.find('.xm-select-linkage-group' + (idx + 1) + ' li[value="' + item + '"] span').text();
			names.push(name);
		});
		return names.length == vs.length ? names.join('/') : null;
	};

	Common.prototype.commonHanler = function (key, label) {
		//计算input的提示语
		this.changePlaceHolder(label);
		//计算高度
		this.retop(label.parents('.' + FORM_SELECT));
		this.checkHideSpan(key, label);
		this.calcLabelLeft(label, 0, true);
		//表单默认值
		label.parents('.' + PNAME).find('.' + HIDE_INPUT).val(data[key].values.map(function (val) {
			return val.val;
		}).join(','));
		//title值
		label.parents('.' + FORM_TITLE + ' .' + NAME).attr('title', data[key].values.map(function (val) {
			return val.name;
		}).join(','));
	};

	Common.prototype.initVal = function (id) {
		var _this11 = this;

		var target = {};
		if (id) {
			target[id] = data[id];
		} else {
			target = data;
		}
		$.each(target, function (key, val) {
			var values = val.values,
			    div = $('dl[xid="' + key + '"]').parent(),
			    label = div.find('.' + LABEL),
			    dl = div.find('dl');
			dl.find('dd.' + THIS).removeClass(THIS);

			var _vals = values.concat([]);
			_vals.concat([]).forEach(function (item, index) {
				_this11.addLabel(key, label, item);
				dl.find('dd[lay-value="' + item.val + '"]').addClass(THIS);
			});
			if (val.config.radio) {
				_vals.length && values.push(_vals[_vals.length - 1]);
			}
			_this11.commonHanler(key, label);
		});
	};

	Common.prototype.handlerLabel = function (id, dd, isAdd, oval, notOn) {
		var div = $('[xid="' + id + '"]').prev().find('.' + LABEL),
		    val = dd && {
			name: dd.find('span').attr('name'),
			val: dd.attr('lay-value')
		},
		    vals = data[id].values,
		    on = data[id].config.on || events.on[id];
		if (oval) {
			val = oval;
		}
		var fs = data[id];
		if (isAdd && fs.config.max && fs.values.length >= fs.config.max) {
			var maxTipsFun = data[id].config.maxTips || events.maxTips[id];
			maxTipsFun && maxTipsFun(id, vals.concat([]), val, fs.max);
			return;
		}
		if (!notOn) {
			if (on && on instanceof Function && on(id, vals.concat([]), val, isAdd, dd && dd.hasClass(DISABLED)) == false) {
				return;
			}
		}
		var dl = $('dl[xid="' + id + '"]');
		isAdd ? (dd && dd[0] ? (dd.addClass(THIS), dd.removeClass(TEMP)) : dl.find('.xm-select-linkage')[0] && this.linkageAdd(id, val), this.addLabel(id, div, val), vals.push(val)) : (dd && dd[0] ? dd.removeClass(THIS) : dl.find('.xm-select-linkage')[0] && this.linkageDel(id, val), this.delLabel(id, div, val), this.remove(vals, val));
		if (!div[0]) return;
		//单选选完后直接关闭选择域
		if (fs.config.radio) {
			this.changeShow(div, false);
		}
		//移除表单验证的红色边框
		div.parents('.' + FORM_TITLE).prev().removeClass('layui-form-danger');

		//清空搜索值
		fs.config.clearInput && this.clearInput(id);

		this.commonHanler(id, div);
	};

	Common.prototype.addLabel = function (id, div, val) {
		if (!val) return;
		var tips = 'fsw="' + NAME + '"';
		var _ref = [$('<span ' + tips + ' value="' + val.val + '"><font ' + tips + '>' + val.name + '</font></span>'), $('<i ' + tips + ' class="xm-icon-close">\xD7</i>')],
		    $label = _ref[0],
		    $close = _ref[1];

		$label.append($close);
		//如果是radio模式
		var fs = data[id];
		if (fs.config.radio) {
			fs.values.length = 0;
			$('dl[xid="' + id + '"]').find('dd.' + THIS + ':not([lay-value="' + val.val + '"])').removeClass(THIS);
			div.find('span').remove();
		}
		//如果是固定高度
		div.find('input').css('width', '50px');
		div.find('input').before($label);
	};

	Common.prototype.delLabel = function (id, div, val) {
		if (!val) return;
		div.find('span[value="' + val.val + '"]:first').remove();
	};

	Common.prototype.checkHideSpan = function (id, div) {
		var parentHeight = div.parents('.' + NAME)[0].offsetHeight + 5;
		div.find('span.xm-span-hide').removeClass('xm-span-hide');
		div.find('span[style]').remove();

		var count = data[id].config.showCount;
		div.find('span').each(function (index, item) {
			if (index >= count) {
				$(item).addClass('xm-span-hide');
			}
		});

		var prefix = div.find('span:eq(' + count + ')');
		prefix[0] && prefix.before($('<span style="padding-right: 6px;" fsw="' + NAME + '"> + ' + (div.find('span').length - count) + '</span>'));
	};

	Common.prototype.retop = function (div) {
		//计算dl显示的位置
		var dl = div.find('dl'),
		    top = div.offset().top + div.outerHeight() + 5 - $win.scrollTop(),
		    dlHeight = dl.outerHeight();
		var up = div.hasClass('layui-form-selectup') || dl.css('top').indexOf('-') != -1 || top + dlHeight > $win.height() && top >= dlHeight;
		div = div.find('.' + NAME);

		var fs = data[dl.attr('xid')];
		var base = dl.parents('.layui-form-pane')[0] && dl.prev()[0].clientHeight > 38 ? 14 : 10;
		if (fs) {
			if (fs.config.direction == 'up') {
				dl.css({
					top: 'auto',
					bottom: '42px'
				});
				return;
			}
			if (fs.direction == 'down') {
				dl.css({
					top: div[0].offsetTop + div.height() + base + 'px',
					bottom: 'auto'
				});
				return;
			}
		}

		if (up) {
			dl.css({
				top: 'auto',
				bottom: '42px'
			});
		} else {
			dl.css({
				top: div[0].offsetTop + div.height() + base + 'px',
				bottom: 'auto'
			});
		}
	};

	Common.prototype.changeShow = function (children, isShow) {
		//显示于隐藏
		$('.layui-form-selected').removeClass('layui-form-selected');
		var top = children.parents('.' + FORM_SELECT);
		$('.' + PNAME + ' .' + FORM_SELECT).not(top).removeClass(FORM_SELECTED);
		if (isShow) {
			this.retop(top);
			top.addClass(FORM_SELECTED);
			top.find('.' + INPUT).focus();
			if (!top.find('dl dd[lay-value]:not(.' + FORM_SELECT_TIPS + ')').length) {
				top.find('dl .' + FORM_NONE).addClass(FORM_EMPTY);
			}
		} else {
			var id = top.find('dl').attr('xid');
			top.removeClass(FORM_SELECTED);
			this.clearInput(id);
			top.find('dl .' + FORM_EMPTY).removeClass(FORM_EMPTY);
			top.find('dl dd.' + DD_HIDE).removeClass(DD_HIDE);
			top.find('dl dd.' + TEMP).remove();
			//计算ajax数据是否为空, 然后重新请求数据
			if (id && data[id] && data[id].config.isEmpty) {
				this.triggerSearch(top);
			}
			this.changePlaceHolder(top.find('.' + LABEL));
		}
	};

	Common.prototype.changePlaceHolder = function (div) {
		//显示于隐藏提示语
		//调整pane模式下的高度
		var title = div.parents('.' + FORM_TITLE);
		title[0] || (title = div.parents('dl').prev());
		if (!title[0]) {
			return;
		}

		var id = div.parents('.' + PNAME).find('dl[xid]').attr('xid');
		if (data[id] && data[id].config.height) {//既然固定高度了, 那就看着办吧

		} else {
			var height = title.find('.' + NAME)[0].clientHeight;
			title.css('height', (height > 34 ? height + 4 : height) + 'px');
			//如果是layui pane模式, 处理label的高度
			var label = title.parents('.' + PNAME).parent().prev();
			if (label.is('.layui-form-label') && title.parents('.layui-form-pane')[0]) {
				height = height > 36 ? height + 4 : height;
				title.css('height', height + 'px');
				label.css({
					height: height + 2 + 'px',
					lineHeight: height - 18 + 'px'
				});
			}
		}

		var input = title.find('.' + TDIV + ' input'),
		    isShow = !div.find('span:last')[0] && !title.find('.' + INPUT).val();
		if (isShow) {
			var ph = input.attr('back');
			input.removeAttr('back');
			input.attr('placeholder', ph);
		} else {
			var _ph = input.attr('placeholder');
			input.removeAttr('placeholder');
			input.attr('back', _ph);
		}
	};

	Common.prototype.indexOf = function (arr, val) {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i].val == val || arr[i].val == (val ? val.val : val) || arr[i] == val || JSON.stringify(arr[i]) == JSON.stringify(val)) {
				return i;
			}
		}
		return -1;
	};

	Common.prototype.remove = function (arr, val) {
		var idx = this.indexOf(arr, val ? val.val : val);
		if (idx > -1) {
			arr.splice(idx, 1);
			return true;
		}
		return false;
	};

	Common.prototype.selectAll = function (id, isOn, skipDis) {
		var _this12 = this;

		var dl = $('[xid="' + id + '"]');
		if (!dl[0]) {
			return;
		}
		if (dl.find('.xm-select-linkage')[0]) {
			return;
		}
		dl.find('dd[lay-value]:not(.' + FORM_SELECT_TIPS + '):not(.' + THIS + ')' + (skipDis ? ':not(.' + DISABLED + ')' : '')).each(function (index, item) {
			item = $(item);
			var val = {
				name: item.find('span').attr('name'),
				val: item.attr('lay-value')
			};
			_this12.handlerLabel(id, dl.find('dd[lay-value="' + val.val + '"]'), true, val, !isOn);
		});
	};

	Common.prototype.removeAll = function (id, isOn, skipDis) {
		var _this13 = this;

		var dl = $('[xid="' + id + '"]');
		if (!dl[0]) {
			return;
		}
		if (dl.find('.xm-select-linkage')[0]) {
			//针对多级联动的处理
			data[id].values.concat([]).forEach(function (item, idx) {
				var vs = item.val.split('/');
				var pid = void 0,
				    li = void 0,
				    index = 0;
				do {
					pid = vs[index++];
					li = dl.find('.xm-select-linkage-group' + index + ':not(.xm-select-linkage-hide) li[value="' + pid + '"]');
					li.click();
				} while (li.length && pid != undefined);
			});
			return;
		}
		data[id].values.concat([]).forEach(function (item, index) {
			if (skipDis && dl.find('dd[lay-value="' + item.val + '"]').hasClass(DISABLED)) {} else {
				_this13.handlerLabel(id, dl.find('dd[lay-value="' + item.val + '"]'), false, item, !isOn);
			}
		});
	};

	Common.prototype.reverse = function (id, isOn, skipDis) {
		var _this14 = this;

		var dl = $('[xid="' + id + '"]');
		if (!dl[0]) {
			return;
		}
		if (dl.find('.xm-select-linkage')[0]) {
			return;
		}
		dl.find('dd[lay-value]:not(.' + FORM_SELECT_TIPS + ')' + (skipDis ? ':not(.' + DISABLED + ')' : '')).each(function (index, item) {
			item = $(item);
			var val = {
				name: item.find('span').attr('name'),
				val: item.attr('lay-value')
			};
			_this14.handlerLabel(id, dl.find('dd[lay-value="' + val.val + '"]'), !item.hasClass(THIS), val, !isOn);
		});
	};

	Common.prototype.skin = function (id) {
		var skins = ['default', 'primary', 'normal', 'warm', 'danger'];
		var skin = skins[Math.floor(Math.random() * skins.length)];
		$('dl[xid="' + id + '"]').parents('.' + PNAME).find('.' + FORM_SELECT).attr('xm-select-skin', skin);
		this.check(id) && this.commonHanler(id, $('dl[xid="' + id + '"]').parents('.' + PNAME).find('.' + LABEL));
	};

	Common.prototype.getPosition = function (e) {
		var x = 0,
		    y = 0;
		while (e != null) {
			x += e.offsetLeft;
			y += e.offsetTop;
			e = e.offsetParent;
		}
		return { x: x, y: y };
	};

	Common.prototype.onreset = function () {
		//监听reset按钮, 然后重置多选
		$(document).on('click', '[type=reset]', function (e) {
			$(e.target).parents('form').find('.' + PNAME + ' dl[xid]').each(function (index, item) {
				var id = item.getAttribute('xid'),
				    dl = $(item),
				    dd = void 0,
				    temp = {};
				common.removeAll(id);
				data[id].config.init.forEach(function (val, idx) {
					if (val && (!temp[val] || data[id].config.repeat) && (dd = dl.find('dd[lay-value="' + val.val + '"]'))[0]) {
						common.handlerLabel(id, dd, true);
						temp[val] = 1;
					}
				});
			});
		});
	};

	Common.prototype.bindEvent = function (name, id, fun) {
		if (id && id instanceof Function) {
			fun = id;
			id = null;
		}
		if (fun && fun instanceof Function) {
			if (!id) {
				$.each(data, function (id, val) {
					data[id] ? data[id].config[name] = fun : events[name][id] = fun;
				});
			} else {
				data[id] ? data[id].config[name] = fun : events[name][id] = fun;
			}
		}
	};

	Common.prototype.check = function (id) {
		if ($('dl[xid="' + id + '"]').length) {
			return true;
		} else {
			delete data[id];
			return false;
		}
	};

	Common.prototype.render = function (id, select) {
		if (this.check(id)) {
			select = data[id].select;
		}
		common.init(select);
		common.one($('dl[xid="' + id + '"]').parents('.' + PNAME));
		common.initVal(id);
	};

	var Select4 = function Select4() {
		this.v = v;
	};
	var common = new Common();

	Select4.prototype.value = function (id, type, isAppend) {
		if (typeof id != 'string') {
			return [];
		}
		var fs = data[id];
		if (!common.check(id)) {
			return [];
		}
		if (typeof type == 'string' || type == undefined) {
			var arr = fs.values.concat([]) || [];
			if (type == 'val') {
				return arr.map(function (val) {
					return val.val;
				});
			}
			if (type == 'valStr') {
				return arr.map(function (val) {
					return val.val;
				}).join(',');
			}
			if (type == 'name') {
				return arr.map(function (val) {
					return val.name;
				});
			}
			if (type == 'nameStr') {
				return arr.map(function (val) {
					return val.name;
				}).join(',');
			}
			return arr;
		}
		if (common.isArray(type)) {
			var dl = $('[xid="' + id + '"]'),
			    temp = {},
			    dd = void 0,
			    isAdd = true;
			if (isAppend == false) {
				//删除传入的数组
				isAdd = false;
			} else if (isAppend == true) {
				//追加模式
				isAdd = true;
			} else {
				//删除原有的数据
				common.removeAll(id);
			}
			if (isAdd) {
				fs.values.forEach(function (val, index) {
					temp[val.val] = 1;
				});
			}
			type.forEach(function (val, index) {
				if (val && (!temp[val] || fs.config.repeat)) {
					if ((dd = dl.find('dd[lay-value="' + val + '"]'))[0]) {
						common.handlerLabel(id, dd, isAdd, null, true);
						temp[val] = 1;
					} else {
						var _name = common.valToName(id, val);
						if (_name) {
							common.handlerLabel(id, dd, isAdd, {
								name: _name,
								val: val
							}, true);
							temp[val] = 1;
						}
					}
				}
			});
		}
	};

	Select4.prototype.on = function (id, fun) {
		common.bindEvent('on', id, fun);
		return this;
	};

	Select4.prototype.filter = function (id, fun) {
		common.bindEvent('filter', id, fun);
		return this;
	};

	Select4.prototype.maxTips = function (id, fun) {
		common.bindEvent('maxTips', id, fun);
		return this;
	};

	Select4.prototype.config = function (id, config, isJson) {
		if (id && (typeof id === 'undefined' ? 'undefined' : _typeof(id)) == 'object') {
			isJson = config == true;
			config = id;
			id = null;
		}
		if (config && (typeof config === 'undefined' ? 'undefined' : _typeof(config)) == 'object') {
			if (isJson) {
				config.header || (config.header = {});
				config.header['Content-Type'] = 'application/json; charset=UTF-8';
				config.dataType = 'json';
			}
			id ? (ajaxs[id] = $.extend(true, {}, ajaxs[id] || ajax, config), !common.check(id) && this.render(id), data[id] && (data[id].config.direction = config.direction), config.searchUrl && data[id] && common.triggerSearch($('.' + PNAME + ' dl[xid="' + id + '"]').parents('.' + FORM_SELECT), true)) : ($.extend(true, ajax, config), $.each(ajaxs, function (key, item) {
				$.extend(true, item, config);
			}));
		}
		return this;
	};

	Select4.prototype.render = function (id, options) {
		var _this15 = this;

		if (id && (typeof id === 'undefined' ? 'undefined' : _typeof(id)) == 'object') {
			options = id;
			id = null;
		}
		var target = {};
		id ? common.check(id) && (target[id] = data[id]) : target = data;

		var config = options ? {
			init: options.init,
			disabled: options.disabled,
			max: options.max,
			isSearch: options.isSearch,
			isCreate: options.isCreate,
			radio: options.radio,
			skin: options.skin,
			direction: options.direction,
			height: options.height,
			formname: options.formname,
			layverify: options.layverify,
			layverType: options.layverType,
			searchType: options.searchType,
			showCount: options.showCount,
			placeholder: options.placeholder,
			create: options.create,
			filter: options.filter,
			maxTips: options.maxTips,
			on: options.on,
			template: options.template
		} : {};

		if (Object.getOwnPropertyNames(target).length) {
			$.each(target, function (key, val) {
				//恢复初始值
				if (common.check(key)) {
					_this15.value(key, []);
					$.extend(data[key].config, config);
					common.render(key, val.select);
				}
			});
		}
		($('select[' + NAME + '="' + id + '"]')[0] ? $('select[' + NAME + '="' + id + '"]') : $('select[' + NAME + ']')).each(function (index, select) {
			var sid = select.getAttribute(NAME);
			common.render(sid, select);
		});
		return this;
	};

	Select4.prototype.disabled = function (id) {
		var target = {};
		id ? common.check(id) && (target[id] = data[id]) : target = {};

		$.each(target, function (key, val) {
			$('dl[xid="' + key + '"]').prev().addClass(DIS);
		});
		return this;
	};

	Select4.prototype.undisabled = function (id) {
		var target = {};
		id ? common.check(id) && (target[id] = data[id]) : target = {};

		$.each(target, function (key, val) {
			$('dl[xid="' + key + '"]').prev().removeClass(DIS);
		});
		return this;
	};

	Select4.prototype.data = function (id, type, config) {
		if (!id || !type || !config) {
			return this;
		}
		!common.check(id) && this.render(id);
		this.value(id, []);
		this.config(id, config);
		if (type == 'local') {
			common.renderData(id, config.arr, config.linkage == true, config.linkageWidth ? config.linkageWidth : '100');
		} else if (type == 'server') {
			common.ajax(id, config.url, config.keyword, config.linkage == true, config.linkageWidth ? config.linkageWidth : '100');
		}
		return this;
	};

	Select4.prototype.btns = function (id, btns, config) {
		if (!btns || !common.isArray(btns)) {
			return this;
		};
		var target = {};
		id ? common.check(id) && (target[id] = data[id]) : target = {};

		btns = btns.map(function (obj) {
			if (typeof obj == 'string') {
				if (obj == 'select') {
					return quickBtns[0];
				}
				if (obj == 'remove') {
					return quickBtns[1];
				}
				if (obj == 'reverse') {
					return quickBtns[2];
				}
				if (obj == 'skin') {
					return quickBtns[3];
				}
			}
			return obj;
		});

		$.each(target, function (key, val) {
			val.config.btns = btns;
			var dd = $('dl[xid="' + key + '"]').find('.' + FORM_SELECT_TIPS + ':first');
			if (btns.length) {
				var show = config && config.show && (config.show == 'name' || config.show == 'icon') ? config.show : '';
				var html = common.renderBtns(key, show, config && config.space ? config.space : '30px');
				dd.html(html);
			} else {
				var pcInput = dd.parents('.' + FORM_SELECT).find('.' + TDIV + ' input');
				var _html = pcInput.attr('placeholder') || pcInput.attr('back');
				dd.html(_html);
				dd.removeAttr('style');
			}
		});

		return this;
	};

	Select4.prototype.search = function (id, val) {
		if (id && common.check(id)) {
			ajaxs[id] = $.extend(true, {}, ajaxs[id] || ajax, {
				first: true,
				searchVal: val
			});
			common.triggerSearch($('dl[xid="' + id + '"]').parents('.' + FORM_SELECT), true);
		}
		return this;
	};

	return new Select4();
});