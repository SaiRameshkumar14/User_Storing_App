package com.java.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.crud.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNameAndAddress(String name, String address);

}
