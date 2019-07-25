package com.chenxianyong.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import com.google.common.collect.Range;

/**
 * @author: ChenXianyong
 * @description: 时间处理的工具类
 * @date: 2019/7/25 14:20
 */
public class TimeUtil {
    /**
     * @param date       日期
     * @param formatDate 格式（yyyy-MM-dd HH:mm:ss）
     * @return
     * @Description 将制定日期转换成指定格式
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static String getStrByDate(Date date, String formatDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        String strDate = null;
        strDate = sdf.format(date);
        return strDate;
    }

    /**
     * @param days 天数，0表示当天，负数为多少天之前，正数为多少天之后
     * @return 时间戳
     * @Description 获取某一天的开始时间的时间戳
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static long startOfDay(int days) {
        return LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * @return 时间戳
     * @Description 获取本周周一的时间戳
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static long startOfWeek() {
        LocalDate now = LocalDate.now();
        int minusDays = 1 - now.getDayOfWeek().getValue();
        return now.plusDays(minusDays).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * @return 毫秒
     * @Description 当前时间距离今天结束剩余的时间
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static int remainingSecondsToday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) ((cal.getTimeInMillis() - System.currentTimeMillis()));
    }

    /**
     * @param hour   小时
     * @param minute 分钟
     * @return 时间戳
     * @Description 获取当天几点几分的时间戳
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static Long getTodayTimestampByHour(int hour, int minute) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expectationTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), hour,
                minute);
        return expectationTime.toEpochSecond(ZoneOffset.of("+8")) * 1000;
    }

    /**
     * @return 小时（24小时制）
     * @Description 获取当前的小时
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static int getCurrentHour() {
        return LocalTime.now().getHour();
    }

    /**
     * @param timestamp  时间戳
     * @param formatDate 格式
     * @return 时间（String）
     * @Description 将时间戳转成指定格式的时间
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static String getDataTimeByMillis(long timestamp, String formatDate) {
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * @param localDate
     * @return 时间戳
     * @Description 获取当天的开始时间和结束时间
     * @author ChenXianyong
     * @date 2019年7月8日
     */
    public static Range<Long> getLocalDateRange(LocalDate localDate) {
        long todayStart = LocalDateTime.of(localDate, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli();
        long todayEnd = LocalDateTime.of(localDate, LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli();
        return Range.closed(todayStart, todayEnd);
    }
}