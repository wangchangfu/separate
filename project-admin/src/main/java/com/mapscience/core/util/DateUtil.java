package com.mapscience.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * <b>时间日期操作辅助工具.</b>
 * <b>Description:</b> 在DateUtil类中, 关于几个名词的定义: 
 *    日期, 例如: 2014-01-01;
 *    时间, 例如: 08:30:42;
 *    时间轴, 例如: 08:30:42.001;
 *    日期时间, 例如: 2014-01-01 08:30:42;
 *    日期时间轴, 例如: 2014-01-01 08:30:42.001.
 *    
 * <p>Copyright: Copyright (c) 2018</p>  

* <p>Company: zzs</p>  

* @author wcf  

* @date 2018年6月25日  

* @version 1.0
 */
public abstract class DateUtil {

    /**
     * 最大默认日期: 2999-12-31
     */
    public static final String MAX_DATE = "2999-12-31";

    /**
     * 中文式日期时间 格式模板（默认）:yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 中文式日期时间轴 格式模板:yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String ZH_CN_DATE_TIME_STAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 中文式日期 格式模板:yyyy-MM-dd
     */
    public static final String ZH_CN_DATE = "yyyy-MM-dd";
    /**
     * 中文式时间轴 格式模板:HH:mm:ss.SSS
     */
    public static final String ZH_CN_TIME_STAMP = "HH:mm:ss.SSS";
    /**
     * 中文式时间 格式模板:HH:mm:ss
     */
    public static final String ZH_CN_TIME = "HH:mm:ss";

    /**
     * 年月日时分秒字符串 格式模板:yyyyMMddHHmmss
     */
    public static final String CURR_TIME = "yyyyMMddHHmmss";
    /**
     * 年月日时分字符串 格式模板:yyyyMMddHHmm
     */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyyMMddHHmm";
    /**
     * 年月日字符串 格式模板:yyyyMMdd
     */
    public static final String YEAR_MONTH_DAY = "yyyyMMdd";
    /**
     * 时分秒字符串 格式模板:HHmmss
     */
    public static final String HOUR_MINUTE_SECOND = "HHmmss";
    /**
     * 飞机起飞、到达时间格式(保留日期时间,去掉秒) 格式模板:yyyy-MM-dd HH:mm
     */
    public static final String FLIGHT_TIME = "yyyy-MM-dd HH:mm";
    /**
     * 时分字符串 格式模板:HH:mm
     */
    public static final String HOUR_MINUTE = "HH:mm";

    /**
     * 星期字典, 默认从1开始即代表星期一, 7结束即代表星期天
     */
    public static final int WEEKS_DIGITS[] = { 7, 1, 2, 3, 4, 5, 6 };

	/**
	 * 星期字典, 星期简写-周几
	 */
	public static final String[] CN_WEEKS_SIMPLE = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	/**
	 * 星期字典, 星期全写-星期几
	 */
	public static final String[] CN_WEEKS_FULL = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected DateUtil() {
        super();
    }

    /**
     * <pre>
     * 获取当前系统时间实例.
     * </pre>
     * 
     * @return Date 时间实例
     */
    public static Date date() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前系统时间
     * 
     * @return Timestamp
     */
    public static Timestamp timestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <pre>
     * 根据时间毫秒数得到时间实例.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return Date 时间实例
     */
    public static Date date(long millis) {
        return new Date(millis);
    }

    /**
     * <pre>
     * 获取当前系统时间的时间毫秒数.
     * </pre>
     * 
     * @return long 时间毫秒数
     */
    public static long times() {
        return System.currentTimeMillis();
    }

    /**
     * <pre>
     * 获取默认格式的时间格式化模板实例,
     * 默认格式为:yyyy-MM-dd HH:mm:ss.
     * </pre>
     * 
     * @return DateFormat 时间格式化模板实例
     */
    public static DateFormat getDateFormat() {
        return new SimpleDateFormat(DEFAULT_DATE_TIME);
    }

    /**
     * <pre>
     * 获取指定时间格式的时间格式化模板实例,
     * 参数 format 的值必须形如:yyyy-MM-dd HH:mm:ss,
     * 如果 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @return DateFormat 时间格式化模板实例
     */
    public static DateFormat getDateFormat(String format) {
        if (null == format || format.length() <= 0) {
            return null;
        }

        return new SimpleDateFormat(format);
    }

