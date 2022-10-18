package com.jingwenli.codelifter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.CommenterInterviewPrepPost;
import com.jingwenli.codelifter.models.InterviewPrepPost;
import com.jingwenli.codelifter.repositories.CommenterInterviewPrepPostRepository;

@Service
public class CommenterInterviewPrepPostService {

	@Autowired
	private CommenterInterviewPrepPostRepository commenterInterviewPrepPostRepo;
	
//	FIND ALL
	public List<CommenterInterviewPrepPost> findAllCommentsByPost(InterviewPrepPost interviewPrepPost) {
		return commenterInterviewPrepPostRepo.findAllByInterviewPrepPost(interviewPrepPost);
	}
	
//	CREATE
	public CommenterInterviewPrepPost createNewComment(CommenterInterviewPrepPost commenterInterviewPrepPost) {
		return commenterInterviewPrepPostRepo.save(commenterInterviewPrepPost);
	}
}
