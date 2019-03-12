//>>built
require({cache:{"url:dojox/calendar/templates/SimpleColumnView.html":'\x3cdiv data-dojo-attach-events\x3d"keydown:_onKeyDown"\x3e\t\n\t\x3cdiv data-dojo-attach-point\x3d"header" class\x3d"dojoxCalendarHeader"\x3e\n\t\t\x3cdiv class\x3d"dojoxCalendarYearColumnHeader" data-dojo-attach-point\x3d"yearColumnHeader"\x3e\n\t\t\t\x3ctable\x3e\x3ctr\x3e\x3ctd\x3e\x3cspan data-dojo-attach-point\x3d"yearColumnHeaderContent"\x3e\x3c/span\x3e\x3c/td\x3e\x3c/tr\x3e\x3c/table\x3e\t\t\n\t\t\x3c/div\x3e\n\t\t\x3cdiv data-dojo-attach-point\x3d"columnHeader" class\x3d"dojoxCalendarColumnHeader"\x3e\n\t\t\t\x3ctable data-dojo-attach-point\x3d"columnHeaderTable" class\x3d"dojoxCalendarColumnHeaderTable" cellpadding\x3d"0" cellspacing\x3d"0"\x3e\x3c/table\x3e\n\t\t\x3c/div\x3e\n\t\x3c/div\x3e\t\n\t\x3cdiv data-dojo-attach-point\x3d"vScrollBar" class\x3d"dojoxCalendarVScrollBar"\x3e\n\t\t\x3cdiv data-dojo-attach-point\x3d"vScrollBarContent" style\x3d"visibility:hidden;position:relative; width:1px; height:1px;" \x3e\x3c/div\x3e\n\t\x3c/div\x3e\t\n\t\x3cdiv data-dojo-attach-point\x3d"scrollContainer" class\x3d"dojoxCalendarScrollContainer"\x3e\n\t\t\x3cdiv data-dojo-attach-point\x3d"sheetContainer" style\x3d"position:relative;left:0;right:0;margin:0;padding:0"\x3e\n\t\t\t\x3cdiv data-dojo-attach-point\x3d"rowHeader" class\x3d"dojoxCalendarRowHeader"\x3e\n\t\t\t\t\x3ctable data-dojo-attach-point\x3d"rowHeaderTable" class\x3d"dojoxCalendarRowHeaderTable" cellpadding\x3d"0" cellspacing\x3d"0"\x3e\x3c/table\x3e\n\t\t\t\x3c/div\x3e\n\t\t\t\x3cdiv data-dojo-attach-point\x3d"grid" class\x3d"dojoxCalendarGrid"\x3e\n\t\t\t\t\x3ctable data-dojo-attach-point\x3d"gridTable" class\x3d"dojoxCalendarGridTable" cellpadding\x3d"0" cellspacing\x3d"0" style\x3d"width:100%"\x3e\x3c/table\x3e\n\t\t\t\x3c/div\x3e\n\t\t\t\x3cdiv data-dojo-attach-point\x3d"itemContainer" class\x3d"dojoxCalendarContainer" data-dojo-attach-event\x3d"mousedown:_onGridMouseDown,mouseup:_onGridMouseUp,ondblclick:_onGridDoubleClick,touchstart:_onGridTouchStart,touchmove:_onGridTouchMove,touchend:_onGridTouchEnd"\x3e\n\t\t\t\t\x3ctable data-dojo-attach-point\x3d"itemContainerTable" class\x3d"dojoxCalendarContainerTable" cellpadding\x3d"0" cellspacing\x3d"0" style\x3d"width:100%"\x3e\x3c/table\x3e\n\t\t\t\x3c/div\x3e\n\t\t\x3c/div\x3e \n\t\x3c/div\x3e\n\x3c/div\x3e\n\n'}});
define("dojox/calendar/SimpleColumnView","./ViewBase dijit/_TemplatedMixin ./_VerticalScrollBarBase dojo/text!./templates/SimpleColumnView.html dojo/_base/declare dojo/_base/event dojo/_base/lang dojo/_base/array dojo/_base/sniff dojo/_base/fx dojo/_base/html dojo/on dojo/dom dojo/dom-class dojo/dom-style dojo/dom-geometry dojo/dom-construct dojo/mouse dojo/query dojox/html/metrics".split(" "),function(y,z,A,B,C,q,r,H,v,D,E,s,I,k,n,w,p,F,m,G){return C("dojox.calendar.SimpleColumnView",[y,z],{baseClass:"dojoxCalendarSimpleColumnView",
templateString:B,viewKind:"columns",_setTabIndexAttr:"domNode",renderData:null,startDate:null,columnCount:7,minHours:8,maxHours:18,hourSize:100,timeSlotDuration:15,rowHeaderGridSlotDuration:60,rowHeaderLabelSlotDuration:60,rowHeaderLabelOffset:2,rowHeaderFirstLabelOffset:2,verticalRenderer:null,percentOverlap:70,horizontalGap:4,_columnHeaderHandlers:null,constructor:function(){this.invalidatingProperties="columnCount startDate minHours maxHours hourSize verticalRenderer rowHeaderTimePattern columnHeaderDatePattern timeSlotDuration rowHeaderGridSlotDuration rowHeaderLabelSlotDuration rowHeaderLabelOffset rowHeaderFirstLabelOffset percentOverlap horizontalGap scrollBarRTLPosition itemToRendererKindFunc layoutPriorityFunction formatItemTimeFunc textDir items".split(" ");
this._columnHeaderHandlers=[]},destroy:function(a){this._cleanupColumnHeader();this.scrollBar&&this.scrollBar.destroy(a);this.inherited(arguments)},_scrollBar_onScroll:function(a){this._setScrollPosition(a)},buildRendering:function(){this.inherited(arguments);this.vScrollBar&&(this.scrollBar=new A({content:this.vScrollBarContent},this.vScrollBar),this.scrollBar.on("scroll",r.hitch(this,this._scrollBar_onScroll)),this._viewHandles.push(s(this.scrollContainer,F.wheel,dojo.hitch(this,this._mouseWheelScrollHander))))},
postscript:function(){this.inherited(arguments);this._initialized=!0;this.invalidRendering||this.refreshRendering()},_setVerticalRendererAttr:function(a){this._destroyRenderersByKind("vertical");this._set("verticalRenderer",a)},_createRenderData:function(){var a={};a.minHours=this.get("minHours");a.maxHours=this.get("maxHours");a.hourSize=this.get("hourSize");a.hourCount=a.maxHours-a.minHours;a.slotDuration=this.get("timeSlotDuration");a.rowHeaderGridSlotDuration=this.get("rowHeaderGridSlotDuration");
a.slotSize=Math.ceil(a.hourSize/(60/a.slotDuration));a.hourSize=a.slotSize*(60/a.slotDuration);a.sheetHeight=a.hourSize*a.hourCount;a.scrollbarWidth=G.getScrollbar().w+1;a.dateLocaleModule=this.dateLocaleModule;a.dateClassObj=this.dateClassObj;a.dateModule=this.dateModule;a.dates=[];a.columnCount=this.get("columnCount");var b=this.get("startDate");null==b&&(b=new a.dateClassObj);this.startDate=b=this.floorToDay(b,!1,a);for(var c=0;c<a.columnCount;c++)a.dates.push(b),b=a.dateModule.add(b,"day",1),
b=this.floorToDay(b,!1,a);a.startTime=new a.dateClassObj(a.dates[0]);a.startTime.setHours(a.minHours);a.endTime=new a.dateClassObj(a.dates[a.columnCount-1]);a.endTime.setHours(a.maxHours);this.displayedItemsInvalidated?(this.displayedItemsInvalidated=!1,this._computeVisibleItems(a),this._isEditing&&this._endItemEditing(null,!1)):this.renderData&&(a.items=this.renderData.items);return a},_validateProperties:function(){this.inherited(arguments);var a=this.minHours;if(0>a||23<a||isNaN(a))this.minHours=
0;a=this.maxHours;if(1>a||24<a||isNaN(a))this.minHours=24;this.minHours>this.maxHours&&(a=this.maxHours,this.maxHours=this.minHours,this.minHours=a);1>this.maxHours-this.minHours&&(this.minHours=0,this.maxHours=24);if(1>this.columnCount||isNaN(this.columnCount))this.columnCount=1;a=this.percentOverlap;if(0>a||100<a||isNaN(a))this.percentOverlap=70;if(5>this.hourSize||isNaN(this.hourSize))this.hourSize=10;a=this.timeSlotDuration;if(1>a||60<a||isNaN(a))this.timeSlotDuration=15},_setStartDateAttr:function(a){this.displayedItemsInvalidated=
!0;this._set("startDate",a)},_setColumnCountAttr:function(a){this.displayedItemsInvalidated=!0;this._set("columnCount",a)},__fixEvt:function(a){a.sheet="primary";a.source=this;return a},_formatRowHeaderLabel:function(a){return this.renderData.dateLocaleModule.format(a,{selector:"time",timePattern:this.rowHeaderTimePattern})},_formatColumnHeaderLabel:function(a){return this.renderData.dateLocaleModule.format(a,{selector:"date",datePattern:this.columnHeaderDatePattern,formatLength:"medium"})},startTimeOfDay:null,
scrollBarRTLPosition:"left",_getStartTimeOfDay:function(){var a=(this.get("maxHours")-this.get("minHours"))*this._getScrollPosition()/this.renderData.sheetHeight;return{hours:this.renderData.minHours+Math.floor(a),minutes:60*(a-Math.floor(a))}},_getEndTimeOfDay:function(){var a=(this.get("maxHours")-this.get("minHours"))*(this._getScrollPosition()+this.scrollContainer.offsetHeight)/this.renderData.sheetHeight;return{hours:this.renderData.minHours+Math.floor(a),minutes:60*(a-Math.floor(a))}},_setStartTimeOfDayAttr:function(a){this._setStartTimeOfDay(a.hours,
a.minutes,a.duration,a.easing)},_getStartTimeOfDayAttr:function(){return this._getStartTimeOfDay()},_setStartTimeOfDay:function(a,b,c,d){var e=this.renderData;a=a||e.minHours;b=b||0;c=c||0;0>b?b=0:59<b&&(b=59);0>a?a=0:24<a&&(a=24);a=60*a+b;b=60*e.minHours;var h=60*e.maxHours;a<b?a=b:a>h&&(a=h);a=(a-b)*e.sheetHeight/(h-b);a=Math.min(e.sheetHeight-this.scrollContainer.offsetHeight,a);this._scrollToPosition(a,c,d)},_scrollToPosition:function(a,b,c){if(b){this._scrollAnimation&&this._scrollAnimation.stop();
var d=this._getScrollPosition();b=Math.abs((a-d)*b/this.renderData.sheetHeight);this._scrollAnimation=new D.Animation({curve:[d,a],duration:b,easing:c,onAnimate:r.hitch(this,function(a){this._setScrollImpl(a)})});this._scrollAnimation.play()}else this._setScrollImpl(a)},_setScrollImpl:function(a){this._setScrollPosition(a);this.scrollBar&&this.scrollBar.set("value",a)},ensureVisibility:function(a,b,c,d,e){d=void 0==d?this.renderData.slotDuration:d;if(this.scrollable&&this.autoScroll){a=60*a.getHours()+
a.getMinutes()-d;b=60*b.getHours()+b.getMinutes()+d;d=this._getStartTimeOfDay();var h=this._getEndTimeOfDay();d=60*d.hours+d.minutes;var h=60*h.hours+h.minutes,f=!1,g=null;switch(c){case "start":f=a>=d&&a<=h;g=a;break;case "end":f=b>=d&&b<=h;g=b-(h-d);break;case "both":f=a>=d&&b<=h,g=a}f||this._setStartTimeOfDay(Math.floor(g/60),g%60,e)}},scrollView:function(a){var b=this._getStartTimeOfDay(),b=60*b.hours+b.minutes+a*this.timeSlotDuration;this._setStartTimeOfDay(Math.floor(b/60),b%60)},_mouseWheelScrollHander:function(a){this.scrollView(0<
a.wheelDelta?-1:1)},refreshRendering:function(){if(this._initialized){this._validateProperties();var a=this.renderData,b=this._createRenderData();this.renderData=b;this._createRendering(b,a);this._layoutRenderers(b)}},_createRendering:function(a,b){n.set(this.sheetContainer,"height",a.sheetHeight+"px");this._configureScrollBar(a);this._buildColumnHeader(a,b);this._buildRowHeader(a,b);this._buildGrid(a,b);this._buildItemContainer(a,b)},_configureScrollBar:function(a){v("ie")&&this.scrollBar&&n.set(this.scrollBar.domNode,
"width",a.scrollbarWidth+1+"px");var b=this.isLeftToRight()?!0:"right"==this.scrollBarRTLPosition,c=b?"right":"left",d=b?"left":"right";this.scrollBar&&(this.scrollBar.set("maximum",a.sheetHeight),n.set(this.scrollBar.domNode,c,0),n.set(this.scrollBar.domNode,b?"left":"right","auto"));n.set(this.scrollContainer,c,a.scrollbarWidth+"px");n.set(this.scrollContainer,d,"0");n.set(this.header,c,a.scrollbarWidth+"px");n.set(this.header,d,"0");this.buttonContainer&&(null!=this.owner&&this.owner.currentView==
this)&&(n.set(this.buttonContainer,c,a.scrollbarWidth+"px"),n.set(this.buttonContainer,d,"0"))},_columnHeaderClick:function(a){q.stop(a);var b=m("td",this.columnHeaderTable).indexOf(a.currentTarget);this._onColumnHeaderClick({index:b,date:this.renderData.dates[b],triggerEvent:a})},_buildColumnHeader:function(a,b){var c=this.columnHeaderTable;if(c){var d=a.columnCount-(b?b.columnCount:0);8==v("ie")&&(null==this._colTableSave?this._colTableSave=r.clone(c):0>d&&(this._cleanupColumnHeader(),this.columnHeader.removeChild(c),
p.destroy(c),this.columnHeaderTable=c=r.clone(this._colTableSave),this.columnHeader.appendChild(c),d=a.columnCount));var e=m("tbody",c),h=m("tr",c),f,e=1==e.length?e[0]:E.create("tbody",null,c),h=1==h.length?h[0]:p.create("tr",null,e);if(0<d)for(e=0;e<d;e++){f=p.create("td",null,h);var g=[];g.push(s(f,"click",r.hitch(this,this._columnHeaderClick)));v("touch")?(g.push(s(f,"touchstart",function(a){q.stop(a);k.add(a.currentTarget,"Active")})),g.push(s(f,"touchend",function(a){q.stop(a);k.remove(a.currentTarget,
"Active")}))):(g.push(s(f,"mousedown",function(a){q.stop(a);k.add(a.currentTarget,"Active")})),g.push(s(f,"mouseup",function(a){q.stop(a);k.remove(a.currentTarget,"Active")})),g.push(s(f,"mouseover",function(a){q.stop(a);k.add(a.currentTarget,"Hover")})),g.push(s(f,"mouseout",function(a){q.stop(a);k.remove(a.currentTarget,"Hover")})));this._columnHeaderHandlers.push(g)}else{d=-d;for(e=0;e<d;e++){f=h.lastChild;h.removeChild(f);p.destroy(f);for(f=this._columnHeaderHandlers.pop();0<f.length;)f.pop().remove()}}m("td",
c).forEach(function(b,c){b.className="";0==c?k.add(b,"first-child"):c==this.renderData.columnCount-1&&k.add(b,"last-child");var d=a.dates[c];this._setText(b,this._formatColumnHeaderLabel(d));this.styleColumnHeaderCell(b,d,a)},this);this.yearColumnHeaderContent&&this._setText(this.yearColumnHeaderContent,a.dateLocaleModule.format(a.dates[0],{selector:"date",datePattern:"yyyy"}))}},_cleanupColumnHeader:function(){for(;0<this._columnHeaderHandlers.length;)for(var a=this._columnHeaderHandlers.pop();0<
a.length;)a.pop().remove()},styleColumnHeaderCell:function(a,b,c){k.add(a,this._cssDays[b.getDay()]);this.isToday(b)?k.add(a,"dojoxCalendarToday"):this.isWeekEnd(b)&&k.add(a,"dojoxCalendarWeekend")},_addMinutesClasses:function(a,b){switch(b){case 0:k.add(a,"hour");break;case 30:k.add(a,"halfhour");break;case 15:case 45:k.add(a,"quarterhour")}},_buildRowHeader:function(a,b){var c=this.rowHeaderTable;if(c){null==this._rowHeaderLabelContainer&&(this._rowHeaderLabelContainer=p.create("div",{"class":"dojoxCalendarRowHeaderLabelContainer"},
this.rowHeader));n.set(c,"height",a.sheetHeight+"px");var d=m("tbody",c),e,h;e=1==d.length?d[0]:p.create("tbody",null,c);d=Math.floor(60/a.rowHeaderGridSlotDuration)*a.hourCount-(b?Math.floor(60/b.rowHeaderGridSlotDuration)*b.hourCount:0);if(0<d)for(var f=0;f<d;f++)h=p.create("tr",null,e),p.create("td",null,h);else{d=-d;for(f=0;f<d;f++)e.removeChild(e.lastChild)}var g=this.renderData,l=Math.ceil(a.hourSize/(60/a.rowHeaderGridSlotDuration)),t=new Date(2E3,0,1,0,0,0);m("tr",c).forEach(function(b,c){var d=
m("td",b)[0];d.className="";n.set(b,"height",7==v("ie")?l-2*(60/a.rowHeaderGridSlotDuration):l+"px");this.styleRowHeaderCell(d,t.getHours(),t.getMinutes(),g);this._addMinutesClasses(d,c*this.renderData.rowHeaderGridSlotDuration%60)},this);c=this._rowHeaderLabelContainer;d=Math.floor(60/this.rowHeaderLabelSlotDuration)*a.hourCount-c.childNodes.length;if(0<d)for(f=0;f<d;f++)e=p.create("span",null,c),k.add(e,"dojoxCalendarRowHeaderLabel");else{d=-d;for(f=0;f<d;f++)c.removeChild(c.lastChild)}l=Math.ceil(a.hourSize/
(60/this.rowHeaderLabelSlotDuration));m("\x3espan",c).forEach(function(b,c){t.setHours(0);t.setMinutes(60*a.minHours+c*this.rowHeaderLabelSlotDuration);this._configureRowHeaderLabel(b,t,c,l*c,g)},this)}},_configureRowHeaderLabel:function(a,b,c,d,e){this._setText(a,this._formatRowHeaderLabel(b));n.set(a,"top",d+(0==c?this.rowHeaderFirstLabelOffset:this.rowHeaderLabelOffset)+"px");b=c*this.rowHeaderLabelSlotDuration%60;k.remove(a,["hour","halfhour","quarterhour"]);this._addMinutesClasses(a,b)},styleRowHeaderCell:function(a,
b,c,d){},_buildGrid:function(a,b){var c=this.gridTable;if(c){n.set(c,"height",a.sheetHeight+"px");var d=Math.floor(60/a.slotDuration)*a.hourCount,e=d-(b?Math.floor(60/b.slotDuration)*b.hourCount:0),h=0<e,f=a.columnCount-(b?b.columnCount:0);8==v("ie")&&(null==this._gridTableSave?this._gridTableSave=r.clone(c):0>f&&(this.grid.removeChild(c),p.destroy(c),this.gridTable=c=r.clone(this._gridTableSave),this.grid.appendChild(c),f=a.columnCount,e=d,h=!0));var g=m("tbody",c),g=1==g.length?g[0]:p.create("tbody",
null,c);if(h)for(var l=0;l<e;l++)p.create("tr",null,g);else{e=-e;for(l=0;l<e;l++)g.removeChild(g.lastChild)}var t=Math.floor(60/a.slotDuration)*a.hourCount-e,x=h||0<f,f=x?f:-f;m("tr",c).forEach(function(b,c){if(x){var d=c>=t?a.columnCount:f;for(c=0;c<d;c++)p.create("td",null,b)}else for(c=0;c<f;c++)b.removeChild(b.lastChild)});m("tr",c).forEach(function(b,c){n.set(b,"height",a.slotSize+"px");0==c?k.add(b,"first-child"):c==d-1&&k.add(b,"last-child");var e=c*this.renderData.slotDuration%60,f=this.minHours+
Math.floor(c*this.renderData.slotDuration/60);m("td",b).forEach(function(b,c){b.className="";0==c?k.add(b,"first-child"):c==this.renderData.columnCount-1&&k.add(b,"last-child");this.styleGridCell(b,a.dates[c],f,e,a);this._addMinutesClasses(b,e)},this)},this)}},styleGridCellFunc:null,defaultStyleGridCell:function(a,b,c,d,e){k.add(a,[this._cssDays[b.getDay()],"H"+c,"M"+d]);if(this.isToday(b))return k.add(a,"dojoxCalendarToday");if(this.isWeekEnd(b))return k.add(a,"dojoxCalendarWeekend")},styleGridCell:function(a,
b,c,d,e){this.styleGridCellFunc?this.styleGridCellFunc(a,b,c,d,e):this.defaultStyleGridCell(a,b,c,d,e)},_buildItemContainer:function(a,b){var c=this.itemContainerTable;if(c){var d=[];n.set(c,"height",a.sheetHeight+"px");var e=a.columnCount-(b?b.columnCount:0);8==v("ie")&&(null==this._itemTableSave?this._itemTableSave=r.clone(c):0>e&&(this.itemContainer.removeChild(c),this._recycleItemRenderers(!0),p.destroy(c),this.itemContainerTable=c=r.clone(this._itemTableSave),this.itemContainer.appendChild(c),
e=a.columnCount));var h=m("tbody",c),f=m("tr",c),h=1==h.length?h[0]:p.create("tbody",null,c),f=1==f.length?f[0]:p.create("tr",null,h);if(0<e)for(var g=0;g<e;g++)h=p.create("td",null,f),p.create("div",{className:"dojoxCalendarContainerColumn"},h);else{e=-e;for(g=0;g<e;g++)f.removeChild(f.lastChild)}m("td\x3ediv",c).forEach(function(b,c){n.set(b,{height:a.sheetHeight+"px"});d.push(b)},this);a.cells=d}},_overlapLayoutPass2:function(a){var b,c,d,e;d=a[a.length-1];for(c=0;c<d.length;c++)d[c].extent=1;
for(b=0;b<a.length-1;b++){d=a[b];for(c=0;c<d.length;c++)if(e=d[c],-1==e.extent){e.extent=1;for(var h=0,f=!1,g=b+1;g<a.length&&!f;g++){for(var l=a[g],t=0;t<l.length&&!f;t++){var k=l[t];e.start<k.end&&k.start<e.end&&(f=!0)}f||h++}e.extent+=h}}},_defaultItemToRendererKindFunc:function(a){return"vertical"},_layoutInterval:function(a,b,c,d,e){var h=[];a.colW=this.itemContainer.offsetWidth/a.columnCount;for(var f=0;f<e.length;f++){var g=e[f];"vertical"==this._itemToRendererKind(g)&&h.push(g)}0<h.length&&
this._layoutVerticalItems(a,b,c,d,h)},_layoutVerticalItems:function(a,b,c,d,e){if(null!=this.verticalRenderer){b=a.cells[b];for(var h=[],f=0;f<e.length;f++){var g=e[f],l=this.computeRangeOverlap(a,g.startTime,g.endTime,c,d),k=this.computeProjectionOnDate(a,c,l[0],a.sheetHeight),m=this.computeProjectionOnDate(a,c,l[1],a.sheetHeight);m>k&&(g=r.mixin({start:k,end:m,range:l,item:g},g),h.push(g))}c=this.computeOverlapping(h,this._overlapLayoutPass2).numLanes;d=this.percentOverlap/100;for(f=0;f<h.length;f++){g=
h[f];k=g.lane;l=g.extent;0==d?(e=1==c?a.colW:(a.colW-(c-1)*this.horizontalGap)/c,k*=e+this.horizontalGap,e=1==l?e:e*l+(l-1)*this.horizontalGap,e=100*e/a.colW,k=100*k/a.colW):(e=1==c?100:100/(c-(c-1)*d),k*=e-d*e,e=1==l?e:e*(l-(l-1)*d));l=this._createRenderer(g,"vertical",this.verticalRenderer,"dojoxCalendarVertical");n.set(l.container,{top:g.start+"px",left:k+"%",width:e+"%",height:g.end-g.start+1+"px"});var k=this.isItemBeingEdited(g),m=this.isItemSelected(g),q=this.isItemHovered(g),s=this.isItemFocused(g),
u=l.renderer;u.set("hovered",q);u.set("selected",m);u.set("edited",k);u.set("focused",this.showFocus?s:!1);u.set("storeState",this.getItemStoreState(g));u.set("moveEnabled",this.isItemMoveEnabled(g._item,"vertical"));u.set("resizeEnabled",this.isItemResizeEnabled(g._item,"vertical"));this.applyRendererZIndex(g,l,q,m,k,s);u.updateRendering&&u.updateRendering(e,g.end-g.start+1);p.place(l.container,b);n.set(l.container,"display","block")}}},_sortItemsFunction:function(a,b){var c=this.dateModule.compare(a.startTime,
b.startTime);0==c&&(c=-1*this.dateModule.compare(a.endTime,b.endTime));return this.isLeftToRight()?c:-c},getTime:function(a,b,c,d){null!=a&&(c=w.position(this.itemContainer,!0),a.touches?(d=void 0==d?0:d,b=a.touches[d].pageX-c.x,c=a.touches[d].pageY-c.y):(b=a.pageX-c.x,c=a.pageY-c.y));a=w.getContentBox(this.itemContainer);this.isLeftToRight()||(b=a.w-b);0>b?b=0:b>a.w&&(b=a.w-1);0>c?c=0:c>a.h&&(c=a.h-1);b=Math.floor(b/(w.getMarginBox(this.itemContainer).w/this.renderData.columnCount));c=this.getTimeOfDay(c,
this.renderData);a=null;b<this.renderData.dates.length&&(a=this.newDate(this.renderData.dates[b]),a=this.floorToDay(a,!0),a.setHours(c.hours),a.setMinutes(c.minutes));return a},_onGridMouseUp:function(a){this.inherited(arguments);this._gridMouseDown&&(this._gridMouseDown=!1,this._onGridClick({date:this.getTime(a),triggerEvent:a}))},_onGridTouchStart:function(a){this.inherited(arguments);var b=this._gridProps;b.moved=!1;b.start=a.touches[0].screenY;b.scrollTop=this._getScrollPosition()},_onGridTouchMove:function(a){this.inherited(arguments);
if(1<a.touches.length&&!this._isEditing)q.stop(a);else if(this._gridProps&&!this._isEditing){var b=a.touches[0].screenX,c=a.touches[0].screenY,d=this._edProps;if(!d||d&&(25<Math.abs(b-d.start.x)||25<Math.abs(c-d.start.y)))this._gridProps.moved=!0,b=this._gridProps.scrollTop-(a.touches[0].screenY-this._gridProps.start),c=this.itemContainer.offsetHeight-this.scrollContainer.offsetHeight,0>b?(this._gridProps.start=a.touches[0].screenY,this._setScrollImpl(0),this._gridProps.scrollTop=0):b>c?(this._gridProps.start=
a.touches[0].screenY,this._setScrollImpl(c),this._gridProps.scrollTop=c):this._setScrollImpl(b)}},_onGridTouchEnd:function(a){this.inherited(arguments);var b=this._gridProps;b&&(!this._isEditing&&!b.moved&&(!b.fromItem&&!b.editingOnStart&&this.selectFromEvent(a,null,null,!0),b.fromItem||(this._pendingDoubleTap&&this._pendingDoubleTap.grid?(this._onGridDoubleClick({date:this.getTime(this._gridProps.event),triggerEvent:this._gridProps.event}),clearTimeout(this._pendingDoubleTap.timer),delete this._pendingDoubleTap):
(this._onGridClick({date:this.getTime(this._gridProps.event),triggerEvent:this._gridProps.event}),this._pendingDoubleTap={grid:!0,timer:setTimeout(r.hitch(this,function(){delete this._pendingDoubleTap}),this.doubleTapDelay)}))),this._gridProps=null)},_onColumnHeaderClick:function(a){this._dispatchCalendarEvt(a,"onColumnHeaderClick")},onColumnHeaderClick:function(a){},getTimeOfDay:function(a,b){var c=60*b.minHours,c=c+a*(60*b.maxHours-c)/b.sheetHeight;return{hours:Math.floor(c/60),minutes:Math.floor(c%
60)}},_isItemInView:function(a){var b=this.inherited(arguments);if(b){var c=this.renderData;if(c.dateModule.difference(a.startTime,a.endTime,"millisecond")>36E5*(24-c.maxHours+c.minHours))return!0;var d=60*a.startTime.getHours()+a.startTime.getMinutes(),e=60*a.endTime.getHours()+a.endTime.getMinutes(),h=60*c.minHours,c=60*c.maxHours;if(0<d&&d<h||d>c&&1440>=d||0<e&&e<h||e>c&&1440>=e)return!1}return b},_ensureItemInView:function(a){var b,c=a.startTime,d=a.endTime,e=this.renderData,h=e.dateModule,f=
Math.abs(h.difference(a.startTime,a.endTime,"millisecond"));if(f>36E5*(24-e.maxHours+e.minHours))return!1;var c=60*c.getHours()+c.getMinutes(),d=60*d.getHours()+d.getMinutes(),g=60*e.minHours,k=60*e.maxHours;0<c&&c<g?(this.floorToDay(a.startTime,!0,e),a.startTime.setHours(e.minHours),a.endTime=h.add(a.startTime,"millisecond",f),b=!0):c>k&&1440>=c&&(this.floorToDay(a.startTime,!0,e),a.startTime=h.add(a.startTime,"day",1),a.startTime.setHours(e.minHours),a.endTime=h.add(a.startTime,"millisecond",f),
b=!0);0<d&&d<g?(this.floorToDay(a.endTime,!0,e),a.endTime=h.add(a.endTime,"day",-1),a.endTime.setHours(e.maxHours),a.startTime=h.add(a.endTime,"millisecond",-f),b=!0):d>k&&1440>=d&&(this.floorToDay(a.endTime,!0,e),a.endTime.setHours(e.maxHours),a.startTime=h.add(a.endTime,"millisecond",-f),b=!0);return b=b||this.inherited(arguments)},_onScrollTimer_tick:function(){this._scrollToPosition(this._getScrollPosition()+this._scrollProps.scrollStep)},snapUnit:"minute",snapSteps:15,minDurationUnit:"minute",
minDurationSteps:15,liveLayout:!1,stayInView:!0,allowStartEndSwap:!0,allowResizeLessThan24H:!0})});