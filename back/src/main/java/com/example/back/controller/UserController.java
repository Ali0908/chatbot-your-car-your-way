package com.example.back.controller;

import com.example.back.dto.UserDto;
import com.example.back.model.User;
import com.example.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    public User save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    // Existing endpoint for retrieving all users
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    // Existing endpoint for retrieving the last user
//    @GetMapping("user/last")
//    public ResponseEntity<User> getLastUser() {
//        return ResponseEntity.ok(userService.getLastUser());
//    }
//
//    // New endpoint for retrieving the next user in round-robin fashion
//    @GetMapping("user/next")
//    public ResponseEntity<User> getNextUser() {
//        User nextUser = userService.getNextUser();
//        if (nextUser == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(nextUser);
//    }

    // New endpoint for retrieving previous user in round-robin fashion
    @GetMapping("user/previous")
    public ResponseEntity<User> getPreviousUser() {
        User previousUser = userService.getPreviousUser();
        if (previousUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(previousUser);
    }

}
