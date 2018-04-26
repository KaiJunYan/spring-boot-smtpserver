package com.example.smtpserver;

import org.subethamail.smtp.server.SMTPServer;


/**
 * java 程序版本 smtp 服务器，拦截邮件并展示详细消息
 * */
public class MailServer {

    public void startServer() {
        SMTPServer smtpServer = new SMTPServer(new MessageHandlerFactoryImpl());
        smtpServer.setHostName("localhost");
        smtpServer.setPort(2525);
        smtpServer.start();
    }

    public static void main(String[] args) {
        MailServer mailServer = new MailServer();
        mailServer.startServer();
    }
}
