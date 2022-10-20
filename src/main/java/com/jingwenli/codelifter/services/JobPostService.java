package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.JobPost;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.JobPostRepository;

@Service
public class JobPostService {

	@Autowired
	private JobPostRepository jobPostRepo;
	
//	FIND ALL
	public List<JobPost> findAllJobPosts() {
		return jobPostRepo.findAll();
	}
	
//	FIND ONE
	public JobPost findOneJobPost(Long id) {
		Optional<JobPost> optionalJobPost = jobPostRepo.findById(id);
		if (optionalJobPost.isPresent() ) {
			return optionalJobPost.get();
		} else {
			return null;
		}
	}
	
//	CREATE
	public JobPost createNewJobPost(JobPost jobPost) {
		return jobPostRepo.save(jobPost);
	}
	
//	EDIT
	public JobPost editJobPost(JobPost jobPost) {
		return jobPostRepo.save(jobPost);
	}
	
//	DELETE
	public void deleteJobPost(Long id) {
		jobPostRepo.deleteById(id);
	}
	
//	FIND MOST RECENT POST
	public JobPost findRecentPost() {
		List<JobPost> postList = jobPostRepo.findRecentPost();
		return postList.get(0);
	}
	
//	FIND ALL MY POSTS
	public List<JobPost> findAllMyPosts(User user) {
		return jobPostRepo.findByJobPostCreator(user);
	}
}
