package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.JobPost;

@Repository
public interface JobPostRepository extends CrudRepository<JobPost, Long> {

	List<JobPost> findAll();
}
