package com.example.task2.service;

import com.example.task2.DTO.ReactionRequestDTO;
import com.example.task2.DTO.ReactionResponseDTO;
import com.example.task2.model.Reaction;
import com.example.task2.model.User;
import com.example.task2.model.Post;
import com.example.task2.repository.ReactionRepository;
import com.example.task2.repository.UserRepository;
import com.example.task2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Autowired
    public ReactionService(ReactionRepository reactionRepository,UserRepository userRepository,PostRepository postRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public ReactionResponseDTO addReaction(ReactionRequestDTO reactionRequestDTO) {
        if (reactionRequestDTO == null || reactionRequestDTO.getUserId() == null || reactionRequestDTO.getPostId() == null || reactionRequestDTO.getType() == null) {
            throw new IllegalArgumentException("Request data is incomplete");
        }
        User user = userRepository.findById(reactionRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + reactionRequestDTO.getUserId()));

        Post post = postRepository.findById(reactionRequestDTO.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + reactionRequestDTO.getPostId()));

        if (reactionRepository.existsByUserUserIdAndPostPostId(user.getUserId(), post.getPostId())) {
            throw new IllegalStateException("User " + user.getUserId() + " already reacted to post " + post.getPostId());
        }
        Reaction reaction = new Reaction(user, post, reactionRequestDTO.getType());
        Reaction savedReaction = reactionRepository.save(reaction);
        return new ReactionResponseDTO(
                savedReaction.getUser().getUserId(),
                savedReaction.getPost().getPostId(),
                savedReaction.getType()
        );
    }
    public List<ReactionResponseDTO> getReactionsByPostId(Long postId) {
        List<Reaction> reactions = reactionRepository.findByPostPostId(postId);
        return reactions.stream()
                .map(reaction -> new ReactionResponseDTO(
                        reaction.getUser().getUserId(),reaction.getPost().getPostId(), reaction.getType())
                ).collect(Collectors.toList());
    }
}