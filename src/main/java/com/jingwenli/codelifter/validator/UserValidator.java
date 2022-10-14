package com.jingwenli.codelifter.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jingwenli.codelifter.models.User;
import com.jingwenli.codelifter.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	UserRepository userRepo;
	
    //    1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object object, Errors errors) {
       User user = (User) object;
 	   Optional<User> potentialUser = userRepo.findUsersByEmail(user.getEmail());
 	   Optional<User> potentialUserName = userRepo.findByUserName(user.getUserName()); 
        
       if (!user.getConfirm().equals(user.getPassword())) {
            // 3
            errors.rejectValue("confirm", "Matches", "Password and Confirm Password must match!");
       }
       
	   if (potentialUser.isPresent()) {
		   errors.rejectValue("email", "Matches", "An account with this email already exists!");
	   }
	   
	   if (potentialUserName.isPresent()) {
	    	errors.rejectValue("userName", "Matches", "An account with this username already exists!");
	   }  
    }
}
