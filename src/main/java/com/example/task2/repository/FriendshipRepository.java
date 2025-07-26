package com.example.task2.repository;

import com.example.task2.model.Friendship;
import com.example.task2.model.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId>{
    boolean existsByUser1UserIdAndUser2UserId(Long user1Id, Long user2Id);
    Optional<Friendship> findByUser1UserIdAndUser2UserId(Long user1Id, Long user2Id);
    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.user1.userId = :userId OR f.user2.userId = :userId) " +
            "AND f.friendshipStatus = 'ACCEPTED'")
    List<Friendship> findAcceptedFriendships(@Param("userId") Long userId);
}