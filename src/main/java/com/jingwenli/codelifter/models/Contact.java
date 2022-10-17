package com.jingwenli.codelifter.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Contact {
	
	@NotBlank(message="Name field is required")
	private String contactName;
	
	@Email(message="Please enter a valid email!")
	@NotBlank(message="Email address is required")
	private String formAddress;
	
	@NotBlank(message="Phone number is required")
	private String phoneNumber;
	
	@NotBlank(message="Subject is required")
	private String formSubject;
	
	@NotBlank(message="Message is required")
	private String formMessage;
	
	public Contact() {}

	public String getFormAddress() {
		return formAddress;
	}

	public void setFormAddress(String formAddress) {
		this.formAddress = formAddress;
	}

	public String getFormSubject() {
		return formSubject;
	}

	public void setFormSubject(String formSubject) {
		this.formSubject = formSubject;
	}

	public String getFormMessage() {
		return formMessage;
	}

	public void setFormMessage(String formMessage) {
		this.formMessage = formMessage;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
