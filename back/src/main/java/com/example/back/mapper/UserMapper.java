package com.example.back.mapper;

import com.example.back.dto.UserDto;
import com.example.back.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        return user;
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getName()
        );
    }
}