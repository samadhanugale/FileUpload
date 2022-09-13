package com.neo.fileupload.controller;

import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RestController
public class UniqueIDGenerator {

    @GetMapping("/uniqueID")
    public String generateUniqueId(){
        String lcNextRequestId = "";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime localDateTime = LocalDateTime.now();

        lcNextRequestId = dateTimeFormatter.format(localDateTime);
        lcNextRequestId = String.format("EA-%s-%d",lcNextRequestId,System.nanoTime() %1000000);
        return  lcNextRequestId;
    }
    @GetMapping("/uniqueOTP")
    public static String generateOTP(){
        Random random = new Random(System.currentTimeMillis());
            StringBuilder newOTP = new StringBuilder();

        for (int count = 0;count <4;++count){
            int number = random.nextInt(10);
            while(number == 0){
                newOTP.append(number);
            }
            newOTP.append(number);
        }
        return newOTP.toString();
    }
    @GetMapping("/referralCode/{codeLength}")
    public static String referralCode(@PathVariable int codeLength){
        DateFormat dateFormat = new SimpleDateFormat("yyMM");
        Calendar cl = Calendar.getInstance();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<codeLength;i++){
            char ch = chars[random.nextInt(chars.length)];
            sb.append(ch);
        }
        String output = dateFormat.format(cl.getTime())+sb.toString();
        return output;
    }

  public static void main(String[] args) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
      Date date = (Date)formatter.parse("12-December-2012");
      long mills = date.getTime();
    System.out.println(mills);
      Date dt =new Date(mills);
    System.out.println(dt);
  }
}
