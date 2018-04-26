package com.example.smtpserver;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;


/**
 * The MessageHandlerFactory is instantiated for every message to be exchanged in an SMTP conversation
 * which (MessageHandlerFactory) in turn instantiates a new instance of MessageHandler.
 * */
public class MessageHandlerFactoryImpl implements MessageHandlerFactory {

    @Override
    public MessageHandler create(MessageContext ctx) {
        return new MessageHandlerImpl(ctx);
    }

}
