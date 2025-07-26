package com.example.task2.DTO;

import jakarta.validation.constraints.NotNull;

public class ReactionRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long postId;

    @NotNull
    private String type;


    public ReactionRequestDTO() {
    }
    public ReactionRequestDTO(Long userId, Long postId, String type) {
        this.userId = userId;
        this.postId = postId;
        this.type = type;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getPostId() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}