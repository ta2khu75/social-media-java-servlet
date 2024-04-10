/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ta2khu75.util;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author ta2khu75
 */
public class SendMail {

    final static String username = "boomkings474@gmail.com";
    final static String password = "plvy vcph ixtr fxec"; // Mật khẩu ứng dụng

    public static Message getMessage(String toMail) throws AddressException, MessagingException {

        // Cài đặt cấu hình email server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo một phiên làm việc
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // Tạo đối tượng MimeMessage
        Message message = new MimeMessage(session);
        // Đặt thông tin người gửi và người nhận
        message.setFrom(new InternetAddress("boomkings474@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toMail));
        return message;
    }

    public static boolean sendVerification(String toMail, String verification) {
        try {
            Message message = getMessage(toMail);
            message.setSubject("Mã xác nhận");
            message.setText("code: "+verification);
            // Gửi email
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean sendShare(String toMail, String url) {
        try {
            Message message = getMessage(toMail);
            message.setSubject("Chia sẽ video");
            message.setText(url);
            // Gửi email
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}