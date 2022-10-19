package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.StudyGroup;
import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.StudyGroupRepository;

@Service
public class StudyGroupService {
	
	@Autowired
	StudyGroupRepository studyRepo;
	
	@Autowired
	UserService userService;
	
//	All
	public List<StudyGroup> allStudyGroups() {
		return studyRepo.findAll();
	}
	
//	Find one
	public StudyGroup oneStudyGroup(Long id) {
		Optional<StudyGroup> optionalStudyGroup = studyRepo.findById(id);
		
		if (optionalStudyGroup.isPresent()) {
			return optionalStudyGroup.get();
		}
		else {
			return null;
		}
	}
	
//	Create
	public StudyGroup createStudyGroup(StudyGroup studyGroup) {
		return studyRepo.save(studyGroup);
	}
	
//	Update
	public StudyGroup updateStudyGroup(StudyGroup studyGroup) {
		return studyRepo.save(studyGroup);
	}
	
//	Delete
	public void deleteStudyGroup(Long id) {
		studyRepo.deleteById(id);
	}
	
//	Assigned
	public List<StudyGroup> getAssignedUsers(User user) {
		return studyRepo.findAllByUsers(user);
	}
	
//	Unassigned
	public List<StudyGroup> getUnassignedUsers(User user) {
		return studyRepo.findByUsersNotContains(user);
	}
	
	public StudyGroup findRecentPost() {
		List<StudyGroup> postList = studyRepo.findRecentPost();
		return postList.get(0);
	}
}
