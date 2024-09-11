package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.ChattingRoom;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {
}
