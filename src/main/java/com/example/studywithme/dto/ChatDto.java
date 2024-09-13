package com.example.studywithme.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

//사실상 dto 임시
@Data
public class ChatDto {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, JOIN, TALK
    }
    private MessageType type; // 메시지 타입
    private int roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String senderId;
    private String message; // 메시지
    private Timestamp sendtime;
}