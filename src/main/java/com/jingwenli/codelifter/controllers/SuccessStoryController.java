package com.jingwenli.codelifter.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
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

import com.jingwenli.codelifter.models.CommenterSuccessStory;
import com.jingwenli.codelifter.models.SuccessStory;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.CommenterSuccessStoryService;
import com.jingwenli.codelifter.services.SuccessStoryService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class SuccessStoryController {

	
	@Autowired
	private SuccessStoryService successStoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommenterSuccessStoryService commenterSuccessStoryService;
	
//	FIND ALL
	@GetMapping("/dashboard/successstories")
	public String allSuccessStories(HttpSession session, Model model) {
		model.addAttribute("allSuccessStoryList", successStoryService.findAllSuccessStories());
		return "successStoryDashboard.jsp";
	}
	
//	FIND ONE
	@GetMapping("/successstories/{id}")
	public String showSuccessStoryDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
		model.addAttribute("oneSuccessStory", successStoryService.findOneSuccessStory(id));
		model.addAttribute("newComment", new CommenterSuccessStory());
		model.addAttribute("allCommentList", commenterSuccessStoryService.findAllComments());
		return "successStoryDetails.jsp";
	}
	
//	CREATE
	@GetMapping("/successstories/new")
    public String showNewSuccessStoryForm(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("newSuccessStory", new SuccessStory());
        return "newSuccessStory.jsp";
    }
	
	@PostMapping("/successstories/new")
	public String processNewSuccessStory(@Valid @ModelAttribute("newSuccessStory") SuccessStory successStory, BindingResult result) {
		if (result.hasErrors()) {
			return "newSuccessStory.jsp";
		} else {
			successStoryService.createNewSuccessStory(successStory);
			return "redirect:/dashboard/successstories";
		}
	}
	
//	EDIT
	@GetMapping("/successstories/{id}/edit")
	public String showEditSuccessStoryForm(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		 Long userId = (Long) currentUser.getId();
		 Long successStoriesCreatorId = successStoryService.findOneSuccessStory(id).getSuccessStoryCreator().getId();
		 if (!userId.equals(successStoriesCreatorId)) {
		 	return "redirect:/dashboard/successstories";
		 }
		model.addAttribute("newSuccessStory", successStoryService.findOneSuccessStory(id));
		return "editSuccessStory.jsp";
	}
	
	@PutMapping("/successstories/{id}/edit")
	public String processEditSuccessStory(
			@Valid @ModelAttribute("newSuccessStory") SuccessStory successStory, BindingResult result, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "editSuccessStory.jsp";
		} else {
			successStoryService.editSuccessStory(successStory);
			return "redirect:/successstories/{id}";
		}
	}
	
//	DELETE
	@DeleteMapping("/successstories/{id}/delete")
	public String deleteSuccessStory(@PathVariable("id") Long id) {
		successStoryService.deleteSuccessStory(id);
		return "redirect:/dashboard/successstories";
	}

//	ADD COMMENT
	@PostMapping("/successstories/{successstoryId}/comment")
	public String addComment(@Valid @ModelAttribute("newComment") CommenterSuccessStory commenterSuccessStory, BindingResult result, @PathVariable("successstoryId") Long id, Model model, Principal principal) {
		if (result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser );
			model.addAttribute("oneSuccessStory", successStoryService.findOneSuccessStory(id));
			model.addAttribute("allCommentList", commenterSuccessStoryService.findAllComments());
			return "successStoryDetails.jsp";
		} else {
			commenterSuccessStoryService.createNewComment(commenterSuccessStory);
			return "redirect:/successstories/{successstoryId}";
		}
	}
}
