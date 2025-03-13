// back/src/main/java/com/example/back/service/ChatService.java
package com.example.back.service;

import com.example.back.model.ChatMessage;
import com.example.back.model.Response;
import com.example.back.repository.ChatMessageRepository;
import com.example.back.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ResponseRepository responseRepository;

    public ChatService(ChatMessageRepository chatMessageRepository, ResponseRepository responseRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.responseRepository = responseRepository;
    }

    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    public List<Response> findAnswers(String question) {
        return responseRepository.findAllByQuestion(question.toLowerCase());
    }
}