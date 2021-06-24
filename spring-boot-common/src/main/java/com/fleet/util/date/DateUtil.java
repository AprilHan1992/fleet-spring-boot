package com.fleet.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtil {

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String FMT_DATE = "yyyy-MM-dd";

    /**
     * 日期格式：HH:mm:ss
     */
    public static final String FMT_TIME = "HH:mm:ss";

    /**
     * 日期格式：HH:mm:ss.SSS
     */
    public static final String FMT_TIMES = "HH:mm:ss.SSS";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String FMT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String FMT_DATETIMES = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String FMT_S_DATE = "yyyyMMdd";

    /**
     * 日期格式：HHmmss
     */
    public static final String FMT_S_TIME = "HHmmss";

    /**
     * 日期格式：HHmmssSSS
     */
    public static final String FMT_S_TIMES = "HHmmssSSS";

    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static final String FMT_S_DATETIME = "yyyyMMddHHmmss";

    /**
     * 日期格式：yyyyMMddHHmmssSSS
     */
    public static final String FMT_S_DATETIMES = "yyyyMMddHHmmssSSS";

    /**
     * 日期（年）格式：yyyy-MM
     */
    public static final String FMT_YEAR = "yyyy";

    /**
     * 日期（年-月）格式：yyyy-MM
     */
    public static final String FMT_MONTH = "yyyy-MM";

    /**
     * 时间增加秒数
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date add(Date date, Integer seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 解析日期字符串
     *
     * @param date       日期字符串
     * @param dateFormat 日期格式化模式
     * @return 日期
     */
    public static Date parse(String date, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 格式化日期
     *
     * @param date       日期
     * @param dateFormat 日期格式化模式
     * @return 日期字符串
     */
    public static String format(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * 格式化日期
     *
     * @param millis     日期表示的毫秒数
     * @param dateFormat 日期格式化模式
     * @return 日期字符串
     */
    public static String format(long millis, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date(millis));
    }

    /**
     * 获取年份
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份 [1,12]
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日[1,31]
     *
     * @param date 日期
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期[1,7]
     *
     * @param date 日期
     * @return 星期，1表示星期一，2表示星期二......7表示星期日
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week == 1 ? 7 : week - 1;
    }

    /**
     * 获取24进制小时
     *
     * @param date 日期
     * @return 24进制小时
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     *
     * @param date 日期
     * @return 秒
     */
    public static int getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    /**
     * 获取毫秒
     *
     * @param date 日期
     * @return 毫秒
     */
    public static int getMillis(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MILLISECOND);
    }

    /**
     * 获取所在月份天数
     *
     * @param date 日期
     * @return 所在月份天数
     */
    public static int getMonthDays(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        return getMonthDays(year, month);
    }

    /**
     * 获取某年某月有多少天
     *
     * @param year  年
     * @param month 月
     * @return 某年某月的天数
     */
    public static int getMonthDays(int year, int month) {
        int days = 31;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = isLeapYear(year) ? 29 : 28;
                break;
        }
        return days;
    }

    /**
     * 获取n秒对应的毫秒数
     *
     * @param n 秒
     * @return 毫秒数
     */
    public static long getMillisOfSeconds(long n) {
        return (n * 1000);
    }

    /**
     * 获取n分钟对应的毫秒数
     *
     * @param n 分钟
     * @return 毫秒数
     */
    public static long getMillisOfMinutes(long n) {
        return (n * 60 * 1000);
    }

    /**
     * 获取n小时对应的毫秒数
     *
     * @param n 小时
     * @return 毫秒数
     */
    public static long getMillisOfHours(long n) {
        return (n * 60 * 60 * 1000);
    }

    /**
     * 获取n天对应的毫秒数
     *
     * @param n 天
     * @return 毫秒数
     */
    public static long getMillisOfDays(long n) {
        return (n * 24 * 60 * 60 * 1000);
    }

    /**
     * 获取n周对应的毫秒数
     *
     * @param n 周
     * @return 毫秒数
     */
    public static long getMillisOfWeeks(long n) {
        return (n * 7 * 24 * 60 * 60 * 1000);
    }

    /**
     * 获取两个日期之间相差的毫秒数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差毫秒数
     */
    public static long getDiffMillis(Date start, Date end) {
        return Math.abs(start.getTime() - end.getTime());
    }

    /**
     * 获取两个日期之间相差的秒数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差秒数
     */
    public static long getDiffSeconds(Date start, Date end) {
        return Math.abs((start.getTime() - end.getTime()) / 1000);
    }

    /**
     * 获取两个日期之间相差的分钟数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差分钟数
     */
    public static long getDiffMinutes(Date start, Date end) {
        return Math.abs((start.getTime() - end.getTime()) / 1000 / 60);
    }

    /**
     * 获取两个日期之间相差的小时数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差小时数
     */
    public static long getDiffHours(Date start, Date end) {
        return Math.abs((start.getTime() - end.getTime()) / 1000 / 60 / 60);
    }

    /**
     * 获取两个日期之间相差的天数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差天数
     */
    public static long getDiffDays(Date start, Date end) {
        return Math.abs((start.getTime() - end.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取两个日期之间相差的周数
     *
     * @param start 日期
     * @param end   日期
     * @return 相差周数
     */
    public static long getDiffWeeks(Date start, Date end) {
        return Math.abs((start.getTime() - end.getTime()) / 1000 / 60 / 60 / 24 / 7);
    }

    /**
     * 判断两个时间是否相等
     *
     * @param date        一个时间，可为null
     * @param anotherDate 另一个时间，可为null
     * @return true：相同，false：不相同
     */
    public static boolean isEqual(Date date, Date anotherDate) {
        if (date == null && anotherDate == null) {
            return true;
        } else if (date == null || anotherDate == null) {
            return false;
        } else {
            return date.compareTo(anotherDate) == 0;
        }
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date        一个时间，可为null
     * @param anotherDate 另一个时间，可为null
     * @return true：相同，false：不相同
     */
    public static boolean isDayEqual(Date date, Date anotherDate) {
        if (date == null && anotherDate == null) {
            return true;
        } else if (date == null || anotherDate == null) {
            return false;
        }
        String day = format(date, FMT_DATE);
        String anotherDay = format(anotherDate, FMT_DATE);
        return day.equals(anotherDay);
    }

    /**
     * 判断两个日期是否是同年同月
     *
     * @param date        一个时间，可为null
     * @param anotherDate 另一个时间，可为null
     * @return true：相同，false：不相同
     */
    public static boolean isMonthEqual(Date date, Date anotherDate) {
        if (date == null && anotherDate == null) {
            return true;
        } else if (date == null || anotherDate == null) {
            return false;
        }
        String month = format(date, FMT_MONTH);
        String anotherMonth = format(anotherDate, FMT_MONTH);
        return month.equals(anotherMonth);
    }

    /**
     * 判断两个日期是否是同年同月
     *
     * @param date        一个时间，可为null
     * @param anotherDate 另一个时间，可为null
     * @return true：相同，false：不相同
     */
    public static boolean isYearEqual(Date date, Date anotherDate) {
        if (date == null && anotherDate == null) {
            return true;
        } else if (date == null || anotherDate == null) {
            return false;
        }
        String year = format(date, FMT_YEAR);
        String anotherYear = format(anotherDate, FMT_YEAR);
        return year.equals(anotherYear);
    }

    /**
     * 判断两个时间对象比较情况，可比较null情况和继承类情况。
     *
     * @param date        一个时间，可为null
     * @param anotherDate 另一个时间，可为null
     * @return 0：相等，1：第一个时间大于第二个时间，2：第一个时间小于第二个时间
     */
    public static int compare(Date date, Date anotherDate) {
        if (date == null && anotherDate == null) {
            return 0;
        } else if (date == null) {
            return -1;
        } else if (anotherDate == null) {
            return 1;
        } else {
            return date.compareTo(anotherDate);
        }
    }

    /**
     * 判断给定的年份是否是闰年
     *
     * @param year 年份
     * @return true：是，false：否
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0);
    }

    /**
     * 获取当年开始日期
     *
     * @param date 当年的某一天日期
     * @return 当年开始日期，时分秒毫秒均为零
     */
    public static Date getYearFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        int year = getYear(date);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取当年末日期
     *
     * @param date 当年的某一天日期
     * @return 当年结束日期，时分秒毫秒均为零
     */
    public static Date getYearLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        int year = getYear(date);
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取当月开始日期
     *
     * @param date 日期
     * @return 当月开始日期，时分秒毫秒均为零
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = getResetCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月末日期
     *
     * @param date 日期
     * @return 当月结束日期，时分秒毫秒均为零
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = getResetCalendar(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取当周开始日期
     *
     * @param date 日期
     * @return 当周开始日期，时分秒毫秒均为零
     */
    public static Date getWeekFirstDay(Date date) {
        Calendar calendar = getResetCalendar(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);// 1 ~ 7
        if (week == 1) {// 周日
            calendar.add(Calendar.DAY_OF_WEEK, -6);
        } else {
            calendar.add(Calendar.DAY_OF_WEEK, 2 - week);
        }
        return calendar.getTime();
    }

    /**
     * 获取当周结束日期
     *
     * @param date 日期
     * @return 当周结束日期，时分秒毫秒均为零
     */
    public static Date getWeekLastDay(Date date) {
        Calendar calendar = getResetCalendar(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);// 1 ~ 7
        if (week != 1)// 周日
        {
            calendar.add(Calendar.DAY_OF_WEEK, 8 - week);
        }
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @param date 当天时间
     * @return 当天开始时间
     */
    public static Date getDayStart(Date date) {
        Calendar calendar = getResetCalendar(date);
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @param date 当天时间
     * @return 当天结束时间
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = getResetCalendar(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 将时分秒毫秒置为零
     *
     * @param date 日期
     * @return Calendar
     */
    public static Calendar getResetCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

    /**
     * 获取一天当前时间的所属文字描述
     *
     * @return 时间描述
     */
    public static String getNowTimeDesc() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 8) {
            return "早上";
        } else if (hour >= 8 && hour < 11) {
            return "上午";
        } else if (hour >= 11 && hour < 13) {
            return "中午";
        } else if (hour >= 13 && hour < 18) {
            return "下午";
        } else {
            return "晚上";
        }
    }
}
