package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.Chatting;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {
}
