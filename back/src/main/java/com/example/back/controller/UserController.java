package com.example.back.controller;


import com.example.back.dto.UserDto;
import com.example.back.model.User;
import com.example.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    public User save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    // Build getmapping to get last user save in database
    @GetMapping("user")
    public ResponseEntity<User> getLastUser() {
        return ResponseEntity.ok(userService.getLastUser());
    }
}
