package com.jingwenli.codelifter.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.StudyGroup;
import com.jingwenli.codelifter.models.User;

@Repository
public interface StudyGroupRepository extends CrudRepository<StudyGroup, Long> {
	
	List<StudyGroup> findAll();
	StudyGroup findByIdIs(Long id);
	List<StudyGroup> findAllByUsers(User user);
	List<StudyGroup> findByUsersNotContains(User user);
	
}
