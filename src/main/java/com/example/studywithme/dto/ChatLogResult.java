package com.example.studywithme.dto;

import lombok.Data;

import java.util.List;
@Data
public class ChatLogResult {
    String result;
    List<ChatDto>ChatDtoList;
}
