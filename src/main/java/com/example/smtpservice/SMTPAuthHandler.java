package com.example.smtpservice;

import org.subethamail.smtp.AuthenticationHandler;
import org.subethamail.smtp.RejectException;

public final class SMTPAuthHandler implements AuthenticationHandler {

    @Override
    public String auth(String clientInput) throws RejectException {
        //TODO 身份验证 参考 LoginAuthenticationHandlerFactory
        return null;
    }

    @Override
    public Object getIdentity() {
        return null;
    }
}
