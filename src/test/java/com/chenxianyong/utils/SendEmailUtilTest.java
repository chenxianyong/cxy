package com.chenxianyong.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2019/7/25.
 */
public class SendEmailUtilTest {
    @Test
    public void sendTest() throws Exception {
        SendEmailUtil.send("cxy", "cxy");
    }

}