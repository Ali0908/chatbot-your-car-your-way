package com.example.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String sender;
    private String content;
    private LocalDateTime timestamp;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chat_message_response",
            joinColumns = @JoinColumn(name = "chat_message_id"),
            inverseJoinColumns = @JoinColumn(name = "response_id")
    )
    private List<Response> response;
}

