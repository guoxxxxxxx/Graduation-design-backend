package com.hebust.utils.mail;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 使用邮箱发送随机验证码
 */
public class SendRandomCode {
    // 发件人邮箱
    private static final String sendEmail = "1785158284@qq.com";
    // 授权码
    private static final String authorityCode = "lejnybgpfbkdcgif";
    // 指定发邮件的主机
    private static final String host = "smtp.qq.com";

    /**
     * 发送验证码到邮箱
     * @param randomCode 验证码
     * @param targetEmail 收件人邮箱地址
     */
    public static void sendMessage(String randomCode, String targetEmail){

        // 系统属性
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        // 获取默认会话对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication(sendEmail, authorityCode);
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // 头部字段
            message.setFrom(new InternetAddress(sendEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail));
            message.setSubject("【验证码】大学生互助平台");
            message.setText("尊敬的用户您好，您本次所需的验证码为: " + randomCode);
            // 发送邮件
            Transport.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
