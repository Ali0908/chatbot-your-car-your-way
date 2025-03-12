package com.example.back.controller;

import com.example.back.model.ChatMessage;
import com.example.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;



    // Méthode invoquée lors de la réception d'un message sur /app/sendMessage
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) throws Exception {
        // Sauvegarde du message dans la base de donnée
        chatService.save(chatMessage);
        return chatMessage;

    }

}
