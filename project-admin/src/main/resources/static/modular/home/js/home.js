$(function(){
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'homeStatistics/findOrgListForOne.do',
		success : function(result) {
			if (result.code == 200) {
				var str ="";
				var data = result.data;
				for ( var x in data) {
					str += "<li><div class='pic'><a href=\"javascript:start('"+data[x].id+"',null);\">"+data[x].name+"</a></div></li>";
				}
				$("#picList").html(str);
			}
		}
	});
	$.ajax({
		type : 'POST',
		anysc:false,
		url : rootPath+'workType/findList.do',
		success : function(result) {
			if (result.code == 200) {
				var str ="";
				var data = result.data;
				for ( var x in data) {
					str += "<li><div class='pic'><a href=\"javascript:start(null,'"+data[x].id+"');\">"+data[x].name+"</a></div></li>";
				}
				//$("#picList2").html(str);
				$("#picList2").html(str);
			   $(".picMarquee-left").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",vis:4,interTime:55});
			}
		}
	});
})
/**
 * 随机数
 * @param num
 * @returns
 */
function redomNum(num){
	var arrnum=Math.ceil(Math.random()*num);
	return arrnum;
}


layui.use('element', function() {
	var laydate = layui.element;
})

var onlineEmp = function(da1) {
	var data1 =[];
	var data2 =[];
	var data3 =[];
	var data4 =[];
	var data5 =[];
	var a11=redomNum(10),a12=redomNum(10),a13=redomNum(10);
	var  a21=redomNum(10),a22=redomNum(10),a23=redomNum(10);
	var  a31=redomNum(10),a32=redomNum(10),a33=redomNum(10);
	var  a41=redomNum(10),a42=redomNum(15),a43=redomNum(10);
	var  a51=redomNum(10),a52=redomNum(10),a53=redomNum(10);
	/*var a11=0,a12=0,a13=0;
	var  a21=0,a22=0,a23=0;
	var  a31=0,a32=0,a33=0;
	var  a41=0,a42=0,a43=0;
	var  a51=0,a52=0,a53=0;*/
	/*for ( var x in da1) {
		for ( var y in da1[x]) {
			if( da1[x][y].type=="固定期限劳动合同"){
				if(da1[x][y].time=="2016"){
					a11=da1[x][y].count;
				}
				if(da1[x][y].time=="2017"){
					a12=da1[x][y].count;
				}
				if(da1[x][y].time=="2018"){
					a13=da1[x][y].count;
				}
			}
			if( da1[x][y].type=="无固定期限劳动合同"){
				if(da1[x][y].time=="2016"){
					a21=da1[x][y].count;
				}
				if(da1[x][y].time=="2017"){
					a22=da1[x][y].count;
				}
				if(da1[x][y].time=="2018"){
					a23=da1[x][y].count;
				}
			}
			if( da1[x][y].type=="实习协议"){
				if(da1[x][y].time=="2016"){
					a31=da1[x][y].count;
				}
				if(da1[x][y].time=="2017"){
					a32=da1[x][y].count;
				}
				if(da1[x][y].time=="2018"){
					a33=da1[x][y].count;
				}
			}
			if( da1[x][y].type=="劳务合同"){
				if(da1[x][y].time=="2016"){
					a41=da1[x][y].count;
				}
				if(da1[x][y].time=="2017"){
					a42=da1[x][y].count;
				}
				if(da1[x][y].time=="2018"){
					a43=da1[x][y].count;
				}
			}
			if( da1[x][y].type !="固定期限劳动合同"&& da1[x][y].type !="无固定期限劳动合同"&& da1[x][y].type !="实习协议"&& da1[x][y].type !="劳务合同"){
				if(da1[x][y].time=="2016"){
					a51+=da1[x][y].count;
				}
				if(da1[x][y].time=="2017"){
					a52+=da1[x][y].count;
				}
				if(da1[x][y].time=="2018"){
					a53+=da1[x][y].count;
				}
			}
		}
	}*/
	data1.push(a11);
	data1.push(a12);
	data1.push(a13);
	data2.push(a21);
	data2.push(a22);
	data2.push(a23);
	data3.push(a31);
	data3.push(a32);
	data3.push(a33);
	data4.push(a41);
	data4.push(a42);
	data4.push(a43);
	data5.push(a51);
	data5.push(a52);
	data5.push(a53);
	var dom = document.getElementById("onlineEmp");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		    color: ['#3398DB'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend : {
				data : [ '固定期', '无固定', '实习协议', '劳务合同', '其他'],
				textStyle : {
					color : "#d8d8d8"
				}
			},
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['2016', '2017', '2018'],
		            axisTick: {
		                alignWithLabel: true
		            },
		            axisLine: {
			        	lineStyle: {
			        		color: '#d8d8d8'
			        	}
			        }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLine: {
			        	lineStyle: {
			        		color: '#d8d8d8'
			        	}
			        }
		        }
		    ],
		    series : [
		        {
		            name:'固定期',
		            type:'bar',
		            barWidth: '10%',
		            data:data1,
		            itemStyle : {
						color : "#4738AA"
					}
		        },
		        {
		            name:'无固定',
		            type:'bar',
		            barWidth: '10%',
		            data:data2,
		            itemStyle : {
						color : "#4DC3E1"
					}
		        }
		        ,
		        {
		            name:'实习协议',
		            type:'bar',
		            barWidth: '10%',
		            data:data3
		            ,
		            itemStyle : {
						color : "#BDDF1F"
					}
		        }
		        ,
		        {
		            name:'劳务合同',
		            type:'bar',
		            barWidth: '10%',
		            data:data4,
		            itemStyle : {
						color : "#1ECCC6"
					}
		        }
		        ,
		        {
		            name:'其他',
		            type:'bar',
		            barWidth: '10%',
		            data:data5
		        }
		    ]
		};
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}
onlineEmp();

