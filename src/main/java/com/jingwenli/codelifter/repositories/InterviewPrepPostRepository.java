package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jingwenli.codelifter.models.InterviewPrepPost;
import com.jingwenli.codelifter.models.User;

public interface InterviewPrepPostRepository extends CrudRepository<InterviewPrepPost, Long> {

	List<InterviewPrepPost> findAll();
	
	@Query(value="SELECT * FROM interviewprepposts Order by created_at DESC;", nativeQuery=true)
	List<InterviewPrepPost> findRecentPost();
	
	List<InterviewPrepPost> findByInterviewPrepPostCreator(User user);
}
