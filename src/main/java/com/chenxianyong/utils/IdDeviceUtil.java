package com.chenxianyong.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IdDeviceUtil {
	private static final long ONE_STEP = 10;
    private static final long BASE = 0;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime4long = System.currentTimeMillis();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    private static long lastTime = new Long(format.format(new Date()));
    private static short lastCount = 0;
    public static Calendar nowCalendar = Calendar.getInstance();
    private final long time;
    private final short count;

    /**
     * @Description 生成id
     * @author ChenXianyong
     * @date 2019年7月8日
     * @param idType
     * @return
     */
    public static long genId(IdType idType) {
          long generateLongID = generateLongID();
          String id = generateLongID + "";
          String idResult = idType.getIndex() + id.substring(2);
          Long valueOf = Long.valueOf(idResult);
          if (valueOf == null) {
               genId(idType);
          }
          return valueOf;
    }
    /**
     * 构造函数初始话相应的参数
     *
     * @param lastTime4long
     */
    public IdDeviceUtil(long lastTime4long) {
          LOCK.lock();
          try {
               if (lastCount == ONE_STEP) {
                    boolean done = false;
                    while (!done) {
                          long now = System.currentTimeMillis();
                          if (now == lastTime4long) {
                               try {
                                    Thread.sleep(1);
                               } catch (InterruptedException e) {
                               }
                               continue;
                          } else {
                               lastTime4long = now;
                               lastCount = 0;
                               done = true;
                          }
                    }
               }
               time = lastTime4long;
               count = lastCount++;
          } finally {
               LOCK.unlock();
          }
    }
    public IdDeviceUtil() {
          LOCK.lock();
          try {
               if (lastCount == ONE_STEP) {
                    boolean done = false;
                    while (!done) {
                          long now = new Long(format.format(new Date()));
                          if (now == lastTime) {
                               try {
                                    Thread.sleep(1);
                               } catch (InterruptedException e) {
                               }
                               continue;
                          } else {
                               lastTime = now;
                               lastCount = 0;
                               done = true;
                          }
                    }
               }
               time = lastTime;
               count = lastCount++;
          } finally {
               LOCK.unlock();
          }
    }
    /**
     * 生成格式是201010151010101000的long型ID
     *
     * @return
     */
    public static long generateID() {
    	IdDeviceUtil idGenerator = new IdDeviceUtil();
          return (idGenerator.time) + idGenerator.count;
    }
    /**
     * @Description 1562581287198
     * @author ChenXianyong
     * @date 2019年7月8日
     * @return
     */
    public static long generateLongID() {
    	IdDeviceUtil idGenerator = new IdDeviceUtil(lastTime4long);
          return (BASE + idGenerator.time) + idGenerator.count;
    }
    public enum IdType {
          USER(31), ORDER(32), COUPON(33), IMAGE(34);
          private int index;
          private IdType(int index) {
               this.index = index;
          }
          public int getIndex() {
               return index;
          }
    }
}