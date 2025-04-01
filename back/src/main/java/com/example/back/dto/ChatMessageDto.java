package com.example.back.dto;


import com.example.back.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private String sender;
}
