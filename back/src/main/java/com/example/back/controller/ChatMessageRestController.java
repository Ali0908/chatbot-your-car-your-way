package com.example.back.controller;

import com.example.back.model.ChatMessage;
import com.example.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ChatMessageRestController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatService.findAll();
    }
}
