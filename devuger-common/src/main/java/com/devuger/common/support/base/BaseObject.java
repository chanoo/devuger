package com.devuger.common.support.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Component
public class BaseObject {
	
	protected JavaMailSender mailSender;
	protected VelocityEngine velocityEngine;

	@Resource protected ShaPasswordEncoder passwordEncoder;
	protected final String SALT = "5044825C3D0B63D4";

	protected final static String AES_SALT = "9198D7C5BE92B2AAD5D2E15ECD22E584";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Logger getLogger() {
		return logger;
	}
	
	@Autowired public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	@Autowired public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	protected String message(int code, String message)
	{
		return String.format(">> [%s][%d] %s", this.getClass().getMethods()[0].getName(), code, message);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
