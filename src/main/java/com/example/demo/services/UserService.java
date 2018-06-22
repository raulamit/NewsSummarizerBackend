package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	//CREATE NEW USER
	@PostMapping("/api/user")
	public User createUser(
			@RequestBody User user) {
		return userRepository.save(user);
	}
	
	//FIND USER BY USERNAME OR CREDENTIALS IF PROVIDED
	//ELSE
	//FIND ALL USERS
	@GetMapping("/api/user")
	public List<User> findAllUsers(
			@RequestParam(name="username", required=false)
				String username,
			@RequestParam(name="password", required=false)
				String password) {
		if(username != null && password != null) {
			return (List<User>) userRepository.findUserByCredentials(username, password);
		}
		else if(username != null) {
			return (List<User>) userRepository.findUserByUsername(username);
		}
		return (List<User>) userRepository.findAll();
	}
	
	//FIND USER BY USER ID
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") int uid) {
		
		Optional<User> data = userRepository.findById(uid);
		if(data.isPresent())
			return data.get();
		
		return null;
	}
	
	//UPDATE USER
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId,
			@RequestBody User newUser) {
		
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole (newUser.getRole());
			user.setPhone(newUser.getPhone());
			user.setEmail(newUser.getEmail());
			
			userRepository.save(user);
			return user;
		}
		return null;
		
	}
		
	//DELETE USER
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
		
	}	

}
