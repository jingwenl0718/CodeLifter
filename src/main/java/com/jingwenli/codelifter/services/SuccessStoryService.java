package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.SuccessStory;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.SuccessStoryRepository;

@Service
public class SuccessStoryService {

	@Autowired
	private SuccessStoryRepository successStoryRepo;
	
//	FIND ALL
	public List<SuccessStory> findAllSuccessStories() {
		return successStoryRepo.findAll();
	}
	
//	FIND ONE
	public SuccessStory findOneSuccessStory(Long id) {
		Optional<SuccessStory> optionalSuccessStory = successStoryRepo.findById(id);
		if (optionalSuccessStory.isPresent() ) {
			return optionalSuccessStory.get();
		} else {
			return null;
		}
	}
	
//	CREATE
	public SuccessStory createNewSuccessStory(SuccessStory successStory) {
		return successStoryRepo.save(successStory);
	}
	
//	EDIT
	public SuccessStory editSuccessStory(SuccessStory successStory) {
		return successStoryRepo.save(successStory);
	}
	
//	DELETE
	public void deleteSuccessStory(Long id) {
		successStoryRepo.deleteById(id);
	}
	
//	FIND MOST RECENT POST
	public SuccessStory findRecentPost() {
		List<SuccessStory> postList = successStoryRepo.findRecentPost();
		return postList.get(0);
	}
	
//	FIND ALL MY POSTS
	public List<SuccessStory> findAllMyPosts(User user) {
		return successStoryRepo.findBySuccessStoryCreator(user);
	}
}