    /**
     * <pre>
     * 将当前系统时间格式化成指定时间格式的字符串,
     * 例如参数 format 的值必须形如:yyyy-MM-dd HH:mm:ss,则返回 2000-01-01 01:01:01,
     * 如果 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @return String 对应时间格式的字符串
     */
    public static String format(String format) {
        if (null == format || format.length() == 0) {
            return null;
        }

        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(format);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成指定时间格式的字符串,
     * 例如参数 format 的值必须形如:yyyy-MM-dd HH:mm:ss,则返回 2000-01-01 01:01:01,
     * 如果 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @return millis 时间毫秒数
     * @return String 对应时间格式的字符串
     */
    public static String format(String format, long millis) {
        if (null == format || format.length() == 0) {
            return null;
        }

        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(format);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成指定时间格式的字符串,
     * 例如参数 format 的值必须形如:yyyy-MM-dd HH:mm:ss,则返回 2000-01-01 01:01:01,
     * 如果 date==null 或者 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @return date 时间实例
     * @return String 对应时间格式的字符串
     */
    public static String format(String format, Date date) {
        if (null == date || null == format || format.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成指定时间格式的字符串,
     * 例如参数 format 的值必须形如:yyyy-MM-dd HH:mm:ss,则返回 2000-01-01 01:01:01,
     * 如果 calendar==null 或者 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @return calendar 日历实例
     * @return String 对应时间格式的字符串
     */
    public static String format(String format, Calendar calendar) {
        if (null == calendar || null == format || format.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(format);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 时间字符串格式标准化, 
     * 即将字符串先用指定时间格式转为时间然后再用该时间格式转为时间字符串,
     * 如果任何一个参数 为null 或者 length()==0,则返回 null,
     * 如果参数转对应的格式异常,在直接返回 str.
     * </pre>
     * 
     * @param format 时间格式
     * @return str 字符串
     * @return String 时间字符串
     */
    public static String format(String format, String str) {
        if (null == format || format.length() == 0 || null == str || str.length() == 0) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(str);
            return sdf.format(date);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * 将时间实例用对应的时间格式化模板实例转换为字符串,
     * 如果 df==null 或者 str==null,则返回 null.
     * </pre>
     * 
     * @param df 时间格式化模板实例
     * @return date 时间实例
     * @return String 时间字符串
     */
    public static String format(DateFormat df, Date date) {
        if (null == df || null == date) {
            return null;
        }

        try {
            return df.format(date);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * 将字符串使用对应的时间格式转换为时间实例,
     * 例如字符串为 2000-01-01 01:01:01,时间格式为 yyyy-MM-dd HH:mm:ss.SSS,则返回对应的时间实例,
     * 如果 str==null 或者 str()==0 或者 format==null 或者 format.length()==0,则返回 null.
     * </pre>
     * 
     * @param format 时间格式
     * @param str 字符串
     * @return Date 时间实例
     */
    public static Date parse(String format, String str) {
        if (null == format || format.length() == 0 || null == str || str.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * 将字符串使用对应的时间格式化模板实例转换为时间实例,
     * 例如字符串为 2000-01-01 01:01:01,时间格式为 yyyy-MM-dd HH:mm:ss.SSS,则返回对应的时间实例,
     * 如果 str==null 或者 str()==0 或者 df==null,则返回 null.
     * </pre>
     * 
     * @param df 时间格式化模板实例
     * @param str 字符串
     * @return Date 时间实例
     */
    public static Date parse(DateFormat df, String str) {
        if (null == df || null == str || str.length() == 0) {
            return null;
        }

        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * 获取当前系统时间所在一天中的开始时间,
     * 例如 2000-01-01 01:01:01 所在一天开始时间为 2000-01-01 00:00:00.
     * </pre>
     * 
     * @return String 日期时间字符串
     */
    public static String getBeginTime() {
        return getBeginTime(Calendar.getInstance());
    }

    /**
     * <pre>
     * 获取指定时间毫秒数所在一天中的开始时间,
     * 例如 millis=2000-01-01 01:01:01 所在一天开始时间为 2000-01-01 00:00:00.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 日期时间字符串
     */
    public static String getBeginTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return getBeginTime(calendar);
    }

    /**
     * <pre>
     * 获取指定时间实例所在一天中的开始时间,
     * 例如 date=2000-01-01 01:01:01 所在一天开始时间为 2000-01-01 00:00:00
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 日期时间字符串
     */
    public static String getBeginTime(Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getBeginTime(calendar);
    }

    /**
     * <pre>
     * 获取指定日历实例所在一天中的开始时间,
     * 例如 calendar=2000-01-01 01:01:01 所在一天开始时间为 2000-01-01 00:00:00
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static String getBeginTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 获取当前系统时间所在一天中的结束时间,
     * 例如 2000-01-01 01:01:01 所在一天结束时间为 2000-01-01 23:59:59.
     * </pre>
     * 
     * @return String 日期时间字符串
     */
    public static String getEndTime() {
        return getEndTime(Calendar.getInstance());
    }

    /**
     * <pre>
     * 获取指定时间毫秒数所在一天中的结束时间,
     * 例如 millis=2000-01-01 01:01:01 所在一天结束时间为 2000-01-01 23:59:59
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 日期时间字符串
     */
    public static String getEndTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return getEndTime(calendar);
    }

    /**
     * <pre>
     * 获取指定时间所在一天中的结束时间,
     * 例如 date=2000-01-01 01:01:01 所在一天结束时间为 2000-01-01 23:59:59,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 日期时间字符串
     */
    public static String getEndTime(Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getEndTime(calendar);
    }

    /**
     * <pre>
     * 获取指定日历实例所在一天中的结束时间,
     * 例如 calendar=2000-01-01 01:01:01 所在一天结束时间为 2000-01-01 23:59:59,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static String getEndTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(calendar.getTime());
    }

	/**
	 * 得到当月起始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getMonthStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 取得季度月
	 *
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}
	
	/**
	 *
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 *
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
	
	/**
	 * 取季度
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getQuarter(Date date) {
		if (date.getMonth() == 0 || date.getMonth() == 1 || date.getMonth() == 2) {
			return 1;
		} else if (date.getMonth() == 3 || date.getMonth() == 4 || date.getMonth() == 5) {
			return 2;
		} else if (date.getMonth() == 6 || date.getMonth() == 7 || date.getMonth() == 8) {
			return 3;
		} else if (date.getMonth() == 9 || date.getMonth() == 10 || date.getMonth() == 11) {
			return 4;
		} else {
			return 0;

		}
	}

	
	/**
	 * 得到当前年起始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getYearStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到当前年最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getYearEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
    
    /**
	 * 得到month的终止时间点.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
	
	/**
	 * 得到当前周起始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getWeekStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.get(Calendar.WEEK_OF_YEAR);
		int firstDay = calendar.getFirstDayOfWeek();
		calendar.set(Calendar.DAY_OF_WEEK, firstDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到当前周截止时间
	 * @param date
	 * @return
	 */
	public static Date getWeekEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.get(Calendar.WEEK_OF_YEAR);
		int firstDay = calendar.getFirstDayOfWeek();
		calendar.set(Calendar.DAY_OF_WEEK, 8 - firstDay);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

    /**
     * <pre>
     * 获取当前时间所在小时的开始时间,
     * 例如 calendar=2014-02-12 01:01:01 所在一天开始时间为 2014-02-12 01:00:00
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static Date getHourBeginTime() {
        return getHourBeginTime(Calendar.getInstance());
    }
    
    /**
     * <pre>
     * 获取根据指定时间所在小时的开始时间,
     * 例如 calendar=2014-02-12 01:01:01 所在一天开始时间为 2014-02-12 01:00:00
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static Date getHourBeginTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * <pre>
     * 获取当前时间在所月份的开始时间,
     * 例如 calendar=2014-02-12 01:01:01 所在一天开始时间为 2014-02-01 00:00:00
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static String getMonthBeginTime() {
        return getMonthBeginTime(Calendar.getInstance());
    }

    /**
     * <pre>
     * 获取根据指定时间在所月份的开始时间,
     * 例如 calendar=2014-02-12 01:01:01 所在一天开始时间为 2014-02-01 00:00:00
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param
     * @return String 日期时间字符串
     */
    public static String getMonthBeginTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(calendar.getTime());
    }
    
    /**
	 * 取得月最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMonthEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
    // =============================================================================
    // ------------ yyyy-MM-dd
    // =============================================================================
    /**
     * <pre>
     * 将当前系统时间格式化成 yyyy-MM-dd 格式的字符串,
     * 例如: 2000-01-01.
     * </pre>
     * 
     * @return String 日期字符串
     */
    public static String getDate() {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        return df.format(_date);
    }
    
    /**
     * <pre>
     * 将当前系统时间格式化成指定格式的字符串,
     * 例如: yyyyMMdd.
     * </pre>
     * 
     * @return String 日期字符串
     */
    public static String getDate(String format) {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(format);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成 yyyy-MM-dd 格式的字符串,
     * 例如 millis=2000-01-01 01:01:01,则返回 2000-01-01.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 日期字符串
     */
    public static String getDate(long millis) {
        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成 yyyy-MM-dd 格式的字符串,
     * 例如 date=2000-01-01 01:01:01,则返回 2000-01-01,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 日期字符串
     */
    public static String getDate(Date date) {
        if (null == date) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成 yyyy-MM-dd 格式的字符串,
     * 例如 calendar=2000-01-01 01:01:01,则返回 2000-01-01,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 日期字符串
     */
    public static String getDate(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 将 yyyy-MM-dd 格式的字符串转为时间实例,
     * 例如 str=2000-01-01,则返回date=2000-01-01 00:00:00,
     * 如果 str==null 或者格式转换异常,则返回 null.
     * </pre>
     * 
     * @param str 日期字符串
     * @return Date 时间实例
     */
    public static Date parseDate(String str) {
        if (null == str || str.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // =============================================================================
    // ------------ HH:mm:ss
    // =============================================================================
    /**
     * <pre>
     * 将当前系统时间格式化成  HH:mm:ss 格式的字符串,
     * 例如: 01:01:01.
     * </pre>
     * 
     * @return String 时间字符串
     */
    public static String getTime() {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(ZH_CN_TIME);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成 HH:mm:ss 格式的字符串,
     * 例如 millis=2000-01-01 01:01:01,则返回 01:01:01.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 时间字符串
     */
    public static String getTime(long millis) {
        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(ZH_CN_TIME);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成 HH:mm:ss 格式的字符串,
     * 例如 date=2000-01-01 01:01:01,则返回 01:01:01,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 时间字符串
     */
    public static String getTime(Date date) {
        if (null == date) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_TIME);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成 HH:mm:ss 格式的字符串,
     * 例如 calendar=2000-01-01 01:01:01,则返回01:01:01,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 时间字符串
     */
    public static String getTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_TIME);
        return df.format(calendar.getTime());
    }

    // =============================================================================
    // ------------ yyyy-MM-dd HH:mm:ss
    // =============================================================================
    /**
     * <pre>
     * 将当前系统时间格式化成 yyyy-MM-dd HH:mm:ss 格式的字符串,
     * 例如: 2000-01-01 01:01:01.
     * </pre>
     * 
     * @return String 日期时间字符串
     */
    public static String getDatetime() {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成 yyyy-MM-dd HH:mm:ss 格式的字符串,
     * 例如 millis=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 日期时间字符串
     */
    public static String getDatetime(long millis) {
        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成  yyyy-MM-dd HH:mm:ss 格式的字符串,
     * 例如 date=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 日期时间字符串
     */
    public static String getDatetime(Date date) {
        if (null == date) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成 yyyy-MM-dd HH:mm:ss 格式的字符串,
     * 例如 calendar=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 日期时间字符串
     */
    public static String getDatetime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 将 yyyy-MM-dd HH:mm:ss 格式的字符串转为时间实例,
     * 例如 str=2000-01-01 01:01:01,则返回date=2000-01-01 01:01:01,
     * 如果 str==null 或者格式转换异常,则返回 null.
     * </pre>
     * 
     * @param str 日期时间字符串
     * @return Date 时间实例
     */
    public static Date parseDatetime(String str) {
        if (null == str || str.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME);
        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // =============================================================================
    // ------------ yyyy-MM-dd HH:mm:ss.SSS
    // =============================================================================
    /**
     * <pre>
     * 将当前系统时间格式化成 yyyy-MM-dd HH:mm:ss.SSS 格式的字符串,
     * 例如: 2000-01-01 01:01:01.001.
     * </pre>
     * 
     * @return String 日期时间轴字符串
     */
    public static String getDatetimeStamp() {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(ZH_CN_DATE_TIME_STAMP);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成 yyyy-MM-dd HH:mm:ss.SSS 格式的字符串,
     * 例如 millis=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01.001.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 日期时间轴字符串
     */
    public static String getDatetimeStamp(long millis) {
        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(ZH_CN_DATE_TIME_STAMP);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成  yyyy-MM-dd HH:mm:ss.SSS 格式的字符串,
     * 例如 date=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01.001,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 日期时间轴字符串
     */
    public static String getDatetimeStamp(Date date) {
        if (null == date) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE_TIME_STAMP);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成 yyyy-MM-dd HH:mm:ss.SSS 格式的字符串,
     * 例如 calendar=2000-01-01 01:01:01,则返回 2000-01-01 01:01:01.001,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 日期时间轴字符串
     */
    public static String getDatetimeStamp(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE_TIME_STAMP);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 将 yyyy-MM-dd HH:mm:ss.SSS 格式的字符串转为时间实例,
     * 例如 str=2000-01-01 01:01:01.001,则返回date=2000-01-01 01:01:01.001,
     * 如果 str==null 或者格式转换异常,则返回 null.
     * </pre>
     * 
     * @param str 日期时间轴字符串
     * @return Date 时间实例
     */
    public static Date parseDatetimeStamp(String str) {
        if (null == str || str.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE_TIME_STAMP);
        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // =============================================================================
    // ------------ yyyyMMddHHmmss
    // =============================================================================
    /**
     * <pre>
     * 将当前系统时间格式化成 yyyyMMddHHmmss 格式的字符串,
     * 例如: 20000101010101.
     * </pre>
     * 
     * @return String 年月日时分秒字符串
     */
    public static String getCurrTime() {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(CURR_TIME);
        return df.format(_date);
    }

    public static String getCurrTime(String format) {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(format);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间毫秒数格式化成 yyyyMMddHHmmss 格式的字符串,
     * 例如 millis=2000-01-01 01:01:01,则返回 20000101010101.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 年月日时分秒字符串
     */
    public static String getCurrTime(long millis) {
        Date _date = new Date(millis);
        DateFormat df = new SimpleDateFormat(CURR_TIME);
        return df.format(_date);
    }

    /**
     * <pre>
     * 将时间实例格式化成  yyyyMMddHHmmss 格式的字符串,
     * 例如 date=2000-01-01 01:01:01,则返回 20000101010101,
     * 如果 date==null,则返回 null.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 年月日时分秒字符串
     */
    public static String getCurrTime(Date date) {
        if (null == date) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(CURR_TIME);
        return df.format(date);
    }

    /**
     * <pre>
     * 将日历实例格式化成 yyyyMMddHHmmss 格式的字符串,
     * 例如 calendar=2000-01-01 01:01:01,则返回 20000101010101,
     * 如果 calendar==null,则返回 null.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 年月日时分秒字符串
     */
    public static String getCurrTime(Calendar calendar) {
        if (null == calendar) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(CURR_TIME);
        return df.format(calendar.getTime());
    }

    /**
     * <pre>
     * 将 yyyyMMddHHmmss 格式的字符串转为时间实例,
     * 例如 str=20000101010101,则返回date=2000-01-01 01:01:01,
     * 如果 str==null 或者格式转换异常,则返回 null.
     * </pre>
     * 
     * @param str 年月日时分秒字符串
     * @return Date 时间实例
     */
    public static Date parseCurrTime(String str) {
        if (null == str || str.length() == 0) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(CURR_TIME);
        try {
            return df.parse(str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // =============================================================================
    // ------------ space util
    // =============================================================================

    /**
     * <pre>
     * 获取当前系统时间的指定分钟数后的日历实例,
     * 注意:minutes>=0 时表示当前之后的时间; minutes<0时表示当前之前的时间。
     * </pre>
     * 
     * @param minutes 分钟数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterMinuteDate(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minutes);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间毫秒数的指定分钟数后的日历实例,
     * 注意:minutes>=0 时表示当前之后的时间; minutes<0时表示当前之前的时间。
     * </pre>
     * 
     * @param minutes 分钟数
     * @param millis 时间毫秒数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterMinuteDate(int minutes, long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间实例的指定分钟数后的日历实例,
     * 如果 date==null,则返回 null.
     * 注意:minutes>=0 时表示当前之后的时间; minutes<0时表示当前之前的时间.
     * </pre>
     * 
     * @param minutes 分钟数
     * @param date 时间实例
     * @return Calendar 日历实例
     */
    public static Calendar getLaterMinuteDate(int minutes, Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar;
    }
    
    /**
     * <pre>
     * 获取指定时间毫秒数的指定分钟数后的日历实例,
     * 注意:seconds>=0 时表示当前之后的时间; seconds<0时表示当前之前的时间。
     * </pre>
     * 
     * @param seconds 秒数
     * @param millis 时间毫秒数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterSecondDate(int seconds, long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }
    
    /**
     * <pre>
     * 获取指定时间实例的指定秒数后的日历实例,
     * 如果 date==null,则返回 null.
     * 注意:minutes>=0 时表示当前之后的时间; seconds<0时表示当前之前的时间.
     * </pre>
     * 
     * @param seconds 秒数
     * @param date 时间实例
     * @return Calendar 日历实例
     */
    public static Calendar getLaterSecondDate(int seconds, Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    /**
     * <pre>
     * 获取当前系统时间的指定小时数后的日历实例,
     * 注意:hours>=0 时表示当前之后的时间; hours<0时表示当前之前的时间。
     * </pre>
     * 
     * @param hours 小时数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterHourDate(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hours);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间毫秒数的指定小时数后的日历实例,
     * 注意:hours>=0 时表示当前之后的时间; hours<0时表示当前之前的时间.
     * </pre>
     * 
     * @param hours 小时数
     * @param millis 时间毫秒数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterHourDate(int hours, long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(Calendar.HOUR, hours);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间实例的指定分钟数后的日历实例,
     * 如果 date==null,则返回 null.
     * 注意:hours>=0 时表示当前之后的时间; hours<0时表示当前之前的时间.
     * </pre>
     * 
     * @param hours 分钟数
     * @param date 时间实例
     * @return Calendar 日历实例
     */
    public static Calendar getLaterHourDate(int hours, Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        return calendar;
    }

    /**
     * <pre>
     * 获取当前系统时间的指定天数后的日历实例,
     * 注意:days>=0 时表示当前之后的时间; days<0时表示当前之前的时间。
     * </pre>
     * 
     * @param days 天数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterDayDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间毫秒数的指定天数后的日历实例,
     * 注意:days>=0 时表示当前之后的时间; days<0时表示当前之前的时间.
     * </pre>
     * 
     * @param days 天数
     * @param millis 时间毫秒数
     * @return Calendar 日历实例
     */
    public static Calendar getLaterDayDate(int days, long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(Calendar.DATE, days);
        return calendar;
    }

    /**
     * <pre>
     * 获取指定时间实例的指定分钟数后的日历实例,
     * 如果 date==null,则返回 null.
     * 注意:days>=0 时表示当前之后的时间; days<0时表示当前之前的时间.
     * </pre>
     * 
     * @param days 分钟数
     * @param date 时间实例
     * @return Calendar 日历实例
     */
    public static Calendar getLaterDayDate(int days, Date date) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar;
    }

    /**
     * <pre>
     * 获取当前系统时间对应的星期,
     * 注意:默认从1开始即代表星期一,7结束即代表星期天.
     * </pre>
     * 
     * @return String 星期
     */
    public static int getWeek() {
        Calendar calendar = Calendar.getInstance();
        return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * <pre>
     * 获取指定时间毫秒数对应的星期,
     * 注意:默认从1开始即代表星期一,7结束即代表星期天.
     * </pre>
     * 
     * @param millis 时间毫秒数
     * @return String 星期
     */
    public static int getWeek(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * <pre>
     * 获取指定时间毫秒数对应的星期,
     * 如果 date==null,则返回 -1.
     * 注意:默认从1开始即代表星期一,7结束即代表星期天.
     * </pre>
     * 
     * @param date 时间实例
     * @return String 星期
     */
    public static int getWeek(Date date) {
        if (null == date) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * <pre>
     * 获取指定时间毫秒数对应的星期,
     * 如果 calendar==null,则返回 -1.
     * 注意:默认从1开始即代表星期一,7结束即代表星期天.
     * </pre>
     * 
     * @param calendar 日历实例
     * @return String 星期
     */
    public static int getWeek(Calendar calendar) {
        if (null == calendar) {
            return -1;
        }

        return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * <pre>
     * 获取指定中文格式 yyyy-MM-dd HH:mm:ss 日期时间字符串对应的星期,
     * 如果 str==null 或 str.length==0 或者字符串的与中文格式模板不匹配,则返回 -1.
     * 注意:默认从1开始即代表星期一,7结束即代表星期天.
     * </pre>
     * 
     * @param str 中文格式日期时间字符串
     * @return String 星期
     */
    public static int getWeek(String str) {
        if (null == str || str.length() == 0) {
            return -1;
        }

        DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
        Date date;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
    
    /**
	 * <pre>
	 * 获取指定日期时间对应的星期,
	 * 如果 date==null 则返回 "".
	 * 注意:默认从1开始即代表周一,7结束即代表周日.
	 * </pre>
	 * 
	 * @param date 日期
	 * @return String 星期
	 */
    public static String getSimpleWeek(Date date){
    	return getSimpleWeek(getDate(date));
    }
    
    /**
	 * <pre>
	 * 获取指定中文格式 yyyy-MM-dd HH:mm:ss 日期时间字符串对应的星期,
	 * 如果 str==null 或 str.length==0 或者字符串的与中文格式模板不匹配,则返回 "".
	 * 注意:默认从1开始即代表周一,7结束即代表周日.
	 * </pre>
	 * 
	 * @param str
	 *            中文格式日期时间字符串
	 * @return String 星期
	 */
	public static String getSimpleWeek(String str) {
		if (null == str || str.length() == 0) {
			return "";
		}

		DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
		Date date;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return CN_WEEKS_SIMPLE[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}
	
	 
    /**
	 * <pre>
	 * 获取指定日期时间对应的星期,
	 * 如果 date==null 则返回 "".
	 * 注意:默认从1开始即代表周一,7结束即代表周日.
	 * </pre>
	 * 
	 * @param date
	 *            日期
	 * @return String 星期
	 */
    public static String getFullWeek(Date date){
    	return getFullWeek(getDate(date));
    }
    

	/**
	 * <pre>
	 * 获取指定中文格式 yyyy-MM-dd HH:mm:ss 日期时间字符串对应的星期,
	 * 如果 str==null 或 str.length==0 或者字符串的与中文格式模板不匹配,则返回 "".
	 * 注意:默认从1开始即代表星期一,7结束即代表星期日.
	 * </pre>
	 * 
	 * @param str
	 *            中文格式日期时间字符串
	 * @return String 星期
	 */
	public static String getFullWeek(String str) {
		if (null == str || str.length() == 0) {
			return "";
		}

		DateFormat df = new SimpleDateFormat(ZH_CN_DATE);
		Date date;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return CN_WEEKS_FULL[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

    /**
     * <pre>
     * 获取指定时间与当前系统时间的间隔秒数,
     * 计算方式公式: (afterDate.getTime() - newDate.getTime()) / 1000,
     * 注意:如果其中任何一个date == null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param date 时间实例
     * @return long 间隔秒数
     */
    public static long spaceSeconds(Date date) {
        if (null == date) {
            throw new RuntimeException("can't space second, have one date is null");
        }

        long spaceMillis = System.currentTimeMillis() - date.getTime();
        return spaceMillis / 1000;
    }

    /**
     * <pre>
     * 获取两个时间之间的间隔秒数,
     * 计算方式公式: (afterDate.getTime() - beforeDate.getTime()) / 1000,
     * 注意:如果其中任何一个date == null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param afterDate 时间实例
     * @param beforeDate 时间实例
     * @return long 间隔秒数
     */
    public static long spaceSeconds(Date afterDate, Date beforeDate) {
        if (null == afterDate || null == beforeDate) {
            throw new RuntimeException("can't space second, have one date is null.");
        }

        long spaceMillis = afterDate.getTime() - beforeDate.getTime();
        return spaceMillis / 1000;
    }

    /**
     * <pre>
     * 获取指定时间与当前系统时间的间隔分钟数,
     * 计算方式公式: (newDate.getTime() - date.getTime()) / 60000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param date 时间实例
     * @return long 间隔分钟数
     */
    public static long spaceMinutes(Date date) {
        if (null == date) {
            throw new RuntimeException("can't space minutes, have one date is null.");
        }

        long spaceMillis = System.currentTimeMillis() - date.getTime();
        return spaceMillis / 60000;
    }

    /**
     * <pre>
     * 获取两个时间之间的间隔分钟数,
     * 计算方式公式: (afterDate.getTime() - beforeDate.getTime()) / 60000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param afterDate 时间实例
     * @param beforeDate 时间实例
     * @return long 间隔分钟数
     */
    public static long spaceMinutes(Date afterDate, Date beforeDate) {
        if (null == afterDate || null == beforeDate) {
            throw new RuntimeException("can't space minutes, have one date is null.");
        }

        long spaceMillis = afterDate.getTime() - beforeDate.getTime();
        return spaceMillis / 60000;
    }

    /**
     * <pre>
     * 获取指定时间与当前系统时间的间隔小时数,
     * 计算方式公式: (newDate.getTime() - date.getTime()) / 3600000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param date 时间实例
     * @return long 间隔小时数
     */
    public static long spaceHours(Date date) {
        if (null == date) {
            throw new RuntimeException("can't space hours, have one date is null.");
        }

        long spaceMillis = System.currentTimeMillis() - date.getTime();
        return spaceMillis / 3600000;
    }

    /**
     * <pre>
     * 获取两个时间之间的间隔小时数,
     * 计算方式公式: (afterDate.getTime() - beforeDate.getTime()) / 3600000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param afterDate 时间实例
     * @param beforeDate 时间实例
     * @return long 间隔小时数
     */
    public static long spaceHours(Date afterDate, Date beforeDate) {
        if (null == afterDate || null == beforeDate) {
            throw new RuntimeException("can't space hours, have one date is null.");
        }

        long spaceMillis = afterDate.getTime() - beforeDate.getTime();
        double hours = spaceMillis / 3600000d;
        return (long) (Math.ceil(hours));
    }

    /**
     * <pre>
     * 获取指定时间与当前系统时间的间隔天数(忽略时分秒),
     * 计算方式公式: (newDate.getTime() - date.getTime()) / 86400000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param date 时间实例
     * @return long 间隔天数
     */
    public static long spaceDays(Date date) {
        Date _date = new Date(System.currentTimeMillis());
        return spaceDays(_date, date);
    }

    /**
     * <pre>
     * 获取两个时间之间的间隔天数(忽略时分秒),
     * 计算方式公式: (afterDate.getTime() - beforeDate.getTime()) / 86400000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param afterDate 时间实例
     * @param beforeDate 时间实例
     * @return long 间隔天数
     */
    public static long spaceDays(Date afterDate, Date beforeDate) {
        if (null == afterDate || null == beforeDate) {
            throw new RuntimeException("can't space days, have one date is null.");
        }

        Calendar afterCal = Calendar.getInstance();
        afterCal.setTime(afterDate);
        Calendar beforeCal = Calendar.getInstance();
        beforeCal.setTime(beforeDate);
        return spaceDays(afterCal, beforeCal);
    }

    /**
     * <pre>
     * 获取两个日历实例之间的间隔天数(忽略时分秒),
     * 计算方式公式: (afterCal.getTimeInMillis() - beforeCal.getTimeInMillis()) / 86400000,
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     *  afterDate 日历实例
     * beforeDate 日历实例
     * @return long 间隔天数
     */
    public static long spaceDays(Calendar afterCal, Calendar beforeCal) {
        if (null == afterCal || null == beforeCal) {
            throw new RuntimeException("can't space days, have one date is null.");
        }

        afterCal.set(Calendar.HOUR_OF_DAY, 0);
        afterCal.set(Calendar.MINUTE, 0);
        afterCal.set(Calendar.SECOND, 0);
        afterCal.set(Calendar.MILLISECOND, 0);

        beforeCal.set(Calendar.HOUR_OF_DAY, 0);
        beforeCal.set(Calendar.MINUTE, 0);
        beforeCal.set(Calendar.SECOND, 0);
        beforeCal.set(Calendar.MILLISECOND, 0);
        long spaceMillis = afterCal.getTimeInMillis() - beforeCal.getTimeInMillis();
        return spaceMillis / 86400000;
    }

    /**
     * <pre>
     * 判断 beforeDate 是在当前系统时间之前, 
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * beforeDate 时间实例
     * afterDate 时间实例
     * @return boolean boolean
     */
    public static boolean before(Date beforeDate) {
        if (null == beforeDate) {
            throw new RuntimeException("can't before, have one date is null");
        }

        // 计算两个时间的毫秒数差
        long millis = beforeDate.getTime() - System.currentTimeMillis();
        return (millis < 0);
    }

    /**
     * <pre>
     * 判断 beforeDate 是在 afterDate 时间之前, 
     * 注意:如果其中任何一个为null, 则抛出 RuntimeException.
     * </pre>
     * 
     * @param beforeDate 时间实例
     * @param afterDate 时间实例
     * @return boolean boolean
     */
    public static boolean before(Date beforeDate, Date afterDate) {
        if (null == beforeDate || null == afterDate) {
            throw new RuntimeException("can't before, have one date is null");
        }

        // 计算两个时间的毫秒数差
        long millis = beforeDate.getTime() - afterDate.getTime();
        return (millis < 0);
    }

    /**
     * <pre>
     * 比较两个日期时间字符串的大小,
     * 如果 beforeDate > afterDate ,则返回 1;
     * 如果 beforeDate = afterDate ,则返回 0;
     * 如果 beforeDate < afterDate ,则返回 -1.
     * 注意:必须格式一样方可返回争取的结果.
     * </pre>
     * 
     * @param beforeDate 第一个日期时间字符串
     * @param afterDate 第二个日期时间字符串
     * @return int
     */
    public static int dateCompare(String beforeDate, String afterDate) {
        int length = beforeDate.length();
        char c1, c2;
        for (int i = 0; i < length; i++) {
            c1 = beforeDate.charAt(i);
            c2 = afterDate.charAt(i);
            if (c1 - c2 > 0) {
                return 1;
            } else if (c1 - c2 < 0) {
                return -1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
    	// 设置提现记录进度数据
		JSONArray remarks = new JSONArray();
		JSONObject remark = new JSONObject();
		remark.put("text", "提现申请");
		remark.put("dateTime", DateUtil.getDatetime());
		remarks.add(remark);
		
		JSONObject remark1 = new JSONObject();
		remark1.put("text", "平台审核成功");
		remark1.put("dateTime", DateUtil.format(DateUtil.DEFAULT_DATE_TIME, DateUtil.getLaterSecondDate(1, remark.getDate("dateTime"))));
		remarks.add(remark1);
		System.out.println(remarks.toJSONString());
	}
}
