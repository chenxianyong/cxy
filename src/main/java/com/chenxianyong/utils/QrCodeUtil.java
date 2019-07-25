package com.chenxianyong.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author: ChenXianyong
 * @description: 生成二维码工具类
 * @date: 2019/7/25 14:20
 */
public class QrCodeUtil {
    private static Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);
    /**
     * 二维码颜色 0xFFFF0000，红色
     */
    private static final int BLACK = 0xFF000000;
    /**
     * 二维码背景色。注：二维码颜色色差大，扫描快，但如果"BLACK'设置为黑色外其他颜色，可能无法扫描
     * 0xFF0000FF，蓝色
     */
    private static final int WHITE = 0xFFFFFFFF;
    /**
     * 字符集
     */
    private static final String CHARSET = "utf-8";
    /**
     * 格式
     */
    private static final String FORMAT_NAME = "JPG";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 200;
    /**
     * LOGO宽度
     */
    private static final int WIDTH = 60;
    /**
     * LOGO高度
     */
    private static final int HEIGHT = 60;
    /**
     * 二维码格式参数
     */
    private static final Hashtable<EncodeHintType, Object> HINTS = new Hashtable<EncodeHintType, Object>();

    static {
        /**
         * 二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
         * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；
         * 共有四级； 选择M，扫描速度快。
         */
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        /**
         * 设置字符集
         */
        HINTS.put(EncodeHintType.CHARACTER_SET, CHARSET);
        /**
         * 二维码边界空白大小 1,2,3,4 (4为默认,最大)
         */
        HINTS.put(EncodeHintType.MARGIN, 1);
    }

    /**
     * @param content  内容
     * @param destPath 存储地址
     * @Description 生成二维码（无LOGO）
     * @author ChenXianyong
     * @date 2019年7月9日
     */
    public static void encode(String content, String destPath) {
        encode(content, null, destPath);
    }

    /**
     * 生成二维码(含LOGO)
     *
     * @param content  内容
     * @param logo     LOGO地址
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String logo, String destPath) {
        /**
         * 创建二维码图片
         */
        BufferedImage image = createImage(content, logo);
        try {
            /**
             * 将二维码图片按照指定格式存入指定路径
             */
            ImageIO.write(image, FORMAT_NAME, new File(destPath));
        } catch (IOException e) {
            logger.error("二维码写入文件失败");
            e.printStackTrace();
        }
    }

    /**
     * @param content 内容
     * @param logo    LOGO地址
     * @return 图片
     * @Description 创建二维码
     * @author ChenXianyong
     * @date 2019年7月9日
     */
    private static BufferedImage createImage(String content, String logo) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE,
                    QRCODE_SIZE, HINTS);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }
            if (StringUtils.isBlank(logo)) {
                return image;
            }
            // 插入图片
            insertImage(image, logo);
            return image;
        } catch (WriterException e) {
            logger.error("生成二维码出错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param source 二维码图片
     * @param logo   LOGO图片地址
     * @Description 二维码中插入LOGO
     * @author ChenXianyong
     * @date 2019年7月9日
     */
    private static void insertImage(BufferedImage source, String logo) {
        File file = new File(logo);
        // 判断LOGO文件是否存在
        if (!file.exists()) {
            // 不存在
            return;
        }
        try {
            // 读取LOGO文件
            Image src = ImageIO.read(new File(logo));
            // 获取logo文件的宽高
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            // 判断宽高是否超出LOGO指定的宽高
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            // 按照设置的宽高，创建缩放版本的LOGO
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 获取图像
            Graphics g = tag.getGraphics();
            // 绘制缩小后的LOGO图
            g.drawImage(image, 0, 0, null);
            // 释放资源
            g.dispose();
            src = image;
            // 插入LOGO
            Graphics2D graph = source.createGraphics();
            int x = (QRCODE_SIZE - width) / 2;
            int y = (QRCODE_SIZE - height) / 2;
            graph.drawImage(src, x, y, width, height, null);
            Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
            graph.setStroke(new BasicStroke(3f));
            graph.draw(shape);
            graph.dispose();
        } catch (IOException e) {
            // ImageIOC流读取错误
            logger.error("插入LOGO的时候，ImageIO流读取错误");
            e.printStackTrace();
        }
    }

    /**
     * @param path 二维码图片地址
     * @return 内容
     * @Description 解析二维码(只能解析黑白色logo的)
     * @author ChenXianyong
     * @date 2019年7月9日
     */
    public static String decode(String path) {
        return decode(new File(path));
    }

    /**
     * @param file 二维码图片文件
     * @return 内容
     * @Description 解析二维码(只能解析黑白色logo的)
     * @author ChenXianyong
     * @date 2019年7月9日
     */
    public static String decode(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                return null;
            }
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result;
            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
            result = new MultiFormatReader().decode(bitmap, hints);
            String resultStr = result.getText();
            return resultStr;
        } catch (Exception e) {
            logger.error("解析二维码出错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author: ChenXianyong
     * @description: 解析彩色logo的二维码
     * @date: 2019/7/24 18:18
     * @param: [file]
     * @return: java.lang.String
     */
    public static String decode1(File file) {
        String result = null;
        try {

        } catch (Exception e) {

        }
        return result;
    }
}