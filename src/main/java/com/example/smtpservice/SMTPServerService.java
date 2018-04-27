package com.example.smtpservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SuppressWarnings("restriction")
@Service
public class SMTPServerService {

    @Value("${smtpserver.enabled}")
    String enabled = "";

    @Value("${smtpserver.hostName}")
    String hostName = "";

    @Value("${smtpserver.port}")
    String port = "";

    private SMTPServer smtpServer;

    public SMTPServerService() {
    }

    @PostConstruct
    public void start() {
        if (enabled.equalsIgnoreCase("true")) {
            SimpleMessageListenerImpl l = new SimpleMessageListenerImpl();

            //使用不同的Factory实现不同的功能
            //SMTPAuthHandlerFactory smtpAuthHandlerFactory = new SMTPAuthHandlerFactory();

            UsernamePasswordValidatorImpl validator = new UsernamePasswordValidatorImpl();
            SMTPAuthHandlerFactory2 smtpAuthHandlerFactory2 = new SMTPAuthHandlerFactory2(validator);

            smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(l), smtpAuthHandlerFactory2);
            smtpServer.setHostName(this.hostName);
            smtpServer.setPort(Integer.valueOf(port));
            smtpServer.start();
            System.out.println("****** SMTP Server is running for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
        } else {
            System.out.println("****** SMTP Server NOT ENABLED by settings ");
        }
    }

    @PreDestroy
    public void stop() {
        if (enabled.equalsIgnoreCase("true")) {
            System.out.println("****** Stopping SMTP Server for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
            smtpServer.stop();
        }
    }

    public boolean isRunning() {
        return smtpServer.isRunning();
    }
}