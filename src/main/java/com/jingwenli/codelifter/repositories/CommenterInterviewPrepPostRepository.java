package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.CommenterInterviewPrepPost;

@Repository
public interface CommenterInterviewPrepPostRepository extends CrudRepository<CommenterInterviewPrepPost, Long> {

	List<CommenterInterviewPrepPost> findAll();
}
