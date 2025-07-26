package com.example.task2.controller;

import com.example.task2.DTO.UserRequestDTO;
import com.example.task2.DTO.UserResponseDTO;
import com.example.task2.model.User;
import com.example.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDTO userRequestDTO){
        User user =userService.registerUser(userRequestDTO);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/fetchallusers")
        public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users =userService.getAllUsers();
        return ResponseEntity.ok(users);
        }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        UserResponseDTO user = userService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
