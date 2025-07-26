package com.example.task2.repository;

import com.example.task2.model.Post;
import com.example.task2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findAllByUserUserId(Long userId);
    }
