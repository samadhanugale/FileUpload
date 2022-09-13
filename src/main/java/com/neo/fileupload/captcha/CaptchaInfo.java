package com.neo.fileupload.captcha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaInfo {
    private int captchaId;
    private String captcha;
    private String hiddenCaptcha;
    private String reaCaptcha;
}
