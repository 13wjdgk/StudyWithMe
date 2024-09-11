package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.ChattingParticipant;

public interface ChattingParticipantRepository extends JpaRepository<ChattingParticipant, Integer> {
}
