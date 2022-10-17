package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.LifestylePost;

@Repository
public interface LifestylePostRepository extends CrudRepository<LifestylePost, Long> {

	List<LifestylePost> findAll();
}
