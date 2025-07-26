package com.example.task2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @ManyToOne
    @MapsId("user1Id")
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @MapsId("user2Id")
    @JoinColumn(name = "user2_id")
    private User user2;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FriendshipStatus friendshipStatus;

    public Friendship(){}
    public Friendship(User user1 ,User user2,FriendshipStatus friendshipStatus){
        this.id=new FriendshipId(user1.getUserId(), user2.getUserId());
        this.user1=user1;
        this.user2=user2;
        this.friendshipStatus=friendshipStatus;
    }

    public FriendshipId getId() {
        return id;}
    public User getUser1() {
        return user1;}
    public User getUser2() {
        return user2;}
    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;}
    public void setFriendshipStatus(FriendshipStatus friendshipStatus){
        this.friendshipStatus=friendshipStatus;
    }
}