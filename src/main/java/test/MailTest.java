package test;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 测试第三方邮箱验证
 */
public class MailTest {

    public static void main(String[] args) throws Exception {
        MailTest.sendMail("1358805979@qq.com","www.baidu.com");
    }

    /**
     * 邮件发送类
     * @param to    发送邮件给谁
     * @param validateUrl   代表要发送的超链接
     */
    public static void sendMail(String to, String validateUrl) throws Exception{

        /** 创建连接对象  连接到邮箱服务器 */
        Properties props = new Properties();

        /** 设置服务器地址 */
        props.put("mail.smtp.host","smtp.163.com");

        /** 设置协议 */
        props.put("mail.store.protocol","smtp");

        /** 设置端口 */
        props.put("mail.smtp.port",25);

        /**  */
        props.put("mail.smtp.auth","true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                /**
                 * 创建一个邮箱账号
                 */
                return new PasswordAuthentication("17858762819@163.com","hq0694878385");
            }
        });

        /** 创建邮件对象 */
        Message message = new MimeMessage(session);

        /**
         *  2.1设置发件人
         *  17858762819@163.com 是一个字符串，将字符串转换成地址
         */
        message.setFrom(new InternetAddress("17858762819@163.com"));

        /**
         *  2.2设置收件人
         */
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

        /**
         * 2.3设置邮件主题
         */
        message.setSubject("黄骞测试邮箱验证");
        message.setSentDate(new Date());

        /**
         * 设置邮件正文
         * ?validateUrl="+validateUrl+"：表示从外面传入一个验证超链接
         */
        message.setContent("<h1>测试验证</h1>邮箱验证请点击以下连接"+validateUrl,"text/html;charset=UTF-8");

        /**
         * 发送一封激活邮件
         */
        Transport.send(message);

    }
}
