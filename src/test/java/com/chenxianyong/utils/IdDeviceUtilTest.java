package com.chenxianyong.utils;

import org.junit.Test;

import static com.chenxianyong.utils.IdDeviceUtil.*;

/**
 * Created by Administrator on 2019/7/24.
 */
public class IdDeviceUtilTest {
    @Test
    public void genIdTest() throws Exception {
        long genId = genId(IdDeviceUtil.IdType.USER);
        System.out.println(genId);
    }

    @Test
    public void generateIDTest() throws Exception {
        long l = generateID();
        System.out.println(l);
    }

    @Test
    public void generateLongIDTest() throws Exception {
        long l = generateLongID();
        System.out.println(l);
    }

}