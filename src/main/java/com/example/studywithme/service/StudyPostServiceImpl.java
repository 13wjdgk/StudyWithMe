package com.example.studywithme.service;

import com.example.studywithme.dto.StudyPostDTO;
import com.example.studywithme.dto.UserDto;
import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.repository.CategoryRepository;
import com.example.studywithme.repository.StudyPostRepository;
import com.example.studywithme.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
public class StudyPostServiceImpl implements StudyPostService {

    private final StudyPostRepository studyPostRepository;
    private final CategoryRepository categoryRepository;
    private final UsersRepository userRepository;
    private final HttpSession session;  // 세션 추가

    @Autowired
    public StudyPostServiceImpl(StudyPostRepository studyPostRepository, CategoryRepository categoryRepository, UsersRepository userRepository, HttpSession session) {
        this.studyPostRepository = studyPostRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.session = session;  // 세션 주입
    }

    // 세션에서 현재 로그인된 사용자 ID 가져오기
    private String getCurrentUserIdFromSession() {
       UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authenticated");
        }
        return userDto.getUserId();  // 세션에서 UserDto의 userId 반환*/

    }

    @Transactional
    @Override
    public StudyPostDTO createStudyPost(StudyPostDTO studyPostDto) {
        // 1. 새로운 카테고리 생성
        Category category = new Category();
        category.setLanguage(studyPostDto.getLanguage());
        category.setCertification(studyPostDto.getCertification());
        category.setMajor(studyPostDto.getMajor());
        category.setCareer(studyPostDto.getCareer());
        category.setExam(studyPostDto.getExam());
        category.setHobbies(studyPostDto.getHobbies());
        category.setProgramming(studyPostDto.getProgramming());
        category.setSelfDirected(studyPostDto.getSelfDirected());
        category.setEtc(studyPostDto.getEtc());
        category.setMeetType(studyPostDto.getMeetType());

        Category savedCategory = categoryRepository.save(category);

        // 2. 유저 정보 설정 (세션에서 ID 가져오기)
        String currentUserId = getCurrentUserIdFromSession();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // 3. 글 작성 정보 설정
        StudyPost studyPost = new StudyPost();
        studyPost.setTitle(studyPostDto.getTitle());
        studyPost.setDescription(studyPostDto.getDescription());
        studyPost.setStudyType(studyPostDto.getStudyType());
        studyPost.setStudyDate(studyPostDto.getStudyDate());
        studyPost.setEndDate(studyPostDto.getEndDate());
        studyPost.setDeadline(studyPostDto.getDeadline());
        studyPost.setMaxMembers(studyPostDto.getMaxMembers());
        studyPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));  // 현재 시각 자동 생성
        studyPost.setCategory(savedCategory);  // 저장된 카테고리 연결
        studyPost.setUser(user);  // 로그인된 사용자 정보 연결

        // 스터디 포스트 저장
        StudyPost savedPost = studyPostRepository.save(studyPost);

        // 저장된 StudyPost를 DTO로 변환하여 반환
        return convertToDto(savedPost);
    }

    @Override
    public StudyPostDTO updateStudyPost(int postId, StudyPostDTO studyPostDto) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid post ID: " + postId));

        // 세션에서 현재 로그인된 사용자와 글 작성자 비교
        String currentUserId = getCurrentUserIdFromSession();
        if (!studyPost.getUser().getUserId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this post.");
        }

        // 기존 글 정보 수정
        studyPost.setTitle(studyPostDto.getTitle());
        studyPost.setDescription(studyPostDto.getDescription());
        studyPost.setStudyType(studyPostDto.getStudyType());
        studyPost.setStudyDate(studyPostDto.getStudyDate());
        studyPost.setEndDate(studyPostDto.getEndDate());
        studyPost.setDeadline(studyPostDto.getDeadline());
        studyPost.setMaxMembers(studyPostDto.getMaxMembers());

        StudyPost updatedPost = studyPostRepository.save(studyPost);
        return convertToDto(updatedPost);
    }

    @Override
    public void deleteStudyPost(int postId) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid post ID: " + postId));

        // 세션에서 현재 로그인된 사용자와 글 작성자 비교
        String currentUserId = getCurrentUserIdFromSession();
        if (!studyPost.getUser().getUserId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this post.");
        }

        studyPostRepository.delete(studyPost);
    }

    @Override
    public StudyPostDTO getStudyPostById(int postId) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));

        /*// 세션에서 현재 로그인된 사용자 정보 설정
        String currentUserId = getCurrentUserIdFromSession();

        // DTO 변환 후 로그인된 사용자 ID를 설정
        StudyPostDTO resultDto = convertToDto(studyPost);
        resultDto.setCurrentUserId(currentUserId);  // 세션에서 가져온 유저 ID 설정
        return resultDto;*/
        return convertToDto(studyPost);
    }

    private StudyPostDTO convertToDto(StudyPost studyPost) {
        return new StudyPostDTO(
                studyPost.getPostId(),
                studyPost.getTitle(),
                studyPost.getDescription(),
                studyPost.getStudyType(),
                studyPost.getStudyDate(),
                studyPost.getEndDate(),
                studyPost.getDeadline(),
                studyPost.getMaxMembers(),
                studyPost.getCreatedAt(),
                studyPost.getCategory().getCategoryId(),
                studyPost.getCategory().getLanguage(),
                studyPost.getCategory().getCertification(),
                studyPost.getCategory().getMajor(),
                studyPost.getCategory().getCareer(),
                studyPost.getCategory().getExam(),
                studyPost.getCategory().getHobbies(),
                studyPost.getCategory().getProgramming(),
                studyPost.getCategory().getSelfDirected(),
                studyPost.getCategory().getEtc(),
                studyPost.getCategory().getMeetType().name()
        );
    }
}
