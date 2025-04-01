package com.example.back.controller;

import com.example.back.dto.ChatMessageDto;
import com.example.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatMessageRestController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatMessageDto> getAllMessages() {
        return chatService.findAll();
    }
}
