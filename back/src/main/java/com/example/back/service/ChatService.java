package com.example.back.service;

import com.example.back.model.ChatMessage;
import com.example.back.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }


    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
