package com.neo.fileupload.controller;

import com.neo.fileupload.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailSenderController {
    @Autowired
    private EmailSender emailSender;

    @GetMapping("/sendmail/{email}/{name}")
    public String sendEmail(@PathVariable String email,@PathVariable String name) throws MessagingException {
        emailSender.sendEmail(email,name);
        return "Mail send on : "+email;
    }
}
