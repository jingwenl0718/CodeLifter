package com.jingwenli.codelifter.controllers;

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

import com.jingwenli.codelifter.models.JobPost;
import com.jingwenli.codelifter.services.JobPostService;

@Controller
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;
	
//	FIND ALL
	@GetMapping("/dashboard/jobposts")
	public String allJobPosts(HttpSession session, Model model) {
		model.addAttribute("allJobPostList", jobPostService.findAllJobPosts());
		return "jobPostDashboard.jsp";
	}
	
//	CREATE
	@GetMapping("/jobposts/new")
	public String showNewJobPostForm(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		model.addAttribute("newJobPost", new JobPost());
		return "newJobPost.jsp";
	}
	
	@PostMapping("/jobposts/new")
	public String processNewProject(@Valid @ModelAttribute("newPost") JobPost jobPost, BindingResult result) {
		if (result.hasErrors()) {
			return "newJobPost.jsp";
		} else {
			jobPostService.createNewJobPost(jobPost);
			return "redirect:/dashboard/jobposts";
		}
	}
	
//	EDIT
	@GetMapping("/jobposts/{id}/edit")
	public String showEditProjectForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		 Long userId = (Long) session.getAttribute("userId");
		 Long jobPostsCreatorId = jobPostService.findOneJobPost(id).getJobPostCreator().getId();
		 if (!userId.equals(jobPostsCreatorId)) {
		 	return "redirect:/dashboard/jobposts";
		 }
		model.addAttribute("newJobPost", jobPostService.findOneJobPost(id));
		return "editJobPost.jsp";
	}
	
	@PutMapping("/jobposts/{id}/edit")
	public String processEditProject(
			@Valid @ModelAttribute("newJobPost") JobPost jobPost, BindingResult result, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "editJobPost.jsp";
		} else {
			jobPostService.editJobPost(jobPost);
			return "redirect:/jobposts/{id}";
		}
	}
	
//	FIND ONE
	@GetMapping("/jobposts/{id}")
	public String showJobPostDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("oneJobPost", jobPostService.findOneJobPost(id));
		return "jobPostDetails.jsp";
	}
	
//	DELETE
	@DeleteMapping("/jobposts/{id}/delete")
	public String deleteJobPost(@PathVariable("id") Long id) {
		jobPostService.deleteJobPost(id);
		return "redirect:/dashboard/jobposts";
	}
}