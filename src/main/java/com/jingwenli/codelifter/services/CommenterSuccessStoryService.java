package com.jingwenli.codelifter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.CommenterSuccessStory;
import com.jingwenli.codelifter.models.SuccessStory;
import com.jingwenli.codelifter.repositories.CommenterSuccessStoryRepository;

@Service
public class CommenterSuccessStoryService {

	@Autowired
	private CommenterSuccessStoryRepository commenterSuccessStoryRepo;
	
//	FIND ALL
	public List<CommenterSuccessStory> findAllCommentsByPost(SuccessStory successStory) {
		return commenterSuccessStoryRepo.findAllBySuccessStory(successStory);
	}
	
//	CREATE
	public CommenterSuccessStory createNewComment(CommenterSuccessStory commenterSuccessStory) {
		return commenterSuccessStoryRepo.save(commenterSuccessStory);
	}
}
