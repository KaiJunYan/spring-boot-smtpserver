package com.example.smtpserver;

import com.example.smtpservice.SMTPServerService;
import org.apache.commons.mail.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@ContextConfiguration
//@SpringBootTest
public class SmtpserverApplicationTests {

    @Autowired
    SMTPServerService smtpServerService;

    @Test
    public void contextLoads() {
        smtpServerService.start();
    }

    @Test
    public void sendSimpleTestEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.setStartTLSEnabled(true);
        email.setFrom("user@gmail.com");
        email.setSubject("Simple email");
        email.setMsg("This is a simple plain text email :-)");
        email.addTo("foo@bar.com");
        //密码验证
		email.setAuthentication("admin","admin");
        email.send();
    }

    //使用这个测试发送附件的邮件
    @Test
    public void sendEmailWithAttachment() throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("src/main/resources/cloudServer.xlsx");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("xlsx file");
        attachment.setName("cloudServer.xlsx");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@example.org", "Me");
        email.setSubject("File attachment");
        email.setMsg("This email contains an enclosed file.");

        // Add the attachment
        email.attach(attachment);

        // Send the email
        email.send();
    }

    @Test
    public void sendHTMLFormattedEmail() throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@example.org", "Me");
        email.setSubject("HTML email");

        // Set the HTML message
        email.setHtmlMsg("<html><body>This is an <b>HTML</b> email.<br /><br /></body></html>");

        // Set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        // Send the email
        email.send();
    }

    @Test
    public void sendEmailWithBase64Subject() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.setFrom("spammy@example.org");
        email.addTo("foo@bar.com");
        email.setSubject("=?UTF-8?B?4pyIIEJvc3RvbiBhaXJmYXJlIGRlYWxzIC0gd2hpbGUgdGhleSBsYXN0IQ==?=");
        email.setMsg("Not really interesting, huh?");
        email.send();
    }

    @Test
    public void sendEmailToManyRecipientsWithTwoHeaders() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.setFrom("info@example.com");
        email.addTo("test1@example.com");
        email.addTo("test2@example.com");
        email.addHeader("Foo", "Bar");
        email.addHeader("Foo2", "Bar2");
        email.setSubject("Hi");
        email.setMsg("Just to check if everything is OK");
        email.send();
    }

    @Test
    public void sendEmailWithDots() throws EmailException {
        Email email = new SimpleEmail();
        email.setDebug(true);
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.setFrom("user@example.com");
        email.addTo("foo@example.com");
        email.setSubject("Two dots separated with a new line");
        email.setMsg(".\n.");
        email.send();
    }

}
