package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jingwenli.codelifter.models.InterviewPrepPost;

public interface InterviewPrepPostRepository extends CrudRepository<InterviewPrepPost, Long> {

	List<InterviewPrepPost> findAll();
}
