package com.mtd.ecom_server.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.ecom_server.exceptions.ResourceNotFoundException;
import com.mtd.ecom_server.models.Product;
import com.mtd.ecom_server.models.User;
import com.mtd.ecom_server.repos.ProductRepo;
import com.mtd.ecom_server.repos.UserRepo;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger Log = LoggerFactory.getLogger(UserController.class);
	@Autowired UserRepo userRepo;
	@Tag(name="get all users")
	@GetMapping("/all")
	public List<User> getAllUsers(){
		Log.info("fetching all users");
		return userRepo.findAll();
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable String id) {
	    Log.info("Fetching user by id: " + id);
	    return userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable String email) {
	    Log.info("Fetching user by email: " + email);
	    return userRepo.findByEmail(email)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
	}

		
		@PostMapping("/add")
		public User addUser(@RequestBody User newUser) {
		    return userRepo.save(newUser);
		}

	@DeleteMapping("/user/{id}")
	public String deleteUser (@PathVariable String id) {
	    Optional<User> finduser = userRepo.findById(id);
	    if(finduser.isEmpty()) {
	        Log.error("failed to delete user" + id);
	        throw new ResourceNotFoundException("User not found");
	    }
	    userRepo.deleteById(id);
	    Log.error("deleting the user by id" + id);
	    return "deleted user";
	}

	
	
	@PutMapping("/user/edit/{id}")
	public User editUser(@PathVariable String id, @RequestBody User newUser) {
	    User finduser = userRepo.findById(id).get();

	    finduser.setName(newUser.getName());
	    finduser.setEmail(newUser.getEmail());
	    finduser.setPassword(newUser.getPassword());
	    finduser.setStreet(newUser.getStreet());
	    finduser.setCity(newUser.getCity());
	    finduser.setZip(newUser.getZip());

		Log.info("editing Products");

	    return userRepo.save(finduser);
	}

	


	

}
