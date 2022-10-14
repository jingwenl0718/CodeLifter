package com.jingwenli.codelifter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
   
   public void verifyEmail(User newUser, BindingResult result) {
	   Optional<User> potentialUser = userRepo.findUsersByEmail(newUser.getEmail());
	   
	   if (potentialUser.isPresent()) {
		   result.rejectValue("email", "Matches", "An account with this email already exists!");
	   }
   }
}
