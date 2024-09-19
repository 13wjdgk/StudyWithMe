package com.example.studywithme.config;

import com.example.studywithme.dto.UserDto;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerInterceptor;


@Configuration
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements HandlerInterceptor {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/studywithme/login", "POST"),
                                new AntPathRequestMatcher("/studywithme/users", "GET"),
                                new AntPathRequestMatcher("/studywithme/users", "POST"),
                                new AntPathRequestMatcher("/studywithme/users", "PUT"),
                                new AntPathRequestMatcher("/studywithme/userDetail", "POST"),

                                new AntPathRequestMatcher("/getNewPassword.html"),
                                new AntPathRequestMatcher("/api/study-posts", "POST"),
                                new AntPathRequestMatcher("/api/study-posts/{postId}", "PUT"),
                                new AntPathRequestMatcher("/api/study-posts/{postId}", "DELETE"),
                                new AntPathRequestMatcher("/api/study-posts/{postId}", "GET"),
                                new AntPathRequestMatcher("/index.html"),
                                new AntPathRequestMatcher("/register.html"),
                                new AntPathRequestMatcher("/room.html"),
                                new AntPathRequestMatcher("/ChatRoomDetail.html"),
                                new AntPathRequestMatcher("/chat/room/**", "GET"),  // 채팅 리스트 화면
                                new AntPathRequestMatcher("/chat/room", "POST")   , // 채팅방 생성
                                new AntPathRequestMatcher("/chat/chatting", "GET"),
                                new AntPathRequestMatcher("/room/{roomId}", "DELETE"),  // 새로 추가된 DELETE 요청

                                new AntPathRequestMatcher("/write.html"),
                                new AntPathRequestMatcher("/detail.html"),
                                new AntPathRequestMatcher("/edit.html"),
                                new AntPathRequestMatcher("/register.html"),
                                new AntPathRequestMatcher("/mypage.html"),
                            new AntPathRequestMatcher("/main.html"),
                            new AntPathRequestMatcher("/StudyList/**", "GET")


//                                new AntPathRequestMatcher("/static/register.html"),
//                                new AntPathRequestMatcher("/mypage.html"),
                                //new AntPathRequestMatcher("/static/login.html")


                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login.html")
                        .defaultSuccessUrl("/main.html", true)
                        .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
