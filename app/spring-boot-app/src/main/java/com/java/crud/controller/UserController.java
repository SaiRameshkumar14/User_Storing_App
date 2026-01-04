package com.java.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.crud.entity.User;
import com.java.crud.service.UserServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

    // POST - Create Single User
    @CrossOrigin
	@PostMapping("/user")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		return userServiceImpl.createUser(user);
	}

    // POST - Create Multiple User
    @CrossOrigin
    @PostMapping("/users")
	public ResponseEntity<?> addUsers(@RequestBody Set<User> users) {
		return userServiceImpl.createUsers(users) ;
	}

    // POST - Excel file
    @CrossOrigin
    @PostMapping(value ="/users/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadUsers(@RequestParam("file") MultipartFile file) throws IOException {
        return userServiceImpl.saveFileData(file.getInputStream());
    }

    // GET - Get Single User
    @CrossOrigin
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userServiceImpl.getUserById(id);
	}

    // GET - Get Multiple Users
    @CrossOrigin
    @GetMapping("/users")
	public List<User> getAllUsers() {
		return userServiceImpl.getUsers();
	}

    // PUT - Modify single User detail
    @CrossOrigin
	@PutMapping("/user/{id}")
    //localhost:8080/user/3?name=Saran&address=Madurai
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("address") String address) {
		return userServiceImpl.updateUser(id, name, address);
	}

    // DELETE - Delete single user
    @CrossOrigin
	@DeleteMapping("/user/{id}")
    //localhost:8080/user/1
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		return userServiceImpl.deleteUserById(id);
	}
}
