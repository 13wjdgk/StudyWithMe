package com.example.studywithme.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ChatRoomResult {

    private String result;
    List<ChatRoomDto>chatRoomDtos;
}
