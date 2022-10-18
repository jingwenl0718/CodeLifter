package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.JobPost;

@Repository
public interface JobPostRepository extends CrudRepository<JobPost, Long> {

	List<JobPost> findAll();
	
	@Query(value="SELECT * FROM jobposts Order by created_at DESC;", nativeQuery=true)
	List<JobPost> findRecentPost();
}
