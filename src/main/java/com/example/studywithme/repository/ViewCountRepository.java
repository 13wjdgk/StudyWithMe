package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.ViewCount;

public interface ViewCountRepository extends JpaRepository<ViewCount, Integer> {
}
