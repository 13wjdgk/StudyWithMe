package com.example.studywithme.repository;

import com.example.studywithme.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.ChattingParticipant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChattingParticipantRepository extends JpaRepository<ChattingParticipant, Integer> {
    @Query("SELECT cp.chatRoom FROM ChattingParticipant cp WHERE cp.user.userId = :userId")
    List<ChattingRoom> findChatRoomsByUserId(@Param("userId") String userId);
}
