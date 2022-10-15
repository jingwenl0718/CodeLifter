package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.CommenterLifestylePost;

@Repository
public interface CommenterLifestylePostRepository extends CrudRepository<CommenterLifestylePost, Long> {

	List<CommenterLifestylePost> findAll();
}
