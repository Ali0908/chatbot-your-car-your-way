package com.example.back.controller;

import com.example.back.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    // Méthode invoquée lors de la réception d'un message sur /app/sendMessage
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) throws Exception {
        // Ici, vous pouvez ajouter une logique de validation ou de traitement
        return chatMessage;
     // Appeler le service pour sauvegarder le message en base de données, en sauvegardant le nom de l'utilisateur et le contenu du message

        // Ajouter au front un formulaire pour qu'il saisie son prénom ensuite il pourra accéder au formulaire de chat
    }

}
