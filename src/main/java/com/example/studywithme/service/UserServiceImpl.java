package com.example.studywithme.service;

import com.example.studywithme.dto.UserDto;
import com.example.studywithme.entity.User;
import com.example.studywithme.repository.CategoryRepository;
import com.example.studywithme.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService , UserDetailsService{
    private final UsersRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto detailUser(String id) {
        // user
        UserDto userDto = new UserDto();
        userRepository.findById(id).ifPresent(user -> {
            userDto.setUserId(user.getUserId());
            userDto.setNickname(user.getNickname());
            userDto.setPassword(passwordEncoder.encode(user.getPassword()));

            // category
            String categoryId =  user.getCategory().getId();
            userDto.setCategory(categoryRepository.findById(categoryId).orElse(null));
        });

        return userDto;
    }

    @Override
    public UserDto updateUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setNickname(user.getNickname());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setCategory(categoryRepository.findById(user.getCategory().getId()).orElse(null));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        categoryRepository.save(user.getCategory());
        userRepository.save(user);

        return userDto;
    }

    @Override
    public UserDto login(String id, String password) {
        UserDto userDto = new UserDto();
        User user = this.userRepository.findById(id).orElse(null);

        if(user != null &&  passwordEncoder.matches(password, user.getPassword()) ) {
            userDto.setUserId(user.getUserId());
            userDto.setNickname(user.getNickname());
            userDto.setPassword(passwordEncoder.encode(user.getPassword())); // -> 이게 맞아?
//            userDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDto.setCategory(categoryRepository.findById(user.getCategory().getId()).orElse(null));
        }

        return userDto;
    }

    @Override
    public UserDto insertUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setNickname(user.getNickname());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        categoryRepository.save(user.getCategory());
        userDto.setCategory(categoryRepository.findById(user.getCategory().getId()).orElse(null));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return userDto;
    }

//    public InMemoryUserDetailsManager userDetailsService(String userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUserId())
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElse(null);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .build();
    }

//    @Override
//    public User loadUserByUsername(String userId) {
//        return userRepository.findById(userId).orElse(null);
//    }
}
