$(function() {
    //当前时间
    setInterval(function() {
        var date = new Date();

        var yy = date.getFullYear(); //年
        var MM = date.getMonth() + 1; //月
        var dd = date.getDate(); //日
        var hh = date.getHours(); //时
        var mm = date.getMinutes(); //分
        var ss = date.getSeconds(); //秒
        var week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()]; //星期

        if (hh < 10) {
            hh = "0" + hh
        }
        if (mm < 10) {
            mm = "0" + mm
        }
        if (ss < 10) {
            ss = "0" + ss
        }

        $("#time").html(yy + "年" + MM + "月" + dd + "号" + "&nbsp; &nbsp;" + week + "&nbsp; &nbsp;" + hh + ":" + mm + ":" + ss);

    }, 1000);

    //菜单收缩
    var x = true;
    $(".tof").click(function() {
        var wid = $(".menu").width();
        if (x) {
            $(".menu").animate({ left: -wid }, function() {
                $(".menu").hide();
            });
            $(this).removeClass('tof_before').addClass('tof_after');
            setTimeout(function() {
                $(".cont .lefts .sum").animate({ width: "100%", "margin-left": "0" });
            }, 390)
            x = false;
        } else {
            $(".menu").animate({ left: 0 }, function() {
                $(".menu").show();
            });
            $(this).removeClass('tof_after').addClass('tof_before');
            $(".cont .lefts .sum").animate({ width: "53%", "margin-left": "2%" });
            x = true;
        }
    });
    
    //jQuery(".picMarquee-left2").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",vis:3,interTime:30});
    jQuery(".picMarquee-left").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",vis:3,interTime:30});
});