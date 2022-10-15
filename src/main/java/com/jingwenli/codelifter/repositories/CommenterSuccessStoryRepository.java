package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.CommenterSuccessStory;

@Repository
public interface CommenterSuccessStoryRepository extends CrudRepository<CommenterSuccessStory, Long> {

	List<CommenterSuccessStory> findAll();
}