var education = function(data) {
	var a1=0;
	var a2=0;
	var a3=0;
	var a4=0;
	var a5=0;
	var a6=0;
	var a7=0;
	var a8=0;
	var a9=0;
	for ( var x in data) {
		if(data[x].lastEducation=="博士"){
			a1=data[x].count;
		}else
		if(data[x].lastEducation=="硕士"){
			a2=data[x].count;
		}else
		if(data[x].lastEducation=="研究生"){
			a3=data[x].count;
		}else
		if(data[x].lastEducation=="中央党校大学"){
			a4=data[x].count;
		}else
		if(data[x].lastEducation=="大学"){
			a5=data[x].count;
		}else
		if(data[x].lastEducation=="大专"){
			a6=data[x].count;
		}else
		if(data[x].lastEducation=="中专"){
			a7=data[x].count;
		}else if(data[x].lastEducation=="高中"){
			a8=data[x].count;
		}else
		{
			a9+=data[x].count;
		}
		
	}
	var dom = document.getElementById("education");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			x : 'right',
			y : 'center',
			orient : 'vertical',
			data : [ '博士', '硕士', '研究生', '中央党校大学', '大学', '大专', '中专', '高中', '其他' ],
			textStyle : {
				color : "#d8d8d8"
			}
		},
		calculable : true,
		series : [
			{
				type : 'pie',
				radius : [ 20, 110 ],
				center : [ '40%', '50%' ],
				roseType : 'radius',
				label : {
					normal : {
						show : false
					},
					emphasis : {
						show : true
					}
				},
				lableLine : {
					normal : {
						show : false
					},
					emphasis : {
						show : true
					}
				},
				data : [
					{
						value : a1,
						name : '博士',
						itemStyle : {
							color : "#4738AA"
						}
					},
					{
						value : a2,
						name : '硕士',
						itemStyle : {
							color : "#4DC3E1"
						}
					},
					{
						value : a3,
						name : '研究生',
						itemStyle : {
							color : "#BDDF1F"
						}
					},
					{
						value : a4,
						name : '中央党校大学',
						itemStyle : {
							color : "#3C69D9"
						}
					},
					{
						value : a5,
						name : '大学',
						itemStyle : {
							color : "#1ECCC6"
						}
					},
					{
						value : a6,
						name : '大专',
						itemStyle : {
							color : "#0983F5"
						}
					},
					{
						value : a7,
						name : '中专',
						itemStyle : {
							color : "#50E6A5"
						}
					},
					{
						value : a8,
						name : '高中',
						itemStyle : {
							color : "#5ABB44"
						}
					}
					,
					{
						value : a9,
						name : '其他',
						itemStyle : {
							color : "#5ABf44"
						}
					}
				]
			}
		]
	};

	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}
education();

