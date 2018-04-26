package com.example.smtpserver;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The from(), recipient() and data() methods will be called during each stage of SMTP.
 * Multiple MessageHandler are created if multiple messages are delivered with a single SMTP session.
 * */
public class MessageHandlerImpl implements MessageHandler {
    public MessageContext context;

    public MessageHandlerImpl() {
    }

    MessageHandlerImpl(MessageContext context) {
        this.context = context;
    }

    @Override
    public void from(String from) {
        System.out.println("FROM:"+from);
    }

    @Override
    public void recipient(String recipient) {
        System.out.println("RECIPIENT:"+recipient);
    }

    @Override
    public void data(InputStream data) {
        System.out.println("MAIL DATA");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(this.convertStreamToString(data));
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
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


