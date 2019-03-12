var map,
	url,
	basemap;

require([
	"esri/map",
	"esri/graphic",
	"esri/layers/GraphicsLayer",
	"esri/layers/ArcGISTiledMapServiceLayer",
	"esri/layers/ArcGISDynamicMapServiceLayer",
	"esri/geometry/Point",
	"esri/SpatialReference",
	'esri/symbols/PictureMarkerSymbol',
	"esri/Color",
	"dojo/on",
	"dojo/domReady!"
], function(
	Map, Graphic, GraphicsLayer, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer,
	Point, SpatialReference, PictureMarkerSymbol, Color, on
) {
	map = new Map("map", {
		logo : false,
		//zoom: 20,
		center : [ 106.266258, 36.530343 ],
		spatialReference : new SpatialReference(4326)
	});

	//影像服务
	url = "http://192.168.1.154:6080/arcgis/rest/services/zigongsi/ZGDT/MapServer";
	basemap = new ArcGISTiledMapServiceLayer(url);

	//点图层
	var graphicsLayer = new GraphicsLayer();

	map.addLayers([basemap, graphicsLayer]);

	$.ajax({
		type : 'POST',
		url : rootPath+'/organize/findOrganizeOfCompany.do',
		success : function(result) {
			if (result.code == 200) {
				var result = result.data;
				for (var i = 0; i <= result.length; i++) {
					var obj = result[i];
					DynamicRendering(obj.longitude, obj.latitude, obj);
				}
			} else {

			}
		}
	});
	
	//动态绘点方法
	function DynamicRendering(lon, lat, id) {
		//创建坐标点
		var point = new Point(lon, lat, new SpatialReference({
			wkid : 4326
		}));

		//添加图片
		var symbol = new PictureMarkerSymbol(root + 'html/home/img/clk.png', 40, 40);

		//创建graphic对象
		var graphic = new Graphic(point, symbol);

		//传入id
		graphic.text = id;

		//将graphic对象加入坐标点图层
		graphicsLayer.add(graphic);
	}

	//动态绘点点击事件
	var flag = true;
	var x = true;
	var id = null;
	var evt1 = null;
	on(graphicsLayer, 'click', function(evt) {
		if (evt1 == null) {
			evt1 = evt;
		}
		//获取传入的id
		var id = evt.graphic.text;
		$("#companyPro").html(id.province);
		$("#companyName").html(id.name);
		$("#companyaddr").html(id.address);
		$("#companyphone").html(id.phone);
		var img = evt.graphic.symbol;
		//先判断不否为同一事件
		if (evt.graphic.text.id == evt1.graphic.text.id) {
			var lon = evt.graphic.geometry.x;
			var lat = evt.graphic.geometry.y;
			//判断是否报警，报警则更换图片
			if (x) {
				img.setUrl(root + "html/home/img/circle.png");
				map.setScale(1000000);
				x = false;
			//不报警就切回初始图片
			} else {
				img.setUrl(root + "html/home/img/clk.png");
				map.setLevel(4);
				x = true;
			}	
			MapLoc(lon, lat);
			
			$(".infoShow").toggle(500);
			
		} else {
			var img1 = evt1.graphic.symbol;
			//判断是否报警，报警则更换图片
			img1.setUrl(root + "html/home/img/clk.png");
			img.setUrl(root + "html/home/img/circle.png");
			
			var lon = evt.graphic.geometry.x;
			var lat = evt.graphic.geometry.y;
			
			if (x) {
				map.setScale(1000000);
				$(".infoShow").toggle(500);
				x = false;
			} else{
				map.setScale(1000001);
			}
			MapLoc(lon, lat);
			evt1 = evt;
		}
	});
});

//定位
function MapLoc(lon, lat) {
	if (!lon || !lat) {
		alert("暂无坐标信息，无法定位！");
		return;
	}
	var cPoint = new esri.geometry.Point();
	cPoint.x = lon;
	cPoint.y = lat;

	map.centerAt(cPoint);
}

//定位+缩放
function MapLocation(lon, lat, sum) {
	if (!lon || !lat) {
		alert("暂无坐标信息，无法定位！");
		return;
	}
	var cPoint = new esri.geometry.Point();
	cPoint.x = lon;
	cPoint.y = lat;

	map.setZoom(sum);
	map.centerAt(cPoint);
}