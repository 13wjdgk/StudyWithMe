package com.example.studywithme.dto;

import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    private String nickname;

    private String password;

    private Category category;

    public UserDto(User user){
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.category = user.getCategory();
    }
}
