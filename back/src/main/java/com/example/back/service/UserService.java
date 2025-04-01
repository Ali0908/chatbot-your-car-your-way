package com.example.back.service;

import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;
import com.example.back.model.User;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public User save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return user;
    }

    public User getLastUser() {
        return userRepository.findTopByOrderByIdDesc();
    }
}