package com.example.studywithme.service;


import com.example.studywithme.dto.ChatDto;
import com.example.studywithme.dto.ChatLogResult;
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
    public ChatLogResult ChattingLog(Integer roomId)
    {
        ChatLogResult chatLogResult = new ChatLogResult();
        List<Chatting> chat =chattingRepository.findByChatRoom_ChatroomId(roomId);
        List<ChatDto>chatDtos= new ArrayList<>();
        chat.forEach(chatting -> {
            ChatDto chatDto = new ChatDto();
            chatDto.setSendtime(chatting.getSendTime());
            chatDto.setMessage(chatting.getMessage());
            chatDto.setSender(chatting.getUser().getNickname());
            chatDto.setSenderId(chatting.getUser().getUserId());
            chatDto.setRoomId(chatting.getChatRoom().getChatroomId());
            chatDtos.add(chatDto);
        });
        chatLogResult.setResult("success");
        chatLogResult.setChatDtoList(chatDtos);
        System.out.println(chatDtos);
        return chatLogResult;
    }
}
