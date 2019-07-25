package com.chenxianyong.utils;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

/**
 * @author ChenXianyong
 * @data 2019年7月19日 下午2:05:44
 * @description 加密解密
 */
public class EncodeOrDecodeUtil {

    /**
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     * @author ChenXianyong
     * @description MD5加密
     * @date 2019年7月12日
     */
    public static String md5Encode(String str) {
        return md5Encode(str.getBytes());
    }

    /**
     * @param bytes byte数组
     * @return 加密后的字符串
     * @author ChenXianyong
     * @description MD5加密
     * @data 2019年7月19日
     */
    public static String md5Encode(byte[] bytes) {
        MessageDigest md5 = MD5.get();
        md5.reset();
        md5.update(bytes);
        byte[] digest = md5.digest();
        return encodeHex(digest);
    }

    /**
     * @param array long类型的数组
     * @return 加密后的字符串
     * @author ChenXianyong
     * @description MD5加密
     * @data 2019年7月19日
     */
    public static String md5Encode(long[] array) {
        return md5Encode(long2Byte(array));
    }

    /**
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     * @Description BASE64加密
     * @author ChenXianyong
     * @date 2019年7月12日
     */
    public static String base64Encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(str.getBytes());
    }

    /**
     * @param base64Str 需要解密的字符串
     * @return 解密后的字符串
     * @Description BASE64解密
     * @author ChenXianyong
     * @date 2019年7月12日
     */
    public static String base64Decode(String base64Str) {
        String result = "";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64Str);
            result = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param str 要加密的字符串
     * @return 加密后的字符串
     * @Description SHA加密
     * @author ChenXianyong
     * @date 2019年7月12日
     */
    public static String shaEncode(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    /**
     * @param str 要加密的字符串
     * @return 加密后的字符串
     * @Description SHA256加密
     * @author ChenXianyong
     * @date 2019年7月12日
     */
    public static String sha256Encode(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    /**
     * @param str 要加密的字符串
     * @return 加密后的字符串
     * @Description HMAC加密
     * @author ChenXianyong
     * @date 2019年7月12日
     */
    public static String hmacEncode(String str) {
        String result = "";
        try {
            /**
             * 初始化KeyGenerator
             */
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            /**
             * 产生秘钥
             */
            SecretKey secretKey = keyGenerator.generateKey();
            /**
             * 获得秘钥(默认生成)
             */
            byte[] key = secretKey.getEncoded();
            /**
             * 还原秘钥
             */
            SecretKey secretKey2 = new SecretKeySpec(key, "HmacMD5");
            /**
             * 实例化mac
             */
            Mac mac = Mac.getInstance(secretKey2.getAlgorithm());
            /**
             * 初始化mac
             */
            mac.init(secretKey);
            byte[] hmacMd5Bytes = mac.doFinal(str.getBytes());
            result = Hex.encodeHexString(hmacMd5Bytes);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓--下面是私有方法--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */

    private static ThreadLocal<MessageDigest> MD5 = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (Exception e) {
            }
            return null;
        }
    };

    private static String encodeHex(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    private static byte[] long2Byte(long[] longArray) {
        byte[] byteArray = new byte[longArray.length * 8];
        for (int i = 0; i < longArray.length; i++) {
            byteArray[0 + 8 * i] = (byte) (longArray[i] >> 56);
            byteArray[1 + 8 * i] = (byte) (longArray[i] >> 48);
            byteArray[2 + 8 * i] = (byte) (longArray[i] >> 40);
            byteArray[3 + 8 * i] = (byte) (longArray[i] >> 32);
            byteArray[4 + 8 * i] = (byte) (longArray[i] >> 24);
            byteArray[5 + 8 * i] = (byte) (longArray[i] >> 16);
            byteArray[6 + 8 * i] = (byte) (longArray[i] >> 8);
            byteArray[7 + 8 * i] = (byte) (longArray[i] >> 0);
        }
        return byteArray;
    }

}
