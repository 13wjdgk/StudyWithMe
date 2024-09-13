package com.example.studywithme.controller;

// import 생략...

import com.example.studywithme.dto.ChatDto;
import com.example.studywithme.dto.ChatLogResult;
import com.example.studywithme.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;
    @MessageMapping("/message")
    public void message(ChatDto message) {
        if (ChatDto.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        message.setSendtime(new Timestamp(System.currentTimeMillis()));
        chatService.ChatInsert(message,message.getSenderId());

    }
    @GetMapping("/chatting")
    public ChatLogResult ChttingLog(@RequestParam  Integer roomId)
    {
        return chatService.ChattingLog(roomId);
    }
}