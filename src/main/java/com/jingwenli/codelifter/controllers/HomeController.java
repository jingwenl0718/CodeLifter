package com.jingwenli.codelifter.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingwenli.codelifter.models.Contact;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.InterviewPrepPostService;
import com.jingwenli.codelifter.services.JobPostService;
import com.jingwenli.codelifter.services.LifestylePostService;
import com.jingwenli.codelifter.services.MailService;
import com.jingwenli.codelifter.services.StudyGroupService;
import com.jingwenli.codelifter.services.SuccessStoryService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	JobPostService jobPostService;
	
	@Autowired
	InterviewPrepPostService interviewPrepPostService;
	
	@Autowired
	LifestylePostService lifestylePostService;
	
	@Autowired
	SuccessStoryService successStoryService;
	
	@Autowired
	StudyGroupService studyService;
	
	@Autowired
	Environment env;
	
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        // 1
    	System.out.print(principal);
    	if (principal == null) {
            model.addAttribute("newContact", new Contact());
            model.addAttribute("successStory1", successStoryService.findOneSuccessStory((long) 8));
            model.addAttribute("successStory2", successStoryService.findOneSuccessStory((long) 6));
            model.addAttribute("successStory3", successStoryService.findOneSuccessStory((long) 5));
            model.addAttribute("interviewPost", interviewPrepPostService.findOneInterviewPrepPost((long) 11));
            model.addAttribute("lifestylePost", lifestylePostService.findOneLifestylePost((long) 8));
            model.addAttribute("recentJobPost", jobPostService.findRecentPost());
            model.addAttribute("recentInterviewPost", interviewPrepPostService.findRecentPost());
            model.addAttribute("recentLifestylePost", lifestylePostService.findRecentPost());
            model.addAttribute("recentSuccessStory", successStoryService.findRecentPost());
            model.addAttribute("recentStudyGroupPost", studyService.findRecentPost());
    		return "homePage.jsp";
    	}
    	else {
            String email = principal.getName();
            model.addAttribute("currentUser", userService.findByEmail(email));
            model.addAttribute("newContact", new Contact());
            model.addAttribute("successStory1", successStoryService.findOneSuccessStory((long) 8));
            model.addAttribute("successStory2", successStoryService.findOneSuccessStory((long) 6));
            model.addAttribute("successStory3", successStoryService.findOneSuccessStory((long) 5));
            model.addAttribute("interviewPost", interviewPrepPostService.findOneInterviewPrepPost((long) 11));
            model.addAttribute("lifestylePost", lifestylePostService.findOneLifestylePost((long) 8));
            model.addAttribute("recentJobPost", jobPostService.findRecentPost());
            model.addAttribute("recentInterviewPost", interviewPrepPostService.findRecentPost());
            model.addAttribute("recentLifestylePost", lifestylePostService.findRecentPost());
            model.addAttribute("recentSuccessStory", successStoryService.findRecentPost());
            model.addAttribute("recentStudyGroupPost", studyService.findRecentPost());
            return "homePage.jsp";
    	}
    }
    
    @PostMapping("/home/contactus")
    public String contactUs(@Valid @ModelAttribute("newContact") Contact newContact, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
            model.addAttribute("recentJobPost", jobPostService.findRecentPost());
            model.addAttribute("recentInterviewPost", interviewPrepPostService.findRecentPost());
            model.addAttribute("recentLifestylePost", lifestylePostService.findRecentPost());
            model.addAttribute("recentSuccessStory", successStoryService.findRecentPost());
            model.addAttribute("recentStudyGroupPost", studyService.findRecentPost());
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
    
    @GetMapping("/myhome/{id}")
    public String showMyAccount(@PathVariable("id") Long id, Model model, Principal principal) {
    	String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
        model.addAttribute("allJobPostList", jobPostService.findAllMyPosts(currentUser));
        model.addAttribute("allInterviewPostList", interviewPrepPostService.findAllMyPosts(currentUser));
        model.addAttribute("allLifestylePostList", lifestylePostService.findAllMyPosts(currentUser));
        model.addAttribute("allSuccessStoryList", successStoryService.findAllMyPosts(currentUser));
    	return "myhome.jsp";
    }
    
    
}
