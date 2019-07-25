package com.chenxianyong.utils;

import org.junit.Test;

import java.io.File;

/**
 * Created by Administrator on 2019/7/24.
 */
public class QRCodeUtilTest {

    public static final String content = "123456";
    public static final String destPath = "E:\\IDEA_workspace\\Util\\2.jpg";
    public static final String logoPath = "E:\\IDEA_workspace\\Util\\1.jpg";

    @Test
    public void encode() throws Exception {
        QRCodeUtil.encode(content, "E:\\IDEA_workspace\\Util\\1.jpg");
    }

    @Test
    public void encode1() throws Exception {
        QRCodeUtil.encode(content, logoPath, destPath);
    }

    @Test
    public void decode() throws Exception {
        String decode = QRCodeUtil.decode("E:\\IDEA_workspace\\Util\\2.jpg");
        System.out.println(decode);
    }

    @Test
    public void decode1() throws Exception {
        String decode = QRCodeUtil.decode(new File("E:\\IDEA_workspace\\Util\\1.jpg"));
        System.out.println(decode);
    }

}