package com.example.task2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reaction> reactions=new ArrayList<>();

    @OneToMany(mappedBy = "user1",cascade = CascadeType.ALL)
    private List<Friendship> initiatedFriendships=new ArrayList<>();

    @OneToMany(mappedBy = "user2",cascade = CascadeType.ALL)
    private List<Friendship> recievedFriendships=new ArrayList<>();

    public void addInitiatedFriendship(Friendship friendship) {
        initiatedFriendships.add(friendship);
    }

    public void addReceivedFriendship(Friendship friendship) {
        recievedFriendships.add(friendship);
    }
    public List<Friendship> getInitiatedFriendships() {
        return initiatedFriendships;
    }

    public List<Friendship> getRecievedFriendships(){
        return recievedFriendships;
    }

    public List<Reaction> getReactions(){
        return reactions;
    }
    public void addReaction(Reaction reaction){
        reactions.add(reaction);
    }

    public List<Post> getPosts(){
        return posts;
    }
    public void addPost(Post post){
        posts.add(post);
    }
    public Long getUserId(){
        return userId;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
