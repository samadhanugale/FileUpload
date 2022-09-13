package com.neo.fileupload.captcha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String email;
    private String captcha;
    private String hiddenCaptcha;
    private String realCaptcha;

}
