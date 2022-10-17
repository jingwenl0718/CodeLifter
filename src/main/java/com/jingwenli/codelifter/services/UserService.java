package com.jingwenli.codelifter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.RoleRepository;
import com.jingwenli.codelifter.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepo = userRepository;
        this.roleRepo = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_USER"));
        userRepo.save(user);
    }
    
    // 2 
   public void saveUserWithAdminRole(User user) {
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
       userRepo.save(user);
   }
   
   // 3
   public User findByEmail(String email) {
       return userRepo.findByEmail(email);
   }
   
   public List<User> allUsers() {
	   return userRepo.findAll();
   }
   
   public User updateUser(User user) {
	   return userRepo.save(user);
   }
   
   public User findById(Long Id) {
	   Optional<User> potentialUser = userRepo.findById(Id);
	   if (potentialUser.isPresent()) {
	   		return potentialUser.get();
	   }
	   return null;
   }
   
}
