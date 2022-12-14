package com.jingwenli.codelifter.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jingwenli.codelifter.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	Optional<User> findUsersByEmail(String email);
	
	Optional<User> findByUserName(String userName);
	
	List<User> findAll();
}

