package com.jingwenli.codelifter.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingwenli.codelifter.models.CommenterJobPost;
import com.jingwenli.codelifter.models.JobPost;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.services.CommenterJobPostService;
import com.jingwenli.codelifter.services.FileUploadUtil;
import com.jingwenli.codelifter.services.JobPostService;
import com.jingwenli.codelifter.services.UserService;

@Controller
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommenterJobPostService commenterJobPostService;
	
//	FIND ALL
	@GetMapping("/dashboard/jobposts")
	public String allJobPosts(HttpSession session, Model model) {
		model.addAttribute("allJobPostList", jobPostService.findAllJobPosts());
		return "jobPostDashboard.jsp";
	}
	
//	CREATE
	@GetMapping("/jobposts/new")
    public String showNewJobPostForm(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("newJobPost", new JobPost());
        return "newJobPost.jsp";
    }
	
	@PostMapping("/jobposts/new")
	public String processNewJobPost(@Valid @ModelAttribute("newJobPost") JobPost jobPost, BindingResult result, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if (result.hasFieldErrors("title") || result.hasFieldErrors("headline") || result.hasFieldErrors("description")) {
			return "newJobPost.jsp";
		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			jobPost.setImage(fileName);
			JobPost savedPost = jobPostService.createNewJobPost(jobPost);
	        String uploadDir = "src/main/resources/static/jobpost-image/" + savedPost.getId();
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			return "redirect:/dashboard/jobposts";
		}
	}
	
//	EDIT
	@GetMapping("/jobposts/{id}/edit")
	public String showEditJobPostForm(@PathVariable("id") Long id, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
        if (model.getAttribute("currentUser") == null) {
            return "redirect:/logout";
        }
		 Long userId = (Long) currentUser.getId();
		 Long jobPostsCreatorId = jobPostService.findOneJobPost(id).getJobPostCreator().getId();
		 if (!userId.equals(jobPostsCreatorId)) {
		 	return "redirect:/dashboard/jobposts";
		 }
		model.addAttribute("newJobPost", jobPostService.findOneJobPost(id));
		return "editJobPost.jsp";
	}
	
	@PutMapping("/jobposts/{id}/edit")
	public String processEditJobPost(
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
	public String showJobPostDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser );
		model.addAttribute("oneJobPost", jobPostService.findOneJobPost(id));
		model.addAttribute("newComment", new CommenterJobPost());
		JobPost foundPost = jobPostService.findOneJobPost(id);
		model.addAttribute("allCommentList", commenterJobPostService.findAllCommentsByPost(foundPost));
		return "jobPostDetails.jsp";
	}
	
//	DELETE
	@DeleteMapping("/jobposts/{id}/delete")
	public String deleteJobPost(@PathVariable("id") Long id) {
		jobPostService.deleteJobPost(id);
		return "redirect:/dashboard/jobposts";
	}

//	ADD COMMENT
	@PostMapping("/jobposts/{jobpostId}/comment")
	public String addComment(@Valid @ModelAttribute("newComment") CommenterJobPost commenterJobPost, BindingResult result, @PathVariable("jobpostId") Long id, Model model, Principal principal) {
		if (result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
	        model.addAttribute("currentUser", currentUser );
	        JobPost foundPost = jobPostService.findOneJobPost(id);
			model.addAttribute("oneJobPost", foundPost);
			model.addAttribute("allCommentList", commenterJobPostService.findAllCommentsByPost(foundPost));
			return "jobPostDetails.jsp";
		} else {
			commenterJobPostService.createNewComment(commenterJobPost);
			return "redirect:/jobposts/{jobpostId}";
		}
	}
}