// back/src/main/java/com/example/back/repository/ResponseRepository.java
package com.example.back.repository;

import com.example.back.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findAllByQuestion(String question);
}