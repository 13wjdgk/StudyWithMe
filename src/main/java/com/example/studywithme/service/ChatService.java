package com.example.studywithme.service;


import com.example.studywithme.dto.ChatDto;
import com.example.studywithme.dto.ChatRoomDto;
import com.example.studywithme.dto.ChatRoomResult;
import com.example.studywithme.entity.*;
import com.example.studywithme.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChattingRepository chattingRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UsersRepository usersRepository;
    public void ChatInsert(ChatDto chatDto,String userId)
    {
        Chatting chat = new Chatting();
        ChattingRoom chattingRoom = new ChattingRoom();
        Optional <ChattingRoom> optionalChattingRoom=chattingRoomRepository.findById(chatDto.getRoomId());
        chat.setMessage(chatDto.getMessage());
        chat.setSendTime(chatDto.getSendtime());
        if(optionalChattingRoom.isPresent())
        {
            chat.setChatRoom(optionalChattingRoom.get());
        }
        else
        {
            return;
        }
        User user = new User();
        Optional<User>userOptional =usersRepository.findById(userId);
        if(userOptional.isPresent())
        {
            chat.setUser(userOptional.get());
        }
        else
        {
            return;
        }
        chattingRepository.save(chat);
        System.out.println(chat);
        return;
    }
}
