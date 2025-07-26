package com.example.task2.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Reaction> reactions=new ArrayList<>();

    public List<Reaction> getReactions(){
        return reactions;
    }
    public void addReaction(Reaction reaction){
        reactions.add(reaction);
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }
    public Long getPostId(){
        return postId;
    }

}
