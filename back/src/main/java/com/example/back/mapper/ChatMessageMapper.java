package com.example.back.mapper;

import com.example.back.dto.ChatResponseDto;
import com.example.back.model.ChatMessage;
import com.example.back.dto.ChatMessageDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatMessageMapper {



    public ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        LocalDateTime date = LocalDateTime.now();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(chatMessageDto.getId());
        chatMessage.setContent(chatMessageDto.getContent());
        chatMessage.setTimestamp(date);
        return chatMessage;
    }

    public ChatResponseDto toDto(ChatMessage chatMessage) {
        return new ChatResponseDto(
                chatMessage.getId(),
                chatMessage.getContent(),
                chatMessage.getTimestamp(),
                chatMessage.getUser().getName()
        );
    }
}
