package com.example.task2.controller;

import com.example.task2.DTO.FriendshipRequestDTO;
import com.example.task2.DTO.FriendshipResponseDTO;
import com.example.task2.service.FriendshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> sendRequest(@Valid @RequestBody FriendshipRequestDTO friendshipRequestDTO) {
        try {
            return ResponseEntity.ok(friendshipService.creatFriendship(friendshipRequestDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/accept")
    public ResponseEntity<FriendshipResponseDTO> acceptRequest(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id) {
        return ResponseEntity.ok(friendshipService.updateFriendshipStatus(
                user1Id, user2Id, "ACCEPTED"));
    }

    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<FriendshipResponseDTO>> getFriends(
            @PathVariable Long userId) {
        return ResponseEntity.ok(friendshipService.getFriendshipsByUserId(userId));
    }
}