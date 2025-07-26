package com.example.task2.controller;

import com.example.task2.DTO.PostRequestDTO;
import com.example.task2.DTO.PostResponseDTO;
import com.example.task2.model.Post;
import com.example.task2.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            PostResponseDTO savedPost =postService.createPost(postRequestDTO);
            return ResponseEntity.ok(savedPost);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id).orElse(null);
    }
    @GetMapping("/{userId}/posts")
    public List<PostResponseDTO> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }
}
