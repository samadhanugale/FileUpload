package com.neo.fileupload.service;

import javax.mail.MessagingException;

public interface EmailSender {
     String sendEmail(String email,String name) throws MessagingException;
}
