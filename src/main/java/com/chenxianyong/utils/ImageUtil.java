package com.chenxianyong.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author: ChenXianyong
 * @description: 图片处理工具类
 * @date: 2019/7/25 14:20
 */
public class ImageUtil {
	 /**
     * @Description 添加图片水印操作(物理存盘,自定义格式)
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param imgPath  待处理图片
     * @param markPath 水印图片
     * @param x        水印位于图片左上角的 x 坐标值
     * @param y        水印位于图片左上角的 y 坐标值
     * @param alpha    水印透明度 0.1f ~ 1.0f
     * @param format   添加水印后存储的格式
     * @param destPath 文件存放路径
     */
    public static void addWaterMark(String imgPath, String markPath, int x, int y, float alpha, String format,
               String destPath) {
          try {
               BufferedImage bufferedImage = addWaterMark(imgPath, markPath, x, y, alpha);
               ImageIO.write(bufferedImage, format, new File(destPath));
          } catch (Exception e) {
               throw new RuntimeException("添加图片水印异常");
          }
    }
    /**
     * @Description 添加图片水印操作,返回BufferedImage对象
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param imgPath  待处理图片
     * @param markPath 水印图片
     * @param x        水印位于图片左上角的 x 坐标值
     * @param y        水印位于图片左上角的 y 坐标值
     * @param alpha    水印透明度 0.1f ~ 1.0f
     * @return 处理后的图片对象
     */
    public static BufferedImage addWaterMark(String imgPath, String markPath, int x, int y, float alpha) {
          BufferedImage targetImage = null;
          try {
               // 加载待处理图片文件
               Image img = ImageIO.read(new File(imgPath));
               // 创建目标图象文件
               targetImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
               Graphics2D g = targetImage.createGraphics();
               g.drawImage(img, 0, 0, null);
               // 加载水印图片文件
               Image markImg = ImageIO.read(new File(markPath));
               g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
               g.drawImage(markImg, x, y, null);
               g.dispose();
          } catch (Exception e) {
               throw new RuntimeException("添加图片水印操作异常");
          }
          return targetImage;
    }
    /**
     * @Description 添加文字水印操作(物理存盘,自定义格式)
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param imgPath  待处理图片
     * @param text     水印文字
     * @param font     水印字体信息 不写默认值为宋体
     * @param color    水印字体颜色
     * @param x        水印位于图片左上角的 x 坐标值
     * @param y        水印位于图片左上角的 y 坐标值
     * @param alpha    水印透明度 0.1f ~ 1.0f
     * @param format   添加水印后存储的格式
     * @param destPath 文件存放路径
     */
    public static void addTextMark(String imgPath, String text, Font font, Color color, float x, float y, float alpha,
               String format, String destPath) {
          try {
               BufferedImage bufferedImage = addTextMark(imgPath, text, font, color, x, y, alpha);
               ImageIO.write(bufferedImage, format, new File(destPath));
          } catch (Exception e) {
               throw new RuntimeException("图片添加文字水印异常");
          }
    }
    /**
     * @Description 添加文字水印操作,返回BufferedImage对象
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param imgPath 待处理图片
     * @param text    水印文字
     * @param font    水印字体信息 不写默认值为宋体
     * @param color   水印字体颜色
     * @param x       水印位于图片左上角的 x 坐标值
     * @param y       水印位于图片左上角的 y 坐标值
     * @param alpha   水印透明度 0.1f ~ 1.0f
     * @return 处理后的图片对象
     */
    public static BufferedImage addTextMark(String imgPath, String text, Font font, Color color, float x, float y,
               float alpha) {
          BufferedImage targetImage = null;
          try {
               Font Dfont = (font == null) ? new Font("宋体", 20, 13) : font;
               Image img = ImageIO.read(new File(imgPath));
               // 创建目标图像文件
               targetImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
               Graphics2D g = targetImage.createGraphics();
               g.drawImage(img, 0, 0, null);
               g.setColor(color);
               g.setFont(Dfont);
               g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
               g.drawString(text, x, y);
               g.dispose();
          } catch (Exception e) {
               throw new RuntimeException("添加文字水印操作异常");
          }
          return targetImage;
    }
    /**
     * @Description 图片格式转化操作(文件物理存盘)
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param imgPath  原始图片存放地址
     * @param format   待转换的格式 jpeg,gif,png,bmp等
     * @param destPath 目标文件地址
     */
    public static void formatConvert(String imgPath, String format, String destPath) {
          try {
               BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
               ImageIO.write(bufferedImage, format, new File(destPath));
          } catch (IOException e) {
               throw new RuntimeException("文件格式转换出错");
          }
    }
    /**
     * @Description 图片格式转化操作返回BufferedImage对象
     * @author ChenXianyong
     * @date 2019年7月9日
     * @param bufferedImag BufferedImage图片转换对象
     * @param format       待转换的格式 jpeg,gif,png,bmp等
     * @param destPath     目标文件地址
     */
    public static void formatConvert(BufferedImage bufferedImag, String format, String destPath) {
          try {
               ImageIO.write(bufferedImag, format, new File(destPath));
          } catch (IOException e) {
               throw new RuntimeException("文件格式转换出错");
          }
    }
}