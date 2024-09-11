package com.example.studywithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studywithme.entity.User;

public interface UsersRepository extends JpaRepository<User, String> {

}
