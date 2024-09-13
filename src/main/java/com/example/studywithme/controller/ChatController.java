package com.example.studywithme.controller;

// import 생략...

import com.example.studywithme.dto.ChatDto;
import com.example.studywithme.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;
    @MessageMapping("/chat/message")
    public void message(ChatDto message) {
        if (ChatDto.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        chatService.ChatInsert(message,message.getSenderId());
    }
}