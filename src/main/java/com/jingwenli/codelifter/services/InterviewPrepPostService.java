package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.InterviewPrepPost;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.InterviewPrepPostRepository;

@Service
public class InterviewPrepPostService {

	@Autowired
	private InterviewPrepPostRepository interviewPrepPostRepo;
	
//	FIND ALL
	public List<InterviewPrepPost> findAllInterviewPrepPosts() {
		return interviewPrepPostRepo.findAll();
	}
	
//	FIND ONE
	public InterviewPrepPost findOneInterviewPrepPost(Long id) {
		Optional<InterviewPrepPost> optionalInterviewPrepPost = interviewPrepPostRepo.findById(id);
		if (optionalInterviewPrepPost.isPresent() ) {
			return optionalInterviewPrepPost.get();
		} else {
			return null;
		}
	}
	
//	CREATE
	public InterviewPrepPost createNewInterviewPrepPost(InterviewPrepPost interviewPrepPost) {
		return interviewPrepPostRepo.save(interviewPrepPost);
	}
	
//	EDIT
	public InterviewPrepPost editInterviewPrepPost(InterviewPrepPost interviewPrepPost) {
		return interviewPrepPostRepo.save(interviewPrepPost);
	}
	
//	DELETE
	public void deleteInterviewPrepPost(Long id) {
		interviewPrepPostRepo.deleteById(id);
	}
	
//	FIND MOST RECENT POST
	public InterviewPrepPost findRecentPost() {
		List<InterviewPrepPost> postList = interviewPrepPostRepo.findRecentPost();
		return postList.get(0);
	}
	
//	FIND ALL MY POSTS
	public List<InterviewPrepPost> findAllMyPosts(User user) {
		return interviewPrepPostRepo.findByInterviewPrepPostCreator(user);
	}
}
