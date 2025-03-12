package com.example.back.service;

import com.example.back.model.User;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(String name) {
        User user = User.builder().name(name).build();
        userRepository.save(user);
    }

    public User getLastUser() {
        return userRepository.findTopByOrderByIdDesc();
    }
}
