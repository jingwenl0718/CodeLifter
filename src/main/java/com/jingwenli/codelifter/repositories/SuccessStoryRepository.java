package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.SuccessStory;

@Repository
public interface SuccessStoryRepository extends CrudRepository<SuccessStory, Long> {

	List<SuccessStory> findAll();
}
