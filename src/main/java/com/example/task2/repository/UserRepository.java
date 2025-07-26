package com.example.task2.repository;

import com.example.task2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {
    boolean existsByUsernameAndEmail(String username, String email);
    //boolean existsByUserId(Long userId);
}
