package com.example.task2.service;
import com.example.task2.DTO.PostRequestDTO;
import com.example.task2.DTO.PostResponseDTO;
import com.example.task2.model.Post;
import com.example.task2.model.User;
import com.example.task2.repository.PostRepository;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository,UserRepository userRepository){
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO){
        if (postRequestDTO.getUserId() ==null){
            throw new IllegalArgumentException("User ID connot be null");
        }
        User user=userRepository.findById(postRequestDTO.getUserId()).orElse(null);
        if(user ==null){
            throw new IllegalArgumentException("User ID connot be null");
        }
        Post post=new Post();
        post.setText(postRequestDTO.getText());
        post.setUser(user);
        user.addPost(post);
        Post savedPost=postRepository.save(post);
        userRepository.save(user);
        return convertToDto(savedPost);
    }
    public PostResponseDTO convertToDto(Post post){
        PostResponseDTO postResponseDTO=new PostResponseDTO();
        postResponseDTO.setText(post.getText());
        return postResponseDTO;
    }
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }
    public List<PostResponseDTO> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findAllByUserUserId(userId);
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
