package com.example.task2.DTO;

public class PostResponseDTO {
    private String text;
    private Long userId;

    public String getText(){
        return text;
    }
    public void  setText(String text){
        this.text=text;
    }
    public Long getUserId(){
        return userId;
    }
}
