package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.CommenterJobPost;

@Repository
public interface CommenterJobPostRepository extends CrudRepository<CommenterJobPost, Long> {

	List<CommenterJobPost> findAll();
}
