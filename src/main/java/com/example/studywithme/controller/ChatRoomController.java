package com.example.studywithme.controller;

import com.example.studywithme.dto.ChatLogResult;
import com.example.studywithme.dto.ChatRoomDto;
import com.example.studywithme.dto.ChatRoomResult;
import com.example.studywithme.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChattingRoomService  chattingRoomService;

    // 채팅 리스트 화면
    @GetMapping("/room/{userId}")
    @ResponseBody
    public ChatRoomResult room(@PathVariable String userId) {
        return  chattingRoomService.findAllRoom(userId);
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomResult createRoom(@RequestParam String name,@RequestParam String userId,@RequestParam Integer postId ) {
        return chattingRoomService.createChatRoom(name,userId,postId);
    }

    //@DeleteMapping("/room/{roomId}")
    //@ResponseBody
    /*public ChatRoomResult deleteRoom(@PathVariable Integer roomId ) {

            if(chattingRoomService.ChattingRoomCount(roomId)>1)
            {//한명이상 있으면 나혼자만 나가는걸로
                return
            }
            else
                return chattingRoomService.deleteChatRoom(roomId);
    }*/


    // 특정 채팅방 조회
   // @GetMapping("/room/{roomId}")
    //@ResponseBody
   // public ChatRoom roomInfo(@PathVariable String roomId) {
   //     return chatRoomRepository.findRoomById(roomId);
   // }*/
}