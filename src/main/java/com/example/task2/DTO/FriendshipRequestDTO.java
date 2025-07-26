package com.example.task2.DTO;

import com.example.task2.model.FriendshipStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FriendshipRequestDTO {
    @NotNull
    private Long user1Id;

    @NotNull
    private Long user2Id;

    @NotBlank
    private String friendshipStatus;

    public FriendshipRequestDTO() {}
    public FriendshipRequestDTO(Long user1Id,Long user2Id,String friendshipStatus) {
        this.user1Id=user1Id;
        this.user2Id=user2Id;
        this.friendshipStatus=friendshipStatus;
    }
    public Long getUser1Id(){
        return user1Id;
    }
    public Long getUser2Id(){
        return user2Id;
    }
    public String getFriendshipStatus(){
        return friendshipStatus;
    }
    public void setFriendshipStatus(String friendshipStatus){
        this.friendshipStatus=friendshipStatus;
    }
}
