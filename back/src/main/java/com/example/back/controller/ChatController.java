// back/src/main/java/com/example/back/controller/ChatController.java
package com.example.back.controller;

import com.example.back.model.Response;
import com.example.back.model.ChatMessage;
import com.example.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage)  {

        List<Response> responses = chatService.findAnswers(chatMessage.getContent());

        chatMessage.setResponse(responses);

        chatService.save(chatMessage);

        return ChatMessage.builder()
                .sender(chatMessage.getSender())
                .content(chatMessage.getContent())
                .response(responses)
                .timestamp(LocalDateTime.now())
                .build();
    }
}