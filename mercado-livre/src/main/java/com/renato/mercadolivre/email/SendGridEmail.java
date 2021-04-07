package com.renato.mercadolivre.email;

public class SendGridEmail {
	
	private String fromEmail;
	private String fromName;
	private String replyTo;
	private String to;
	private String subject;
	private String body;
	private String contentType;
	
	public SendGridEmail() {
	}

	public SendGridEmail(String fromEmail, String fromName, String replyTo, String to, String subject, String body,
			String contentType) {
		this.fromEmail = fromEmail;
		this.fromName = fromName;
		this.replyTo = replyTo;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.contentType = contentType;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getContentType() {
		return contentType;
	}
}
