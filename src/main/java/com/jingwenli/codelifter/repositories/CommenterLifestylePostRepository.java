package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.CommenterLifestylePost;
import com.jingwenli.codelifter.models.LifestylePost;

@Repository
public interface CommenterLifestylePostRepository extends CrudRepository<CommenterLifestylePost, Long> {

	List<CommenterLifestylePost> findAllByLifestylePost(LifestylePost lifestylePost);
}
