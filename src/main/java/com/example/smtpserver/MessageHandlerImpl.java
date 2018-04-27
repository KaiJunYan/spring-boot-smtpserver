package com.example.smtpserver;

import com.example.eml.Eml;
import com.example.eml.EmlUtils;
import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.commons.mail.util.MimeMessageUtils;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.io.ReceivedHeaderStream;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.SharedInputStream;
import java.io.*;
import java.util.Properties;

/**
 * The from(), recipient() and data() methods will be called during each stage of SMTP.
 * Multiple MessageHandler are created if multiple messages are delivered with a single SMTP session.
 */
public class MessageHandlerImpl implements MessageHandler {
    public MessageContext context;

    public MessageHandlerImpl() {
    }

    MessageHandlerImpl(MessageContext context) {
        this.context = context;
    }

    @Override
    public void from(String from) {
        System.out.println("FROM:" + from);
    }

    @Override
    public void recipient(String recipient) {
        System.out.println("RECIPIENT:" + recipient);
    }

    @Override
    public void data(InputStream inputStream) {

        // 会导致 EmlUtils.parseInputStream(inputStream);  不再执行
//        System.out.println("MAIL DATA");
//        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
//        System.out.println(this.convertStreamToString(inputStream));
//        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

        //查看inputstream 类型
        //inputstream 类型:org.subethamail.smtp.io.ReceivedHeaderStream
        System.out.println(" inputstream 类型:" + inputStream.getClass().getName());


        try {
            EmlUtils.parseInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void done() {
        System.out.println("Finished");
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}


