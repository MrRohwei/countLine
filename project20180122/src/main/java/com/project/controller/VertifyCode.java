package com.project.controller;

import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName: VertifyCode
 * @Description: 一句话描述该类的功能
 * @Author: Looveh
 * @CreateDate: 2018/1/29 11:44
 * @Version: v1.0
 */
public class VertifyCode{

    /**
     * 图片宽度
     */
    public static final Integer IMAGE_WIDTH = 100;
    /**
     * 图片高度
     */
    public static final Integer IMAGE_HEIGHT = 30;
    /**
     * 验证码
     */
    public static final String CODE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    /**
     * 验证码个数
     */
    public static final int CODE_LEN = 4;

    public static String getVerifyCode(Integer verifySize){
        if(null == verifySize || 0 == verifySize){
            verifySize = CODE_LEN;
        }
        return getVerifyCode(verifySize,CODE_CHAR);
    }

    public static String getVerifyCode(Integer verifySize,String code){

        if(StringUtils.isEmpty(code)){
            code = CODE_CHAR;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < verifySize; i++) {
            Random random = new Random();
            int index = random.nextInt(code.length());
            sb.append(code.toCharArray()[index]);
        }
        return sb.toString();
    }

    /**
     * @方法说明
     * @method outVerifyCodeImage
     * @author Looveh
     * @date 2018/1/29 14:50
     * @param verifySize
     * @param code
     * @return java.lang.String
     * @desc [传入验证码生成验证码图片]
     */
    public static void outVerifyCodeImage(Integer verifySize,String code) throws IOException {
        String verifyCode;
        if(StringUtils.isEmpty(code)){
            verifyCode = getVerifyCode(verifySize);
        }else{
            verifyCode = getVerifyCode(verifySize,code);
        }
        //生成画布并确认宽高
        BufferedImage image = new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);//设置画布颜色
        graphics.fillRect(0,0,IMAGE_WIDTH,IMAGE_HEIGHT);//填充区域

        Font font = new Font("微软雅黑",Font.BOLD,16);
        graphics.setFont(font);
        graphics.setColor(new Color(new Random().nextInt(105),new Random().nextInt(200),new Random().nextInt(255)));
//        for (int i = 0; i < verifyCode.length(); i++) {
        graphics.drawString(verifyCode,30,20);
//        }

        //干扰线
        drawRandomLine(graphics);

        //噪点
        drawRandomPoint(image,graphics);
        FileOutputStream out = new FileOutputStream(new File("D:\\verifyCode.jpg"));

        ImageIO.write(image,"jpg",out);
    }

    /**
     * @方法说明
     * @method drawRandomLine
     * @author Looveh
     * @date 2018/1/29 15:02
     * @param g
     * @return void
     * @desc [干扰线]
     */
    public static void drawRandomLine(Graphics g){
        //设置颜色
        g.setColor(Color.BLUE);
        for (int i = 0; i < 10; i++) {
            int x1 = new Random().nextInt(IMAGE_WIDTH);
            int y1 = new Random().nextInt(IMAGE_HEIGHT);
            int x2 = new Random().nextInt(IMAGE_WIDTH);
            int y2 = new Random().nextInt(IMAGE_HEIGHT);
            g.drawLine(x1,y1,x2,y2);
        }
    }

    /**
     * @方法说明
     * @method drawRandomPoint
     * @author Looveh
     * @date 2018/1/29 15:01
     * @param g
     * @return void
     * @desc [噪点]
     */
    public static void drawRandomPoint(BufferedImage image, Graphics g){
        g.setColor(Color.gray);
        float yawpRate = 0.05f;// 噪点率
        int area = (int) (yawpRate * IMAGE_WIDTH * IMAGE_HEIGHT);
        for (int i = 0; i < area; i++) {
            int x = new Random().nextInt(IMAGE_WIDTH);
            int y = new Random().nextInt(IMAGE_HEIGHT);
            image.setRGB(x,y,1);
        }
    }


    public static void main(String[] args) {

        try {
            outVerifyCodeImage(4,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
