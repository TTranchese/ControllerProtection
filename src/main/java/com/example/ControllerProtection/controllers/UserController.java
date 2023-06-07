package com.example.ControllerProtection.controllers;

import com.example.ControllerProtection.entities.User;
import com.example.ControllerProtection.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserRepository userRepository;
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user) {
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) {
		if (userRepository.existsById(id)) {
			user.setId(id);
			userRepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delUser(@PathVariable Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User carEntity = optionalUser.get();
			userRepository.deleteById(id);
			return new ResponseEntity<>(carEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
