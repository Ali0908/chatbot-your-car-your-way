// back/src/main/java/com/example/back/service/ChatService.java
package com.example.back.service;

import com.example.back.model.ChatMessage;
import com.example.back.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAll() {
        return chatMessageRepository.findAll();
    }

}