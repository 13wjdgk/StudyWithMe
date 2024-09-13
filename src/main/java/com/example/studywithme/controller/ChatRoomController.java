package com.example.studywithme.controller;

import com.example.studywithme.dto.ChatRoomDto;
import com.example.studywithme.dto.ChatRoomResult;
import com.example.studywithme.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChattingRoomService  chattingRoomService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    @ResponseBody
    public ChatRoomResult room(@RequestParam String userId) {

        return  chattingRoomService.findAllRoom(userId);
    }

    // 모든 채팅방 목록 반환
    //@GetMapping("/rooms")
    //@ResponseBody
    //public ChatRoomResult room() {
        //return chattingRoomService.findAllRoom();
   // }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomResult createRoom(@RequestParam String name,@RequestParam String userId,@RequestParam Integer postId ) {
        System.out.println(userId);
        return chattingRoomService.createChatRoom(name,userId,postId);
    }

  //  @GetMapping("/room/enter/{roomId}")
   // public String roomDetail(Model model, @PathVariable String roomId) {
    //    System.out.println(roomId);
    //    model.addAttribute("roomId", roomId);
     //   return "dd";
    //}

    // 채팅방 입장 화면

    // 특정 채팅방 조회
   // @GetMapping("/room/{roomId}")
    //@ResponseBody
   // public ChatRoom roomInfo(@PathVariable String roomId) {
   //     return chatRoomRepository.findRoomById(roomId);
   // }*/
}