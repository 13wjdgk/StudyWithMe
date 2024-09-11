package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.StudyPost;

public interface StudyPostRepository extends JpaRepository<StudyPost, Integer> {
}
