package com.example.smtpservice;

import org.subethamail.smtp.auth.LoginAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.UsernamePasswordValidator;

public class SMTPAuthHandlerFactory2 extends LoginAuthenticationHandlerFactory {

	/**
	 * @param helper
	 */
	public SMTPAuthHandlerFactory2(UsernamePasswordValidator helper) {
		super(helper);
 	}

}
