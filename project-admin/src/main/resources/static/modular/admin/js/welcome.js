WelCome = {};
$(function () {
    //人员统计
    WelCome.ps();
    //本月成本消耗
    WelCome.cctm();
    //车辆统计
    WelCome.vs();
    //业绩排行榜
    WelCome.pr();
});
WelCome.ps = function () {
    var ps = echarts.init(document.getElementById('ps'));
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['环卫工人', '司机', '区域经理', '区域管理员', '行政主管', '片区文员'],
            textStyle: {
                color: '#505050'
            }
        },
        series: [
            {
                name: '统计结果(单位/人)',
                type: 'pie',
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
                data: [
                    {value: 335, name: '环卫工人', itemStyle: {color: "#e99d44"}},
                    {value: 70, name: '司机', itemStyle: {color: "#2dcfc5"}},
                    {value: 5, name: '区域经理', itemStyle: {color: "#44ade9"}},
                    {value: 10, name: '区域管理员', itemStyle: {color: "#09aa73"}},
                    {value: 2, name: '行政主管', itemStyle: {color: "#9ed867"}},
                    {value: 3, name: '片区文员', itemStyle: {color: "#e9d044"}},
                ]
            }
        ]
    };
    ps.setOption(option);
};

WelCome.cctm = function () {
    var cctm = echarts.init(document.getElementById('cctm'));
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            x: 'center',
            data: ['耗油费', '耗水费', '维修费', '加班费', '采购费'],
            textStyle: {
                color: '#505050'
            }
        },
        radar: [
            {
                indicator: [
                    {text: '耗油费89(万元)', max: 200},
                    {text: '耗水费39（万元）', max: 200},
                    {text: '维修费107（万元）', max: 200},
                    {text: '加班费187（万元）', max: 200},
                    {text: '采购费137（万元）', max: 200}
                ],
                center: ['50%', '50%'],
                radius: 100
            }
        ],
        series: [
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        value: [39, 89, 107, 187, 137],
                        name: '统计结果(单位/万元)',
                        itemStyle: {color: "#15b57e"}
                    }
                ]
            }
        ]
    };
    cctm.setOption(option);
};

WelCome.vs = function () {
    var vs = echarts.init(document.getElementById('vs'));
    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: [{
            type: 'category',
            data: ['非压缩式垃圾车', '压缩式垃圾车', '高压清洗车', '护栏清洗车', '路面养护车', '清洗车', '抑尘车', '扫地车', '清洁扫地车', '餐厨垃圾运输车'],
            axisPointer: {
                type: 'shadow'
            },
            axisLine: {
                lineStyle: {
                    color: '#505050'
                }
            }, axisLabel: {
                interval: 0,
                formatter: function (value) {
                    debugger
                    var ret = "";//拼接加\n返回的类目项
                    var maxLength = 2;//每项显示文字个数
                    var valLength = value.length;//X轴类目项的文字个数
                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                    if (rowN > 1)//如果类目项的文字大于3,
                    {
                        for (var i = 0; i < rowN; i++) {
                            var temp = "";//每次截取的字符串
                            var start = i * maxLength;//开始截取的位置
                            var end = start + maxLength;//结束截取的位置
                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                            temp = value.substring(start, end) + "\n";
                            ret += temp; //凭借最终的字符串
                        }
                        return ret;
                    }
                    else {
                        return value;
                    }
                }
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value}辆'
            },
            axisLine: {
                lineStyle: {
                    color: '#505050'
                }
            }
        }],
        series: [{
            name: '车辆数',
            type: 'bar',
            data: [20, 5, 12, 18, 35, 20, 10, 5, 3, 9],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#29eecb'},
                            {offset: 1, color: '#279fe3'}
                        ]
                    )
                }
            }
        }]
    };
    vs.setOption(option);
};

WelCome.pr = function () {
    var pr = echarts.init(document.getElementById('pr'));
    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}分'
            },
            boundaryGap: [0, 0.01],
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        },
        yAxis: {
            type: 'category',
            data: ['TOP5 张三', 'TOP4 李四', 'TOP3 王五', 'TOP2 潇潇', 'TOP1 天语'],
            axisLine: {
                lineStyle: {
                    color: '#505050'
                }
            }
        },
        series: [{
            type: 'bar',
            data: [94, 95, 97, 98, 99],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 1, 0,
                        [
                            {offset: 0, color: '#39d9d0'},
                            {offset: 1, color: '#d6f765'}
                        ]
                    )
                }
            }
        }]
    };
    pr.setOption(option);
};
