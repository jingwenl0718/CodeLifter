package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.LifestylePost;
import com.jingwenli.codelifter.repositories.LifestylePostRepository;

@Service
public class LifestylePostService {

	@Autowired
	private LifestylePostRepository lifestylePostRepo;
	
//	FIND ALL
	public List<LifestylePost> findAllLifestylePosts() {
		return lifestylePostRepo.findAll();
	}
	
//	FIND ONE
	public LifestylePost findOneLifestylePost(Long id) {
		Optional<LifestylePost> optionalLifestylePost = lifestylePostRepo.findById(id);
		if (optionalLifestylePost.isPresent() ) {
			return optionalLifestylePost.get();
		} else {
			return null;
		}
	}
	
//	CREATE
	public LifestylePost createNewLifestylePost(LifestylePost lifestylePost) {
		return lifestylePostRepo.save(lifestylePost);
	}
	
//	EDIT
	public LifestylePost editLifestylePost(LifestylePost lifestylePost) {
		return lifestylePostRepo.save(lifestylePost);
	}
	
//	DELETE
	public void deleteLifestylePost(Long id) {
		lifestylePostRepo.deleteById(id);
	}
}
