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

import com.jingwenli.codelifter.models.CommenterInterviewPrepPost;
import com.jingwenli.codelifter.models.CommenterJobPost;
import com.jingwenli.codelifter.models.InterviewPrepPost;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.CommenterInterviewPrepPostService;
import com.jingwenli.codelifter.services.InterviewPrepPostService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class InterviewPrepPostController {

	@Autowired
	private InterviewPrepPostService interviewPrepPostService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommenterInterviewPrepPostService commenterInterviewPrepPostService;
	
//	FIND ALL
	@GetMapping("/dashboard/interviewposts")
	public String allJobPosts(HttpSession session, Model model) {
		model.addAttribute("allInterviewPrepPostList", interviewPrepPostService.findAllInterviewPrepPosts());
		return "interviewPrepPostDashboard.jsp";
	}
	
//	CREATE
	@GetMapping("/interviewposts/new")
    public String showNewInterviewPrepPostForm(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("newInterviewPrepPost", new InterviewPrepPost());
        return "newInterviewPrepPost.jsp";
    }
	
	@PostMapping("/interviewposts/new")
	public String processNewInterviewPrepPost(@Valid @ModelAttribute("newInterviewPrepPost") InterviewPrepPost interviewPrepPost, BindingResult result) {
		if (result.hasErrors()) {
			return "newInterviewPrepPost.jsp";
		} else {
			interviewPrepPostService.createNewInterviewPrepPost(interviewPrepPost);
			return "redirect:/dashboard/interviewposts";
		}
	}
	
//	EDIT
	@GetMapping("/interviewposts/{id}/edit")
	public String showEditInterviewPrepPostForm(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		 Long userId = (Long) currentUser.getId();
		 Long interviewPrepPostsCreatorId = interviewPrepPostService.findOneInterviewPrepPost(id).getInterviewPrepPostCreator().getId();
		 if (!userId.equals(interviewPrepPostsCreatorId)) {
		 	return "redirect:/dashboard/interviewposts";
		 }
		model.addAttribute("newInterviewPrepPost", interviewPrepPostService.findOneInterviewPrepPost(id));
		return "editInterviewPrepPost.jsp";
	}
	
	@PutMapping("/interviewposts/{id}/edit")
	public String processEditInterviewPrepPost(
			@Valid @ModelAttribute("newInterviewPrepPost") InterviewPrepPost interviewPrepPost, BindingResult result, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "editInterviewPrepPost.jsp";
		} else {
			interviewPrepPostService.editInterviewPrepPost(interviewPrepPost);
			return "redirect:/interviewposts/{id}";
		}
	}
	
//	FIND ONE
	@GetMapping("/interviewposts/{id}")
	public String showJobPostDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
		model.addAttribute("oneInterviewPrepPost", interviewPrepPostService.findOneInterviewPrepPost(id));
		model.addAttribute("newComment", new CommenterInterviewPrepPost());
		model.addAttribute("allCommentList", commenterInterviewPrepPostService.findAllComments());
		return "interviewPrepPostDetails.jsp";
	}
	
//	DELETE
	@DeleteMapping("/interviewposts/{id}/delete")
	public String deleteInterviewPrepPost(@PathVariable("id") Long id) {
		interviewPrepPostService.deleteInterviewPrepPost(id);
		return "redirect:/dashboard/interviewposts";
	}
	
//	ADD COMMENT
	@PostMapping("/interviewposts/{interviewpreppostId}/comment")
	public String addComment(@Valid @ModelAttribute("newComment") CommenterInterviewPrepPost commenterInterviewPrepPost, BindingResult result, @PathVariable("interviewpreppostId") Long id, Model model, Principal principal) {
		if (result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser );
			model.addAttribute("oneInterviewPrepPost", interviewPrepPostService.findOneInterviewPrepPost(id));
			model.addAttribute("allCommentList", commenterInterviewPrepPostService.findAllComments());
			return "interviewPrepPostDetails.jsp";
		} else {
			commenterInterviewPrepPostService.createNewComment(commenterInterviewPrepPost);
			return "redirect:/interviewposts/{interviewpreppostId}";
		}
	}
}
