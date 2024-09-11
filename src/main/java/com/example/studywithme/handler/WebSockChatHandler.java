package com.example.studywithme.handler;

import jakarta.websocket.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
@Slf4j
@Component
public class WebSockChatHandler  extends TextWebSocketHandler{
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("message: {}", message.getPayload());
        session.sendMessage(new TextMessage("hello"));
    }
}
