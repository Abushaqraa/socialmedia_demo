package com.example.task2.DTO;

public class UserResponseDTO {
    private long user_id;
    private String username;
    private String email;

    public UserResponseDTO(long userId, String username, String email) {
        this.user_id=userId;
        this.username=username;
        this.email=email;
    }

    public long getUser_id(){
        return user_id;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
}
