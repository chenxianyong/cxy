package com.chenxianyong.utils;

import com.google.common.collect.Range;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Administrator on 2019/7/25.
 */
public class TimeUtilTest {
    @Test
    public void getStrByDateTest() throws Exception {
        String strByDate = TimeUtil.getStrByDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(strByDate);
    }

    @Test
    public void startOfDayTest() throws Exception {
        long startOfDay = TimeUtil.startOfDay(0);
        System.out.println(startOfDay);
        long day = TimeUtil.startOfDay(1);
        System.out.println(day);
        long ofDay = TimeUtil.startOfDay(-1);
        System.out.println(ofDay);
    }

    @Test
    public void startOfWeekTest() throws Exception {
        long startOfWeek = TimeUtil.startOfWeek();
        System.out.println(startOfWeek);
    }

    @Test
    public void remainingSecondsTodayTest() throws Exception {
        int remainingSecondsToday = TimeUtil.remainingSecondsToday();
        long l = TimeUtil.startOfDay(1) - 1 - System.currentTimeMillis();
        System.out.println(l);
        System.out.println(remainingSecondsToday);
    }

    @Test
    public void getTodayTimestampByHourTest() throws Exception {
        Long todayTimestampByHour = TimeUtil.getTodayTimestampByHour(10, 47);
        System.out.println(todayTimestampByHour);
    }

    @Test
    public void getCurrentHourTest() throws Exception {
        int currentHour = TimeUtil.getCurrentHour();
        System.out.println(currentHour);
    }

    @Test
    public void getDataTimeByMillisTest() throws Exception {
        String dataTimeByMillis = TimeUtil.getDataTimeByMillis(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dataTimeByMillis);
    }

    @Test
    public void getLocalDateRangeTest() throws Exception {
        Range<Long> localDateRange = TimeUtil.getLocalDateRange(LocalDate.now());
        System.out.println(localDateRange);
        Long lowerEndpoint = localDateRange.lowerEndpoint();
        System.out.println(lowerEndpoint);
        Long upperEndpoint = localDateRange.upperEndpoint();
        System.out.println(upperEndpoint);
    }

}