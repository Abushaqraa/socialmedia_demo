package com.example.task2.DTO;

import jakarta.validation.constraints.NotNull;

public class PostRequestDTO {
    private String text;
    @NotNull
    private Long userId;

    public PostRequestDTO(String text,Long userId){
        this.text=text;
        this.userId=userId;
    }

    public PostRequestDTO() {
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }
    public Long getUserId(){
        return userId;
    }
}