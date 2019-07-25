package com.chenxianyong.utils;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2019/7/24.
 */
public class ImageUtilTest {

    //待处理图片
    public static final String imgPath = "L:\\婚纱照\\全部\\10.3 陈现勇 卢美娜 6套 政 筱雅\\JPG格式\\IMG_3676.jpg";
    public static final String ConvertImgPath = "L:\\婚纱照\\全部\\10.3 陈现勇 卢美娜 6套 政 筱雅\\IMG_3676.CR2";

    //水印图片
    public static final String markPath = "L:\\婚纱照\\全部\\10.3 陈现勇 卢美娜 6套 政 筱雅\\JPG格式\\IMG_3677.jpg";

    public static final String textMark = "AAAAAA";
    public static final String destPath = "E:\\IDEA_workspace\\Util\\1.jpg";
    //添加图片水印,物理存盘
    @Test
    public void addWaterMark(){
        ImageUtil.addWaterMark(imgPath, markPath, 1,1,0.6f, "jpg", destPath);
    }

    //添加图片水印,返回BufferedImage
    @Test
    public void addWaterMarkTest(){
        BufferedImage bufferedImage = ImageUtil.addWaterMark(imgPath, markPath, 1, 1, 0.6f);
        System.out.println(bufferedImage);
        System.out.println(bufferedImage.getRaster().getWidth());
    }

    @Test
    public void addTextMark(){
        //字体，风格，字号（磅）
        Font font = new Font("Courier",Font.BOLD, 100);
        ImageUtil.addTextMark(imgPath, textMark, font, Color.red, 100,100,0.8f,"jpg",destPath);
    }

    @Test
    public void addTextMark1(){
        Font font = new Font("Courier",Font.BOLD, 100);
        BufferedImage bufferedImage = ImageUtil.addTextMark(imgPath, textMark, font, Color.red, 100, 100, 0.8f);
        System.out.println(bufferedImage);
        System.out.println(bufferedImage.getRaster().getWidth());
    }

    @Test
    public void formatConvert(){
        ImageUtil.formatConvert(ConvertImgPath, "jpg", destPath);
    }

    @Test
    public void formatConvert1(){
    }

}