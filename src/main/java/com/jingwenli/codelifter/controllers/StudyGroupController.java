package com.jingwenli.codelifter.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jingwenli.codelifter.models.StudyGroup;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.StudyGroupService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class StudyGroupController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudyGroupService studyService;
	
	@GetMapping("/studygroups")
	public String studyGroupPage(@ModelAttribute("newStudyGroup") StudyGroup newStudyGroup ,Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        Long userId = (Long) currentUser.getId();
        
        model.addAttribute("currentUser", currentUser);
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        
		model.addAttribute("unassignedStudyGroups", studyService.getUnassignedUsers(userService.findById(userId)));
		model.addAttribute("assignedStudyGroups", studyService.getAssignedUsers(userService.findById(userId)));
		
		return "studyGroups.jsp";
	}
	
	@PostMapping("/studygroups/new")
	public String newGroup(@Valid @ModelAttribute("newStudyGroup") StudyGroup newStudyGroup, BindingResult result, Model model,
			Principal principal) {
		
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser);
        Long userId = (Long) currentUser.getId();
        
		if (result.hasErrors()) {
			model.addAttribute("unassignedStudyGroups", studyService.getUnassignedUsers(userService.findById(userId)));
			model.addAttribute("assignedStudyGroups", studyService.getAssignedUsers(userService.findById(userId)));
			return "studyGroups.jsp";
		}
		else {
			User user = userService.findById(userId);
			newStudyGroup.setLead(user);
			studyService.createStudyGroup(newStudyGroup);
			user.getStudyGroups().add(newStudyGroup);
			userService.updateUser(user);
			return "redirect:/studygroups";
		}
	}
	
	@GetMapping("/studygroups/edit/{id}")
	public String editStudyGroupForm(@PathVariable("id") Long id , Principal principal ,
			Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        model.addAttribute("currentUser", currentUser);
        
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		
		StudyGroup studyGroup = studyService.oneStudyGroup(id);
		model.addAttribute("editStudyGroup", studyGroup);
		
		if (!studyGroup.getLead().getId().equals((Long) currentUser.getId())) {
			return "redirect:/studygroups";
		}
		return "editStudyGroup.jsp";
	}
	
	@PutMapping("/studygroups/edit/{id}")
	public String editStudyGroup(@Valid @ModelAttribute("editStudyGroup") StudyGroup editStudyGroup, BindingResult result,
			@PathVariable("id") Long id, Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
		
		Long userId = (Long) currentUser.getId();
		User user = userService.findById(userId);
        
		if (result.hasErrors()) {
			return "editStudyGroup.jsp";
		}
		else {
			StudyGroup studyGroup = studyService.oneStudyGroup(id);
			editStudyGroup.setUsers(studyGroup.getUsers());
			editStudyGroup.setLead(user);
			studyService.updateStudyGroup(editStudyGroup);
			return "redirect:/studygroups";
		}
	}
	
	@DeleteMapping("/studygroups/delete/{id}")
	public String deleteStudyGroup(@PathVariable("id") Long id, Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        Long userId = (Long) currentUser.getId();
        User user = userService.findById(userId);
        
        studyService.deleteStudyGroup(id);
		model.addAttribute("unassignedStudyGroups", studyService.getUnassignedUsers(user));
		model.addAttribute("assignedStudyGroups", studyService.getAssignedUsers(user));
		
		return "redirect:/studygroups";
	}
	
	@PutMapping("/studygroups/join/{id}")
	public String joinStudyGroup(@PathVariable("id") Long id, Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        model.addAttribute("currentUser", currentUser);
        
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        
        Long userId = currentUser.getId();
        StudyGroup studyGroup = studyService.oneStudyGroup(id);
        User user = userService.findById(userId);
        
        user.getStudyGroups().add(studyGroup);
        userService.updateUser(user);
        
        model.addAttribute("user", userService.findById(userId));
		model.addAttribute("unassignedStudyGroups", studyService.getUnassignedUsers(user));
		model.addAttribute("assignedStudyGroups", studyService.getAssignedUsers(user));
		
		return "redirect:/studygroups";  
	}
	
	@PutMapping("/studygroups/leave/{id}")
	public String leaveStudyGroup(@PathVariable("id") Long id, Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        model.addAttribute("currentUser", currentUser);
        
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        
        Long userId = currentUser.getId();
        StudyGroup studyGroup = studyService.oneStudyGroup(id);
        User user = userService.findById(userId);
        
        user.getStudyGroups().remove(studyGroup);
        userService.updateUser(user);
        
        model.addAttribute("user", userService.findById(userId));
		model.addAttribute("unassignedStudyGroups", studyService.getUnassignedUsers(user));
		model.addAttribute("assignedStudyGroups", studyService.getAssignedUsers(user));
		
		return "redirect:/studygroups";
	}
	
	
	@GetMapping("/studygroups/{id}")
	public String oneStudyGroup(@PathVariable("id") Long id, Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        
        model.addAttribute("currentUser", currentUser);
        
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		
        model.addAttribute("studyGroup", studyService.oneStudyGroup(id));
		return "studyGroupDetails.jsp";
	}
	
}