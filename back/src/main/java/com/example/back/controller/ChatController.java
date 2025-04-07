// back/src/main/java/com/example/back/controller/ChatController.java
package com.example.back.controller;

import com.example.back.dto.ChatMessageDto;
import com.example.back.dto.ChatResponseDto;
import com.example.back.model.ChatMessage;
import com.example.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatResponseDto sendMessage(ChatMessageDto chatMessageDto) {
        return chatService.save(chatMessageDto);
    }
}