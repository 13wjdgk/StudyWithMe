package com.example.studywithme.dto;

import com.example.studywithme.entity.StudyPost;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class ChatRoomDto {
    private int roomId;
    private String name;
    private StudyPost recruitmentPost;

    public static ChatRoomDto create(String name) {
        ChatRoomDto chatRoom = new ChatRoomDto();
        //chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}