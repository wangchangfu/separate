var map, graLayer, orgId

require([
	"esri/map",
	"esri/layers/ArcGISTiledMapServiceLayer",
	"esri/layers/ArcGISDynamicMapServiceLayer",
	"esri/graphic",
	"esri/layers/GraphicsLayer",
	"esri/geometry/Point",
	"esri/SpatialReference",
	"esri/symbols/PictureMarkerSymbol",
	"dojo/on",
	"dojo/domReady!"
], function(
	Map, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer, Graphic,
	GraphicsLayer, Point, SpatialReference, PictureMarkerSymbol, on
) {

		map = new Map("map", {
			logo : false,
			slider : false,
			center : [ 107.213144, 35.827355 ]
		});
		var url =mapUrl; 
		//影像服务
		var tiled = new ArcGISDynamicMapServiceLayer(url);
		
		//坐标点图层
		graLayer = new GraphicsLayer();
		
		//添加图层到map对象中
		map.addLayers([ tiled, graLayer ]);
		
		//显示年龄分布图
		start(null, null);
		
		//显示入职率
		Situation1();
		//显示离职率
		Situation2();
		//显示转正
		Situation3();
		//显示异动
		Situation4();
		
		//动态绘制点点击事件
		on(graLayer, "click", function(evt) {
			//id
			var id = evt.graphic.text.id;
			//名字
			var name = evt.graphic.text.name;
			//经度
			var lon = evt.graphic.geometry.x
			//纬度
			var lat = evt.graphic.geometry.y 
			//
			var workType=evt.graphic.geometry.workType;
			ZoomtoPoint(lon, lat, 3090000);
			$("#reset").fadeIn(500);
			all(id);
		});

		//鼠标移入事件
		on(graLayer, "mouse-move", function(evt) {
			//id
			var id = evt.graphic.text.name;
			//经度
			var hierarchy = evt.graphic.text.hierarchy==1?"一级公司":evt.graphic.text.hierarchy==2?"二级公司":evt.graphic.text.hierarchy==3?"三级公司":"其他";
			//纬度
			var phone = evt.graphic.geometry.phone;
			//设置弹窗标题
			map.infoWindow.setTitle("基本信息");
			//设置弹窗内容
			map.infoWindow.setContent(
				"<p>公司名称：" + id + "</p>" +
				"<p>级别：" + hierarchy + "</p>" +
				"<p>电话：" + (undefined == phone?" ":phone) + "</p>"
			);
			//显示弹窗
			map.infoWindow.show(evt.mapPoint, map.getInfoWindowAnchor(evt.screenPoint));
		});

		//鼠标移出事件
		on(graLayer, "mouse-out", function(evt) {
			map.infoWindow.hide();
		});
		
		//重置地图
		$("#reset").click(function(){
			ZoomtoPoint(107.213144, 35.827355, 50000000);
			$(this).fadeOut(500);
		});

		//定位方法
		function ZoomtoPoint(x, y, num) {
			var cPoint = new Point();
			cPoint.x = x;
			cPoint.y = y;
			map.setScale(num);
			map.centerAt(cPoint);
		}
	});

//加载入职率
function Situation1(orgId, workType){
	var data={};
	if(orgId!=null){
		data["id"]=orgId;
	}
	graLayer.clear();
	
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgList.do',
		data:data,
		success : function(result) {
			if (result.code == 200) {
				/*var result = result.data;
				for (var i = 0; i <= result.length-1; i++) {
					var obj = result[i];
					drawCoordinate(obj.longitude, obj.latitude, obj);
				}*/
				$.ajax({
					type : 'POST',
					anysc:false,
					url : rootPath+'homeStatistics/findEntryRate.do',
					success : function(result) {
						if (result.code == 200) {
							empSituation1(result.data);
						}
					}
				});
			} else {
				
			}
		}
	});
}

//加载离职率
function Situation2(orgId, workType){
	var data={};
	if(orgId!=null){
		data["id"]=orgId;
	}
	graLayer.clear();
	
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgList.do',
		data:data,
		success : function(result) {
			if (result.code == 200) {
				/*var result = result.data;
				for (var i = 0; i <= result.length-1; i++) {
					var obj = result[i];
					drawCoordinate(obj.longitude, obj.latitude, obj);
				}*/
				$.ajax({
					type : 'POST',
					anysc:false,
					url : rootPath+'homeStatistics/findTurnoveRate.do',
					success : function(result) {
						if (result.code == 200) {
							empSituation2(result.data);
						}
					}
				});
			} else {
				
			}
		}
	});
}

