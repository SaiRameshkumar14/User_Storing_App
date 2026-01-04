package com.java.crud.service;

import com.java.crud.entity.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface UserService {
    ResponseEntity<String> createUser(User user);

    ResponseEntity<String> createUsers(Set<User> users);

    ResponseEntity<String> saveFileData(InputStream file) throws IOException;

    ResponseEntity<User> getUserById(int id);

    List<User> getUsers();

    ResponseEntity<String> updateUser(int id, String userName, String userAddress);

    ResponseEntity<String> deleteUserById(int id);

    // Logics
    boolean checkNullValues(String userName, String userAddress);
}
