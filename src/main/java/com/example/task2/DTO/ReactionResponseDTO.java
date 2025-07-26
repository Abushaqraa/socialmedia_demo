package com.example.task2.DTO;

public class ReactionResponseDTO {
    private Long userId;
    private Long postId;
    private String type;

    public ReactionResponseDTO() {
    }

    public ReactionResponseDTO(Long userId, Long postId, String type) {
        this.userId = userId;
        this.postId = postId;
        this.type = type;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getPostId() {
        return postId;
    }
    public String getType() {
        return type;
    }
}