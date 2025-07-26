package com.example.task2.controller;

import com.example.task2.DTO.ReactionRequestDTO;
import com.example.task2.DTO.ReactionResponseDTO;
import com.example.task2.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class ReactionController {
    private final ReactionService reactionService;
    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }
    @PostMapping("/{postId}/reactions")
    public ResponseEntity<?> addReaction(@PathVariable Long postId, @RequestBody ReactionRequestDTO reactionRequestDTO){
        reactionRequestDTO.setPostId(postId);
        return ResponseEntity.ok(reactionService.addReaction(reactionRequestDTO));
    }
    @GetMapping("/{postId}/reactions")
    public ResponseEntity<List<ReactionResponseDTO>> getReactions(@PathVariable Long postId) {
        return ResponseEntity.ok(reactionService.getReactionsByPostId(postId));
    }
}