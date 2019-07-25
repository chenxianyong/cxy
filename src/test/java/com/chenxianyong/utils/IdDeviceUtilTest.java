package com.chenxianyong.utils;

import org.junit.Test;

import static com.chenxianyong.utils.IdDeviceUtil.*;

/**
 * Created by Administrator on 2019/7/24.
 */
public class IdDeviceUtilTest {
    @Test
    public void genIdTest() throws Exception {
        long genId = getId(IdDeviceUtil.IdType.USER);
        System.out.println(genId);
    }

    @Test
    public void generateIDTest() throws Exception {
        long l = generateID();
        System.out.println(l);
    }

    @Test
    public void generateLongIDTest() throws Exception {
        long l = generateLongId();
        System.out.println(l);
    }

}