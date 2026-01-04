package com.java.crud.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.crud.repository.UserRepository;
import com.java.crud.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // POST - User
    // Create Single User
    public ResponseEntity<String> createUser(User user) {
        user.setName(user.getName().toUpperCase());
        user.setAddress(user.getAddress().toUpperCase());
        //Checking null entries are found or not. After checking that user will be stored
        if (checkNullValues(user.getName(), user.getAddress())) {
            return new ResponseEntity<>("Null Values can't be created", HttpStatus.BAD_REQUEST);
        }
        // Checking the Name or Address is already exist in DB or not
        else if (!userRepository.existsByNameAndAddress(user.getName(), user.getAddress())) {
            userRepository.save(user);
            return new ResponseEntity<>(user.getName() + " from " + user.getAddress() + " got created successfully.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user.getName()+" from " + user.getAddress() + " is Already registered.", HttpStatus.FORBIDDEN);
    }

    // POST - Set<User>
    // Create Multiple Users
    public ResponseEntity<String> createUsers(Set<User> users) {
        // Variable created to know whether any of the user got created or not
        boolean temp = false;
        // Users which is created is stored
        List<String> createdUsers = new ArrayList<>();
        // Users Which is valid to store in DB will be added
        Set<User> validUsers = new HashSet<>();

        // Checking the users details are duplicating or not for per User
        for (User perUser : users) {
            perUser.setName(perUser.getName().toUpperCase());
            perUser.setAddress(perUser.getAddress().toUpperCase());
            //Checking the user details containing null values or not
            if (checkNullValues(perUser.getName(), perUser.getAddress())) {
                return new ResponseEntity<>("Null Values can't be created", HttpStatus.BAD_REQUEST);
            }
            // Checking the user existence in DB
            else if (!userRepository.existsByNameAndAddress(perUser.getName(), perUser.getAddress())){
                createdUsers.add(perUser.getName());
                validUsers.add(perUser);
                temp = true;
            }
        }

        // If temp is true, then there is a user is created and stored in the db
        if (temp) {
            userRepository.saveAll(validUsers);
            String result = "Users Got Created Successfully : ";
            // Converting the created and stored user's name as single string
            String createdUserList = String.join(", ", createdUsers);
            result += createdUserList;
            createdUsers.clear();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }

        // If no other users are created then the set contains the all duplicate entries
        return new ResponseEntity<>("All Users are already exist", HttpStatus.FORBIDDEN);
    }

    // POST - users/upload
    // Create Multiple Users using EXCEL
    public ResponseEntity<String> saveFileData(InputStream file) throws IOException {
        Set<User> usersList = new HashSet<>();
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        for(Row row : sheet){
            User user = new User();
            if(row.getRowNum()!=0){
                user.setName(row.getCell(0).getStringCellValue());
                user.setAddress(row.getCell(1).getStringCellValue());
                usersList.add(user);
            }
        }
        return createUsers(usersList);
    }


    // GET - Id
    // Get Single User
    public ResponseEntity<User> getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET - List<User>
    // Get Multiple Users
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // PUT - /id/name/address
    // Modify single User detail
    public ResponseEntity<String> updateUser(int id, String userName, String userAddress) {
        //Variable to store new details in the old details
        User oldUser = null;
        // Check the new name and address is exist or not in DB
        boolean checkUserExistence = userRepository.existsByNameAndAddress(userName.toUpperCase(), userAddress.toUpperCase());
        // Getting the id from DB
        Optional<User> optionaluser = userRepository.findById(id);

        // Checking the null entries
        if (checkNullValues(userName, userAddress)) {
            return new ResponseEntity<>("Null Values can't be created", HttpStatus.BAD_REQUEST);
        }
        // The optional ID should present and the new details should not exist in DB
        else if (optionaluser.isPresent() && !(checkUserExistence)) {
            oldUser = optionaluser.get();
            oldUser.setName(userName.toUpperCase());
            oldUser.setAddress(userAddress.toUpperCase());
            userRepository.save(oldUser);
            return new ResponseEntity<>("User Modified Successfully", HttpStatus.OK);
        } else if (!(checkUserExistence)) {
            // If there is no ID and details in DB then, create new record
            return new ResponseEntity<>("Invalid User ID",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("User details duplicating another record", HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE - /id
    // Delete single user
    public ResponseEntity<String> deleteUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User Removed Successfully : " + user.getName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User ID Not found", HttpStatus.NOT_FOUND);
    }



// My usage logics

    // Checking the Null value entries
    public boolean checkNullValues(String userName, String userAddress) {
        // Check null or empty inputs
        if(userName == null || userAddress == null){
            return true;
        }
        // check the value entered is null or no=-
        String userCaseName = userName.toLowerCase();
        String userCaseAddress = userAddress.toLowerCase();
        if (userCaseName.equals("null") || userCaseAddress.equals("null")) {
            return true;
        }
        if (userCaseName.isEmpty() || userCaseAddress.isEmpty()
        ) {
            return true;
        }
        return false;
    }
}
