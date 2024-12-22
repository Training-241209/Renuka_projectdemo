package com.ers.ers.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ers.ers.Entity.Role;
import com.ers.ers.Entity.User;
import com.ers.ers.Repository.RoleRepository;
import com.ers.ers.Repository.UserRepository;

@Service
	public class UserService {
	    private UserRepository userRepository;
	    private RoleRepository roleRepository;

	    @Autowired
	    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	    }

	    public User getUserById(Integer userId) {
	        return userRepository.findById(userId).orElse(null);
	    }

	    public User registerUser(User user) {
	        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

	        if (existingUser.isPresent() || user.getUsername().trim().isEmpty() ||
	                user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty() ||
	                user.getPassword().trim().isEmpty())
	            return null;

	        Optional<Role> defaultRole = roleRepository.findByRoleName("Employee");
	        if (defaultRole != null)
	            user.setRole(defaultRole.get());
	        else
	            return null;

	        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashed);

	        return userRepository.save(user);
	    }

	    public User login(String username, String password) {
	        Optional<User> user = userRepository.findByUsername(username);
	        if (user.isPresent() && user.get().getUsername().equals(username)
	                && BCrypt.checkpw(password, user.get().getPassword())) {
	            return user.get();
	        } else
	            return null;
	    }
	}


