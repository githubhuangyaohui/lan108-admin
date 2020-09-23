package com.persistenthuang.lan108admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailTools {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    String sender;

    public String sendEmail(String email) {
        //邮件设置1：一个简单的邮件
        SimpleMailMessage message;
        String code;
        try {
            message = new SimpleMailMessage();
            message.setSubject("108lan");
            code = this.VerifyCode(6);
            message.setText("验证码：" + code);
            message.setTo(email);
            message.setFrom(sender);
            mailSender.send(message);
        } catch (Exception e) {
            return null;
        }
        return code;
    }

    private String VerifyCode(int n) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int ran1 = r.nextInt(10);
            sb.append(ran1);
        }
        return sb.toString();
    }
}