//员工年龄段分布图
var salary = function(data) {
	console.log("获取数据："+data);
	var man=[];
	var woman=[];
	if(data){
	//偶数是男 奇数是女
		for (var i=0;i<data.length;i++) {
			if(i%2==0){
				man.push(data[i]);
			}else{
				woman.push(data[i]);
			}
		}
	}
	var dom = document.getElementById("salary");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	
	app.title = '员工分布图';
	option = {
		    title: {
		        left: 'center'
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b} : {c}'
		    },
		    legend: {
		    	icon: 'rect',
		        left: 'left',
		        data: ['男', '女'],
		        textStyle : {
					color : "#d8d8d8"
				}
		    },
		    xAxis: {
		        type: 'category',
		        name: 'x',
		        splitLine: {show: false},
		        data: ['25以下', '26-30', '31-35', '36-40', '41-45', '46-50', '50以上'],
		        axisLine: {
		        	lineStyle: {
		        		color: '#d8d8d8'
		        	}
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    yAxis: {
		        type: 'log',
		        name: 'y',
		        axisLine: {
		        	lineStyle: {
		        		color: '#d8d8d8'
		        	}
		        }
		    },
		    series: [
		        {
		            name: '男',
		            type: 'line',
		            data: man,
		            itemStyle : {
						color : "#4DC3E1"
					}
		        },
		        {
		            name: '女',
		            type: 'line',
		            data: woman,
		            itemStyle : {
						color : "#445AC9"
					}
		        }
		    ]
		}
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}

salary();
//员工人员情况入职率
var empSituation1 = function(data) {
	var dom = document.getElementById("empSituation1");
	var myChart = echarts.init(dom);
	var app = {};
	var da=redomNum(30);
	option = null;
	option = {
			textStyle : {
				color : "#d8d8d8"
			},
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'center',
		        data:['入职率'],
		        textStyle : {
					color : "#d8d8d8"
				}
		    },
		    color: ['#0983F5', '#1B1A5E'],
		    series: [
		        {
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		                {value:da, name:'入职率'},
		                {value:100-da, name:'其他'},
		                
		            ]
		        }
		    ]
		};
		;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
}
//离职率
var empSituation2 = function(data) {
	var dom = document.getElementById("empSituation2");
	var myChart = echarts.init(dom);
	var app = {};
	var da=redomNum(30);
	option = null;
	option = {
			textStyle : {
				color : "#d8d8d8"
			},
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'center',
		        data:['离职率'],
		        textStyle : {
					color : "#d8d8d8"
				}
		    },
		    color: ['#0983F5', '#1B1A5E'],
		    series: [
		        {
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		                {value:da, name:'离职率'},
		                {value:100-da, name:'其他'}
		            ]
		        }
		    ]
		};
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}
//转正率
var empSituation3 = function(data) {
	var dom = document.getElementById("empSituation3");
	var myChart = echarts.init(dom);
	var app = {};
	var da=redomNum(30);
	option = null;
	option = {
			textStyle : {
				color : "#d8d8d8"
			},
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'center',
		        data:['转正率'],
		        textStyle : {
					color : "#d8d8d8"
				}
		    },
		    color: ['#0983F5', '#1B1A5E'],
		    series: [
		        {
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		                {value:da, name:'转正率'},
		                {value:100-da, name:'其他'}
		                
		            ]
		        }
		    ]
		};
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}
//异动率
var empSituation4 = function(data) {
	var dom = document.getElementById("empSituation4");
	var myChart = echarts.init(dom);
	var app = {};
	var da=redomNum(30);
	option = {
			textStyle : {
				color : "#d8d8d8"
			},
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'center',
		        data:['异动率'],
		        textStyle : {
					color : "#d8d8d8"
				}
		    },
		    color: ['#0983F5', '#1B1A5E'],
		    series: [
		        {
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		                {value:da, name:'异动率'},
		                {value:100-da, name:'其他'},
		            ]
		        }
		    ]
		};
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}

empSituation1();
empSituation2();
empSituation3();
empSituation4();


/*
 * 
 * 设置菜单地址
 */
$(".menu_01").click(function() {
	window.location.href = rootPath + "modelIndex?id=1";
});

$(".menu_02").click(function() {
	window.location.href = rootPath + "modelIndex?id=2";
});

$(".menu_03").click(function() {
	window.location.href = rootPath + "modelIndex?id=3";
});

/*$(".menu_04").click(function() {
	window.location.href = rootPath + "modelIndex?id=4";
});
$(".menu_05").click(function() {
	window.location.href = rootPath + "modelIndex?id=5";
});
$(".menu_06").click(function() {
	window.location.href = rootPath + "modelIndex?id=6";
});
$(".menu_07").click(function() {
	window.location.href = rootPath + "modelIndex?id=7";
});
$(".menu_08").click(function() {
	window.location.href = rootPath + "modelIndex?id=8";
});*/

