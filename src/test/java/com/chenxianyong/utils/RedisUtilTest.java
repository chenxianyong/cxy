package com.chenxianyong.utils;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class RedisUtilTest {
    @Test
    public void setTest() throws Exception {
        boolean setResult = RedisUtil.set("1", "111");
        System.out.println(setResult);
        if (setResult) {
            Boolean exists = RedisUtil.exists("1");
            System.out.println(exists);
            if (exists) {
                String value = RedisUtil.get("1");
                System.out.println(value);
                if (StringUtils.isNotBlank(value)) {
                    Long incr = RedisUtil.incr("1");
                    System.out.println(incr);
                    Long incrBy = RedisUtil.incrBy("1", 100);
                    System.out.println(incrBy);
                    Long decrBy = RedisUtil.decrBy("1", 100);
                    System.out.println(decrBy);
                    Long decr = RedisUtil.decr("1");
                    System.out.println(decr);
                    boolean setExpire = RedisUtil.setExpire("1", 100);
                    System.out.println(setExpire);
                    if (setExpire) {
                        Long ttl = RedisUtil.getTtl("1");
                        System.out.println(ttl + " second");
                    }
                    boolean expireAt = RedisUtil.setExpireAt("1", System.currentTimeMillis() + 1000L);
                    System.out.println(expireAt);
                    if (expireAt) {
                        Long ttl = RedisUtil.getTtl("1");
                        System.out.println(ttl + " unixTime for second");
                    }
                    Boolean existsKey = RedisUtil.exists("1");
                    if (existsKey) {
                        boolean del = RedisUtil.del("1");
                        String s = RedisUtil.get("1");
                        System.out.println(s);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        String s = "comboReservationCountKey";
//        for (int i = 100; i < 1000; i++) {
//            String key = s+i;
//            Boolean exists = RedisUtil.exists(key);
//            if (!exists) {
//                System.out.println(key + "不存在");
//                continue;
//            }
//            boolean del = RedisUtil.del(key);
//            if (del) {
//                System.out.println(key + "删除成功");
//            } else {
//                System.out.println(key + "删除失败");
//            }
//        }
        delSelectCountKey();
        System.out.println((System.currentTimeMillis() - start)/60000 + "分钟");
    }

    public static void delSelectCountKey() {
        String s = "comboSelectCountKey";

        for (int i = 0; i < 1000; i++) {
            String key = s+i;
            Boolean exists = RedisUtil.exists(key);
            if (!exists) {
                System.out.println(key + "不存在");
                continue;
            }
            boolean del = RedisUtil.del(key);
            if (del) {
                System.out.println(key + "删除成功");
            } else {
                System.out.println(key + "删除失败");
            }
        }
    }
}