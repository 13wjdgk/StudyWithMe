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

//    @Bean
//    public WebSecurityCustomizer configure(){
//        return (web) -> web.ignoring()
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                        .requestMatchers(
//                                "/users/{id}"
                                new AntPathRequestMatcher("/studywithme/login", "POST"),
                                new AntPathRequestMatcher("/studywithme/users", "GET"),
                                new AntPathRequestMatcher("/studywithme/users", "POST"),
                                new AntPathRequestMatcher("/studywithme/users", "PUT"),
                                new AntPathRequestMatcher("/index.html"),
                                new AntPathRequestMatcher("/register.html"),
                                new AntPathRequestMatcher("/mypage.html"),
                            new AntPathRequestMatcher("/main.html"),
                            new AntPathRequestMatcher("/StudyList/**", "GET")

//                                new AntPathRequestMatcher("/static/register.html"),
//                                new AntPathRequestMatcher("/mypage.html"),
//                                new AntPathRequestMatcher("/static/login.html")

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

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http,
//        BCryptPasswordEncoder bCryptPasswordEncoder, UserService_구 userService)
//            throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(authProvider);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println("preHandle");
//        HttpSession session = request.getSession();
//        UserDto userDto = (UserDto) session.getAttribute("userDto");
//
//        if (userDto == null) {
//            System.out.println("userDto is null");
//            response.sendRedirect("/login.html");
//        }
//        return true;
//    }
}
