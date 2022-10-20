package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.LifestylePost;
import com.jingwenli.codelifter.models.User;

@Repository
public interface LifestylePostRepository extends CrudRepository<LifestylePost, Long> {

	List<LifestylePost> findAll();
	
	@Query(value="SELECT * FROM lifestyleposts Order by created_at DESC;", nativeQuery=true)
	List<LifestylePost> findRecentPost();
	
	List<LifestylePost> findByLifestylePostCreator(User user);
}
