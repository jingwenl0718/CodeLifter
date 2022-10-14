package com.jingwenli.codelifter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.CommenterJobPost;
import com.jingwenli.codelifter.repositories.CommenterJobPostRepository;

@Service
public class CommenterJobPostService {

	@Autowired
	private CommenterJobPostRepository commenterJobPostRepo;
	
//	FIND ALL
	public List<CommenterJobPost> findAllComments() {
		return commenterJobPostRepo.findAll();
	}
	
//	CREATE
	public CommenterJobPost createNewComment(CommenterJobPost commenterJobPost) {
		return commenterJobPostRepo.save(commenterJobPost);
	}
}
