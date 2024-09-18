package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findById(String userId);
}
