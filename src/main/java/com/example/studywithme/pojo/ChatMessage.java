package com.example.studywithme.pojo;

import lombok.Getter;
import lombok.Setter;
//사실상 dto 임시
@Getter
@Setter
public class ChatMessage {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, JOIN, TALK
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}