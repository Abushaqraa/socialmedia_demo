package com.example.task2.repository;

import com.example.task2.model.Reaction;
import com.example.task2.model.ReactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction,ReactionId> {
    List<Reaction> findByPostPostId(Long postId);
    boolean existsByUserUserIdAndPostPostId(Long userId, Long postId);
}