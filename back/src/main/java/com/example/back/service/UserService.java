package com.example.back.service;

import com.example.back.dto.UserDto;
import com.example.back.mapper.UserMapper;
import com.example.back.model.User;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    // Atomic counter to keep track of the next user index
    private final AtomicInteger counter = new AtomicInteger(0);

    public User save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public User getLastUser() {
//        // This method remains for backward compatibility if needed
//        return userRepository.findTopByOrderByIdDesc();
//    }
//
//    public User getNextUser() {
//        // Retrieve all users sorted by ID (or any other consistent field)
//        List<User> users = userRepository.findAllByOrderByIdAsc();
//        if (users.isEmpty()) {
//            return null; // or throw an exception, based on your needs
//        }
//        // Calculate the index in a round-robin fashion
//        int index = counter.getAndUpdate(i -> (i + 1) % users.size());
//        return users.get(index);
//    }

    public User getPreviousUser() {
        // Retrieve all users sorted by ID (or any other consistent field)
        List<User> users = userRepository.findAllByOrderByIdAsc();
        if (users.isEmpty()) {
            return null; // or throw an exception, based on your needs
        }
        // Calculate the index in a round-robin fashion
        int index = counter.getAndUpdate(i -> (i - 1 + users.size()) % users.size());
        return users.get(index);
    }
}
