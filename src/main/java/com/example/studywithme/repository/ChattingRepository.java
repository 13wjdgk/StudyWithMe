package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.Chatting;

import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {
    List<Chatting> findByChatRoom_ChatroomId(Integer chatRoomId);
}
