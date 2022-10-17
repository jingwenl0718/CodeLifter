package com.jingwenli.codelifter.services;

public interface MailService {
	
	public void send(String fromAddress, String toAddress, String subject, String message) throws Exception;
}
