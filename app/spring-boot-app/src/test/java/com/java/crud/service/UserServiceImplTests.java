package com.java.crud.service;

import com.java.crud.entity.User;
import com.java.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveUser(){
        User user = new User(null, "Karan","Chennai");
        when(userRepository.save(user)).thenReturn(new User(null, "Karan","Chennai"));
        ResponseEntity<String> result = userServiceImpl.createUser(user);
        assertEquals("CreateUser", "KARAN from CHENNAI got created successfully.", result.getBody());
        verify(userRepository, times(1)).save(user);
    }

//    @Test
//    public void shouldSaveAllUsers(){
//        Set<User> listOfUsers = new HashSet<>();
//        listOfUsers.add(new User(null, "Sai", "Kerala"));
//        listOfUsers.add(new User(null, "Sara", "Bihar"));
//        listOfUsers.add(new User(null, "Sara", "Bihar"));
//        when(userRepository.saveAll(listOfUsers)).thenReturn(new ArrayList<>());
//        ResponseEntity<String> result = userService.createUsers(listOfUsers);
//        assertEquals("All Users Created", "Users Got Created Successfully : Sara, Sai", result.getBody());
//        //verify(userRepository, times(1)).saveAll(listOfUsers);
//
//        // Verify the actual calls
//        verify(userRepository, times(3)).existsByNameAndAddress(anyString(), anyString());
//        verify(userRepository, times(2)).save(any(User.class));
//        // Verify that saveAll was never called
//        verify(userRepository, never()).saveAll(anyList());
//    }

    @Test
    public void shouldReturnAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(null, "Sai", "Chennai"));
        when(userRepository.findAll()).thenReturn(users);
        List<User> responseUsers = userServiceImpl.getUsers();
        assertEquals("Correct Size",1,responseUsers.size());
        assertEquals("Got All Users", "Sai", responseUsers.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

}
