package com.example.task2.service;

import com.example.task2.DTO.FriendshipRequestDTO;
import com.example.task2.DTO.FriendshipResponseDTO;
import com.example.task2.model.Friendship;
import com.example.task2.model.FriendshipStatus;
import com.example.task2.model.User;
import com.example.task2.repository.FriendshipRepository;
import com.example.task2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FriendshipResponseDTO creatFriendship(FriendshipRequestDTO friendshipRequestDTO) {
        User user1 = userRepository.findById(friendshipRequestDTO.getUser1Id())
                .orElseThrow(() -> new IllegalArgumentException("user1 not found"));
        User user2 = userRepository.findById(friendshipRequestDTO.getUser2Id())
                .orElseThrow(() -> new IllegalArgumentException("user2 not found"));

        if (friendshipRepository.existsByUser1UserIdAndUser2UserId(Math.min(user1.getUserId(), user2.getUserId()), Math.max(user1.getUserId(), user2.getUserId()))) {
            throw new IllegalStateException("Friendship already exists");
        }
        Friendship friendship = new Friendship(user1, user2, FriendshipStatus.valueOf(friendshipRequestDTO.getFriendshipStatus().toUpperCase()));
        Friendship savedFriendship = friendshipRepository.save(friendship);
        return convertToDTO(savedFriendship);
    }

    @Transactional
    public FriendshipResponseDTO updateFriendshipStatus(Long user1Id, Long user2Id, String status) {
        Friendship friendship = friendshipRepository.findByUser1UserIdAndUser2UserId(
                        Math.min(user1Id, user2Id),
                        Math.max(user1Id, user2Id))
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendship.setFriendshipStatus(FriendshipStatus.valueOf(status.toUpperCase()));
        return convertToDTO(friendshipRepository.save(friendship));
    }

    public List<FriendshipResponseDTO> getFriendshipsByUserId(Long userId) {
        return friendshipRepository.findAll().stream()
                .filter(f -> f.getUser1().getUserId().equals(userId) ||
                        f.getUser2().getUserId().equals(userId))
                .filter(f -> f.getFriendshipStatus() == FriendshipStatus.ACCEPTED)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FriendshipResponseDTO convertToDTO(Friendship friendship) {
        return new FriendshipResponseDTO(
                friendship.getUser1().getUserId(),
                friendship.getUser2().getUserId(),
                friendship.getFriendshipStatus().name()
        );
    }
}