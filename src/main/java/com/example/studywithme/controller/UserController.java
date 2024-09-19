package com.example.studywithme.controller;

import com.example.studywithme.dto.UserDto;
import com.example.studywithme.dto.UserResultDto;
import com.example.studywithme.entity.User;
import com.example.studywithme.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studywithme")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 사용자 회원가입
     * @param user
     * @return ResponseEntity<UserResultDto>
     */
    @PostMapping("/users")
    public ResponseEntity<UserResultDto> insertUser(@RequestBody User user) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            userResultDto.setUserDto(userService.insertUser(user));
            userResultDto.setResult("success");
        } catch (Exception e) {
            userResultDto.setResult("fail");
        }

        if ("success".equals(userResultDto.getResult())) {
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 사용자 정보 조회 (마이페이지)
     * @param session
     * @return ResponseEntity<UserResultDto>
     */
    @GetMapping("/users")
    public ResponseEntity<UserResultDto> detailUser(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            userResultDto.setUserDto(userService.detailUser(userDto.getUserId()));
            userResultDto.setResult("success");
        } catch (Exception e) {
            userResultDto.setResult("fail");
            e.printStackTrace();
        }

        if ("success".equals(userResultDto.getResult())) {
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 사용자 정보 조회 (임시 비밀번호 발급)
     * @param userId, nickname
     * @return ResponseEntity<UserResultDto>
     */
    @PostMapping("/userDetail")
    public ResponseEntity<UserResultDto> detailUserNickname(@RequestParam("user_id") String userId, @RequestParam("nickname") String nickname) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            UserDto userDto = this.userService.detailUser(userId, nickname);
            userResultDto.setUserDto(userDto);
            userResultDto.setResult("success");
        } catch (Exception e) {
            userResultDto.setResult("fail");
            e.printStackTrace();
        }

        if ("success".equals(userResultDto.getResult())) {
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 사용자 정보 업데이트 (마이페이지 업데이트)
     * @param session
     * @param user
     * @return ResponseEntity<UserResultDto>
     */
    @PutMapping("/users")
    public ResponseEntity<UserResultDto> updateUser(HttpSession session, @RequestBody User user) {
        UserResultDto userResultDto = new UserResultDto();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

//        // 현재 session 상의 유저와 업데이트하려는 유저가 동일하다면
//        if (userDto != null) {
//            try {
//                userResultDto.setUserDto(userService.updateUser(user));
//                userResultDto.setResult("success");
//            } catch (Exception e) {
//                userResultDto.setResult("fail");
//            }
//        } else {
//            userResultDto.setResult("fail");
//        }

        try {
            userResultDto.setUserDto(userService.updateUser(user));
            userResultDto.setResult("success");
        } catch (Exception e) {
            userResultDto.setResult("fail");
        }

        // userResultDto 의 result 에 대응
        if ("success".equals(userResultDto.getResult())) {
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping({"/login"})
    public ResponseEntity<UserResultDto> login(@RequestParam("user_id") String id, @RequestParam("password") String password, HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();
        UserDto userDto = this.userService.login(id, password);
        System.out.println("[controller] userDto : " + userDto);

        try {
            if(userDto != null) {
                userResultDto.setUserDto(userDto);
                userResultDto.setResult("success");
            } else {
                userResultDto.setResult("fail");
            }
        } catch (Exception e) {
            userResultDto.setResult("fail");
        }

        // userResultDto 의 result 에 대응
        if ("success".equals(userResultDto.getResult())) {
            session.setAttribute("userDto", userResultDto.getUserDto());
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping({"/logout"})
    public ResponseEntity<UserResultDto> logout(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            session.invalidate();
            userResultDto.setResult("success");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            userResultDto.setResult("fail");
        }
        // userResultDto 의 result 에 대응
        if ("success".equals(userResultDto.getResult())) {
            return ResponseEntity.ok().body(userResultDto);
        } else if ("fail".equals(userResultDto.getResult())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
