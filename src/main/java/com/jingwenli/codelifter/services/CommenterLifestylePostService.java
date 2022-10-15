package com.jingwenli.codelifter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.CommenterLifestylePost;
import com.jingwenli.codelifter.repositories.CommenterLifestylePostRepository;

@Service
public class CommenterLifestylePostService {

	@Autowired
	private CommenterLifestylePostRepository commenterLifestylePostRepo;
	
//	FIND ALL
	public List<CommenterLifestylePost> findAllComments() {
		return commenterLifestylePostRepo.findAll();
	}
	
//	CREATE
	public CommenterLifestylePost createNewComment(CommenterLifestylePost commenterLifestylePost) {
		return commenterLifestylePostRepo.save(commenterLifestylePost);
	}
}
