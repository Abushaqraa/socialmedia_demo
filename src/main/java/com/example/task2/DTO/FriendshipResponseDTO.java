package com.example.task2.DTO;

public class FriendshipResponseDTO {
    private Long user1Id;
    private Long user2Id;
    private String friendshipStatus;

    public FriendshipResponseDTO() {}
    public FriendshipResponseDTO(Long user1Id, Long user2Id, String friendshipStatus) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.friendshipStatus=friendshipStatus;
    }

    public Long getUser1Id() { return user1Id; }
    public Long getUser2Id() { return user2Id; }
    public String getFriendshipStatus() { return friendshipStatus; }
}