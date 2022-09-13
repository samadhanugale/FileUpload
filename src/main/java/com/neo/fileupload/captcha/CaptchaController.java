package com.neo.fileupload.captcha;

import cn.apiclub.captcha.Captcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
public class CaptchaController {

    @GetMapping("/captcha")
    public User registerUser(){
        User user = new User();
        return getCaptcha(user);

    }
    @GetMapping("/captcha2")
    public CaptchaInfo createCaptcha(){
        CaptchaInfo captchaInfo = new CaptchaInfo();
        return getCaptchaInfo(captchaInfo);

    }
    private User getCaptcha(User user){
        Captcha captcha = CaptchaUtil.createCaptcha(70,240);
        user.setHiddenCaptcha(captcha.getAnswer().toString());
        user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
        return user;
    }
    private CaptchaInfo getCaptchaInfo(CaptchaInfo user){
        Captcha captcha = CaptchaUtil.createCaptcha(70,240);
        BufferedImage image = captcha.getImage();
        user.setHiddenCaptcha(captcha.getAnswer().toString());
        user.setReaCaptcha(CaptchaUtil.encodeCaptcha(captcha));
        user.setCaptcha(captcha.getTimeStamp().toString());
        return user;
    }
}
