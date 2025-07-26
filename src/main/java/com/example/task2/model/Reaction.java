package com.example.task2.model;

import jakarta.persistence.*;

@Entity
public class Reaction {
    @EmbeddedId
    private ReactionId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String type;

    public Reaction() {
    }
    public Reaction(User user, Post post, String type) {
        this.user = user;
        this.post = post;
        this.type = type;
        this.id = new ReactionId(user.getUserId(), post.getPostId());
    }

    public ReactionId getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}