// back/src/main/java/com/example/back/service/ChatService.java
package com.example.back.service;

import com.example.back.dto.ChatMessageDto;
import com.example.back.mapper.ChatMessageMapper;
import com.example.back.model.ChatMessage;
import com.example.back.model.User;
import com.example.back.repository.ChatMessageRepository;
import com.example.back.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;

    public ChatService(ChatMessageRepository chatMessageRepository, ChatMessageMapper chatMessageMapper, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageMapper = chatMessageMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public ChatMessage save(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDto);
        User user = userRepository.findByName(chatMessageDto.getSender());
        chatMessage.setUser(user);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessageDto> findAll() {
        return chatMessageRepository.findAll()
                .stream()
                .map(chatMessageMapper:: toDto)
                .collect(Collectors.toList());
    }

}