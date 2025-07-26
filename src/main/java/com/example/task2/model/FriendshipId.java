package com.example.task2.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendshipId implements Serializable {
    private Long user1Id;
    private Long user2Id;

    public FriendshipId(){}
    public FriendshipId(Long user1Id,Long user2Id){
        this.user1Id=user1Id;
        this.user2Id=user2Id;
    }
    public Long getUser1Id(){
        return user1Id;
    }
    public Long getUser2Id(){
        return user2Id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendshipId that)) return false;
        return user1Id.equals(that.user1Id) && user2Id.equals(that.user2Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1Id, user2Id);
    }
}