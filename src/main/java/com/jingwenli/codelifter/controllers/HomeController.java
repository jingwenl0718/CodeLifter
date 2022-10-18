package com.jingwenli.codelifter.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingwenli.codelifter.models.Contact;
import com.jingwenli.codelifter.services.MailService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	Environment env;
	
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        // 1
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        model.addAttribute("newContact", new Contact());
        return "homePage.jsp";
    }
    
    @PostMapping("/home/contactus")
    public String contactUs(@Valid @ModelAttribute("newContact") Contact newContact, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		return "homePage.jsp";
    	}
    	
    	try {
    		String message = "<span style='font-weight:bold;'>Name: </span> " + newContact.getContactName();
    		message += "<br><span style='font-weight:bold;'>Phone Number: </span> " + newContact.getPhoneNumber();
    		message += "<br><span style='font-weight:bold;'>Email Address: </span> " + newContact.getFormAddress();
    		message += "<br><span style='font-weight:bold;'>Message: </span> <br> Dear CodeLifter Staff, <br>" + newContact.getFormMessage();
    		mailService.send(newContact.getFormAddress(), env.getProperty("USER_EMAIL"), newContact.getFormSubject(), message);
    		
    	} catch(Exception e) {
    		model.addAttribute("msg", e);
    	}
    	
    	return "redirect:/home";
    }
}