package com.chenxianyong.utils;

import org.junit.Test;

import static com.chenxianyong.utils.EncodeOrDecodeUtil.*;

/**
 * @author: ChenXianyong
 * @description: 加密解密工具类的测试用例
 * @date: 2019/7/23 18:14
 */
public class EncodeOrDecodeUtilTest {

    public static final String encodeStr = "123";
    public static final byte[] encodeByte = new byte[123];
    public static final long[] encodeLong = new long[123];

    @Test
    public void encodeOrDecodeTest() {

        String md5EncodeStr = md5Encode(encodeStr);
        System.out.println("md5EncodeStr = " + md5EncodeStr);

        String md5EncodeByte = md5Encode(encodeByte);
        System.out.println("md5EncodeByte = " + md5EncodeByte);

        String md5EncodeLong = md5Encode(encodeLong);
        System.out.println("md5EncodeLong = " + md5EncodeLong);

        String base64EncodeResult = base64Encode(encodeStr);
        System.out.println("base64Encode = " + base64EncodeResult);

        String base64DecodeResult = base64Decode(base64EncodeResult);
        System.out.println("base64Decode = " + base64DecodeResult);

        String shaEncodeResult = shaEncode(encodeStr);
        System.out.println("shaEncode = " + shaEncodeResult);

        String string2SHA256Result = sha256Encode(encodeStr);
        System.out.println("sha256Encode = " + string2SHA256Result);

        String hmacEncodeResult = hmacEncode(encodeStr);
        System.out.println("hmacEncode = " + hmacEncodeResult);
    }
}
