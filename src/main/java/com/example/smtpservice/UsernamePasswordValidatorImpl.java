package com.example.smtpservice;

import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;

public class UsernamePasswordValidatorImpl implements UsernamePasswordValidator {

    @Override
    public void login(String username, String password) throws LoginFailedException {
        //TODO  定义验证的方式
        if("admin".equals(username) && "admin".equals(password)){
            System.out.println("============验证通过==========");
        }else{
            System.out.println("============验证不通过==========");
            throw  new LoginFailedException();
        }
    }
}
