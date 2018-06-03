package com.higo.tinklabstest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 获取日期和时间的工具类
 * <p>
 * Created on 2017/11/8.
 *
 * @author sharkliu
 * @version 1.0
 */
public class TimeUtil {
    private final static String PATTERN = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_DATETIME_MILLS = "yyyy-MM-dd HH:mm:ss.SSS";
    // public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME_MILLS = "HH:mm:ss.SSS";
    public static final String FORMAT_TIME = "HH:mm:ss";

    public static String getCurrentDateNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        return sdf.format(new Date());
    }
    public static String getCurrentTimeStamp(){
        return System.currentTimeMillis()+"";
    }
    public static String getDate2(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return getDate(calendar.getTime());
    }

    public static String getDate(Calendar calendar) {
        return getDate(calendar.getTime());
    }

    /*获取日期时间*/
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    public static String getDateTime(String format) {
        return getDateTime(format, new Date());
    }

    public static String getDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            return getDateTime(FORMAT_DATETIME_MILLS, date);
        }
    }

    public static String getDateTime(String format, Date date) {
        return getDateTime(format, Locale.getDefault(), date);
    }

    public static String getDateTime(String format, Locale locale, Date date) {
        return new SimpleDateFormat(format, locale).format(date);
    }

    /*获取日期*/
    public static String getDate() {
        return getDate(new Date());
    }

    public static String getDate(String format) {
        return getDate(format, new Date());
    }

    public static String getDate(Date date) {
        if (date == null) {
            return "";
        }
        return getDate(FORMAT_DATE, date);
    }

    public static String getDate(String format, Date date) {
        if (date == null) {
            return "";
        }
        return getDate(format, Locale.getDefault(), date);
    }

    public static String getDate(String format, Locale locale, Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format, locale).format(date);
    }

    /*获取时间*/
    public static String getTime() {
        return getTime(new Date());
    }

    public static String getTime(String format) {
        return getTime(format, new Date());
    }

    public static String getTime(Date date) {
        if (date == null) {
            return "";
        }
        return getTime(FORMAT_TIME, date);
    }

    public static String getTime(String format, Date date) {
        if (date == null) {
            return "";
        }
        return getTime(format, Locale.getDefault(), date);
    }

    public static String getTime(String format, Locale locale, Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format, locale).format(date);
    }

    /**
     * 根据年月日转化成格式化的日期字符串
     *
     * @param year  年份 >=1900
     * @param month 月份 1-12
     * @param day   日期
     * @return 格式化日期字符串
     */
    public static String getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return getDate(calendar.getTime());
    }

    /**
     * 获取某一天往前推指定天数的日期字符串
     *
     * @param year  年份 >=1900
     * @param month 月份 1-12
     * @param day   日期
     * @param days  往前推的天数
     * @return 格式化日期字符串
     */
    public static String getBeforeDate(int year, int month, int day, int days) {
        return getAfterDate(year, month, day, -days);
    }

    /**
     * 获取当前时间的前几天的日期
     *
     * @param distanceDay
     * @return
     */
    public static String getBeforeDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat(FORMAT_DATE);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取某一天往后推指定天数的日期字符串
     *
     * @param year  年份 >=1900
     * @param month 月份 1-12
     * @param day   日期
     * @param days  往后推的天数
     * @return 格式化日期字符串
     */
    public static String getAfterDate(int year, int month, int day, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        int date = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, date + days);
        return getDate(calendar.getTime());
    }
}
