package com.example.back.repository;

import com.example.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findTopByOrderByIdDesc();

    User findByName(String name);

    List<User> findAllByOrderByIdAsc();
}


