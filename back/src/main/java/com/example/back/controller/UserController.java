package com.example.back.controller;


import com.example.back.model.User;
import com.example.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, String>> save(@RequestBody User user) {
        userService.create(user.getName());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

  // Build getmapping to get last user save in database
    @GetMapping("user")
    public ResponseEntity<User> getLastUser() {
        return ResponseEntity.ok(userService.getLastUser());
    }
}
