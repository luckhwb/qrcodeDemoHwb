package com;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by XHGA on 2017/11/18.
 */
public class MyQRcord {

    /**
     * 将jar包加入maven厂库
     * mvn install:install-file -Dfile=Qrcode.jar  -DgroupId=QRCode  -DartifactId=QRCode -Dversion=3.0 -Dpackaging=jar
     */
    public static void main(String[] args) {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcodeErrorCorrect('M');//纠错等级（分为L、M、H三个等级）
        qrcode.setQrcodeEncodeMode('B');//N代表数字，A代表a-Z，B代表其它字符
        qrcode.setQrcodeVersion(7);//版本
        //生成二维码中要存储的信息
        String qrData = "HEIHEI";
        //设置一下二维码的像素
        int width = 67 + 12 * 6;
        int height = 67 + 12 * 6;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘图
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);//清除下画板内容
        //设置下偏移量,如果不加偏移量，有时会导致出错。
        int pixoff = 2;
        byte[] d = qrData.getBytes();
        if(d.length > 0 && d.length <120){
            boolean[][] s = qrcode.calQrcode(d);
            for(int i=0;i<s.length;i++){
                for(int j=0;j<s.length;j++){
                    if(s[j][i]){
                        gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
                    }
                }
            }
        }
        gs.setColor(Color.CYAN);
        gs.fillRect(55, 55, 29, 29);
        gs.setColor(Color.red);
        gs.setFont(new Font("隶书", 0 ,20));
        gs.drawString("Q", 60, 75);
        gs.dispose();
        bufferedImage.flush();
        try {
            ImageIO.write(bufferedImage, "png", new File("D:/qrcode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
