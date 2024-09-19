package com.example.studywithme.service;

import com.example.studywithme.dto.UserDto;
import com.example.studywithme.entity.User;

public interface UserService {

    UserDto detailUser(String id);
    UserDto detailUser(String userId, String nickname);
    UserDto updateUser(User user);
    UserDto login(String id, String password);
    UserDto insertUser(User user);
}