//加载转正率
function Situation3(orgId, workType){
	var data={};
	if(orgId!=null){
		data["id"]=orgId;
	}
	graLayer.clear();
	
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgList.do',
		data:data,
		success : function(result) {
			if (result.code == 200) {
				/*var result = result.data;
				for (var i = 0; i <= result.length-1; i++) {
					var obj = result[i];
					drawCoordinate(obj.longitude, obj.latitude, obj);
				}*/
				$.ajax({
					type : 'POST',
					anysc:false,
					url : rootPath+'homeStatistics/findPositive.do',
					success : function(result) {
						if (result.code == 200) {
							empSituation3(result.data);
						}
					}
				});
			} else {
				
			}
		}
	});
}

//加载异动率
function Situation4(orgId, workType){
	var data={};
	if(orgId!=null){
		data["id"]=orgId;
	}
	graLayer.clear();
	
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgList.do',
		data:data,
		success : function(result) {
			if (result.code == 200) {
				/*var result = result.data;
				for (var i = 0; i <= result.length-1; i++) {
					var obj = result[i];
					drawCoordinate(obj.longitude, obj.latitude, obj);
				}*/
				$.ajax({
					type : 'POST',
					anysc:false,
					url : rootPath+'homeStatistics/findChangeRate.do',
					success : function(result) {
						if (result.code == 200) {
							empSituation4(result.data);
						}
					}
				});
			} else {
				
			}
		}
	});
}

function start(orgId, workType){
	var data={};
	if(orgId!=null){
		data["id"]=orgId;
	}
	if(workType!=null){
		data["workType"]=workType;
	}
	graLayer.clear();
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgList.do',
		data:data,
		success : function(result) {
			if (result.code == 200) {
				var result = result.data;
				for (var i = 0; i <= result.length-1; i++) {
					var obj = result[i];
					drawCoordinate(obj.longitude, obj.latitude, obj);
				}
				all(null);
				//加入
			}
		}
	});
}

function drawCoordinate(lon, lat, id) {
	//创建坐标点
	var point = new esri.geometry.Point(lon, lat, new esri.SpatialReference({
		wkid : 4326
	}));
	var symbol ;
	//一级目录
	if(id.hierarchy==1){
		symbol = new esri.symbol.PictureMarkerSymbol(rootPath + 'html/home/image/point.png', 15, 15);
	}else if(id.hierarchy==2){
		symbol = new esri.symbol.PictureMarkerSymbol(rootPath + 'html/home/image/point2.png', 15, 15);
	}else if(id.hierarchy==3){
		symbol = new esri.symbol.PictureMarkerSymbol(rootPath + 'html/home/image/point3.png', 15, 15);
	}else{
		symbol = new esri.symbol.PictureMarkerSymbol(rootPath + 'html/home/image/point4.png', 15, 15);
	}
	
	//创建graphic对象
	var graphic = new esri.Graphic(point, symbol);
	//传入id值
	graphic.text = id;
	//将graphic对象加入坐标点图层
	graLayer.add(graphic);
}

var all=function(id){
	var data={};
	if(id!=null){
		data['id']=id;
	}
	$(".number").html(0);
	salary([0,0,0,0,0,0,0,0,0,0,0,0,0,0]);
	$.ajax({
		type : 'POST',
		data:data,
		url : rootPath+'homeStatistics/findAgeDistribution.do',
		success : function(result) {
			if (result.code == 200) {
				salary(result.data);
			}
		}
	});
	$.ajax({
		type : 'POST',
		data:data,
		url : rootPath+'homeStatistics/findContract.do',
		success : function(result) {
			if (result.code == 200) {
				onlineEmp(result.data);
			}
		}
	});
	$.ajax({
		type : 'POST',
		data:data,
		url : rootPath+'homeStatistics/findEducationt.do',
		success : function(result) {
			if (result.code == 200) {
				education(result.data);
			}
		}
	});
	$.ajax({
		type : 'POST',
		data:data,
		url : rootPath+'homeStatistics/getEmpCount.do',
		success : function(result) {
			if (result.code == 200) {
				$(".number").html(result.data);
			}
		},
		error:function(result){
			$(".number").html("0");
		}
	});
}
