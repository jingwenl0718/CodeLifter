package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.SuccessStory;
import com.jingwenli.codelifter.models.User;

@Repository
public interface SuccessStoryRepository extends CrudRepository<SuccessStory, Long> {

	List<SuccessStory> findAll();
	
	@Query(value="SELECT * FROM successstories Order by created_at DESC;", nativeQuery=true)
	List<SuccessStory> findRecentPost();
	
	List<SuccessStory> findBySuccessStoryCreator(User user);
}
