package com.example.studywithme.controller;

// import 생략...

import com.example.studywithme.dto.ChatDto;
import com.example.studywithme.dto.ChatLogResult;
import com.example.studywithme.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting")
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
        System.out.println("d컨트롤러");
    }

    @GetMapping("/chat_log/{roomId}")
    public ChatLogResult ChttingLog(@PathVariable  int roomId)
    {
        return chatService.ChattingLog(roomId);
    }
}