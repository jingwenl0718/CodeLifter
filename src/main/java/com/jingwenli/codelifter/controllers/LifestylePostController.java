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

import com.jingwenli.codelifter.models.CommenterLifestylePost;
import com.jingwenli.codelifter.models.LifestylePost;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.CommenterLifestylePostService;
import com.jingwenli.codelifter.services.LifestylePostService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class LifestylePostController {

	@Autowired
	private LifestylePostService lifestylePostService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommenterLifestylePostService commenterLifestylePostService;
	
//	FIND ALL
	@GetMapping("/dashboard/lifestyleposts")
	public String allLifestyleposts(HttpSession session, Model model) {
		model.addAttribute("allLifestylePostList", lifestylePostService.findAllLifestylePosts());
		return "lifestylePostDashboard.jsp";
	}
	
//	FIND ONE
	@GetMapping("/lifestyleposts/{id}")
	public String showLifestylePostDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
		model.addAttribute("oneLifestylePost", lifestylePostService.findOneLifestylePost(id));
		model.addAttribute("newComment", new CommenterLifestylePost());
		model.addAttribute("allCommentList", commenterLifestylePostService.findAllComments());
		return "lifestylePostDetails.jsp";
	}
	
//	CREATE
	@GetMapping("/lifestyleposts/new")
    public String showNewLifestylePostForm(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("newLifestylePost", new LifestylePost());
        return "newLifestylePost.jsp";
    }
	
	@PostMapping("/lifestyleposts/new")
	public String processNewJobPost(@Valid @ModelAttribute("newLifestylePost") LifestylePost lifestylePost, BindingResult result) {
		if (result.hasErrors()) {
			return "newLifestylePost.jsp";
		} else {
			lifestylePostService.createNewLifestylePost(lifestylePost);
			return "redirect:/dashboard/lifestyleposts";
		}
	}
	
//	EDIT
	@GetMapping("/lifestyleposts/{id}/edit")
	public String showEditLifestylePostForm(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		 Long userId = (Long) currentUser.getId();
		 Long lifestylePostsCreatorId = lifestylePostService.findOneLifestylePost(id).getLifestylePostCreator().getId();
		 if (!userId.equals(lifestylePostsCreatorId)) {
		 	return "redirect:/dashboard/lifestyleposts";
		 }
		model.addAttribute("newLifestylePost", lifestylePostService.findOneLifestylePost(id));
		return "editLifestylePost.jsp";
	}
	
	@PutMapping("/lifestyleposts/{id}/edit")
	public String processEditLifestylePost(
			@Valid @ModelAttribute("newLifestylePost") LifestylePost lifestylePost, BindingResult result, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "editLifestylePost.jsp";
		} else {
			lifestylePostService.editLifestylePost(lifestylePost);
			return "redirect:/lifestyleposts/{id}";
		}
	}
	
//	DELETE
	@DeleteMapping("/lifestyleposts/{id}/delete")
	public String deleteLifestylePost(@PathVariable("id") Long id) {
		lifestylePostService.deleteLifestylePost(id);
		return "redirect:/dashboard/lifestyleposts";
	}
	
//	ADD COMMENT
	@PostMapping("/lifestyleposts/{lifestylepostId}/comment")
	public String addComment(@Valid @ModelAttribute("newComment") CommenterLifestylePost commenterLifestylePost, BindingResult result, @PathVariable("lifestylepostId") Long id, Model model, Principal principal) {
		if (result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser );
			model.addAttribute("oneLifestylePost", lifestylePostService.findOneLifestylePost(id));
			model.addAttribute("allCommentList", commenterLifestylePostService.findAllComments());
			return "lifestylePostDetails.jsp";
		} else {
			commenterLifestylePostService.createNewComment(commenterLifestylePost);
			return "redirect:/lifestyleposts/{lifestylepostId}";
		}
	}
}
