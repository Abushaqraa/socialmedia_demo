package com.example.task2.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReactionId implements Serializable {
    private Long userId;
    private Long postId;

    public ReactionId() {
    }
    public ReactionId(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionId that = (ReactionId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(postId, that.postId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}