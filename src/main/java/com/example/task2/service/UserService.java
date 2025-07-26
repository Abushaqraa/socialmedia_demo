package com.example.task2.service;

import com.example.task2.DTO.UserRequestDTO;
import com.example.task2.DTO.UserResponseDTO;
import com.example.task2.model.User;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User registerUser(UserRequestDTO userRequestDTO){
        if (userRepository.existsByUsernameAndEmail(userRequestDTO.getUsername(), userRequestDTO.getEmail())){
            throw new RuntimeException("username or email already exist");
        }
        User user = new User();
        user.setUsername(userRequestDTO.username);
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail((userRequestDTO.email));
        return userRepository.save(user);
    }
    public List<UserResponseDTO> getAllUsers(){
        List<User> user = userRepository.findAll();
        return user.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private UserResponseDTO convertToDTO(User user){
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }
    public UserResponseDTO getUserById(long id){
        User user =userRepository.findById(id).orElse(null);
        if (user == null){
            return null;
        }
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
