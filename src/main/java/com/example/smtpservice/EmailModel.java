package com.example.smtpservice;

import java.util.Date;


public final class EmailModel {

	//TODO 兼容各类邮件 EmailAttachment  MultiPartEmail  HtmlEmail
	private Date receivedDate;
	private String from;
	private String to;
	private String subject;
	private String emailStr;
	private String filePath;

	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailStr() {
		return emailStr;
	}
	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "EmailModel{" +
				"receivedDate=" + receivedDate +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", subject='" + subject + '\'' +
				", emailStr='" + emailStr + '\'' +
				", filePath='" + filePath + '\'' +
				'}';
	}
}
