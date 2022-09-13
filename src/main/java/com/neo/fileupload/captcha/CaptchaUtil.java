package com.neo.fileupload.captcha;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
@Service
public class CaptchaUtil {
    public static Captcha createCaptcha(int height,int width){
        return new Captcha.Builder(width,height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(),new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }
    public static String encodeCaptcha(Captcha captcha){
        String img = null;
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg",bos);
            byte[] byteArray = Base64.getEncoder().encode(bos.toByteArray());
            img = new String(byteArray);

        }catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }

}
