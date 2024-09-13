package com.example.studywithme.service;

import com.example.studywithme.dto.StudyPostDto;
import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.repository.CategoryRepository;
import com.example.studywithme.repository.StudyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
public class StudyPostServiceImpl implements StudyPostService {

    private final StudyPostRepository studyPostRepository;
    private final CategoryRepository categoryRepository;

    // 테스트용 유저 ID 선언 (모든 메서드에서 사용)
    private final String testUserId = "user002";  // 실제 구현에서는 세션에서 유저 ID를 가져올 예정

    @Autowired
    public StudyPostServiceImpl(StudyPostRepository studyPostRepository, CategoryRepository categoryRepository) {
        this.studyPostRepository = studyPostRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public StudyPostDto createStudyPost(StudyPostDto studyPostDto) {
        // 1. 새로운 카테고리 생성 (항상 새로운 카테고리)
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

        // 2. 유저 정보 생성
        User user = new User();
        user.setUserId(testUserId);

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
        studyPost.setUser(user);  // 유저 연결

        StudyPost savedPost = studyPostRepository.save(studyPost);
        return convertToDto(savedPost);
    }

    @Override
    public StudyPostDto updateStudyPost(int postId, StudyPostDto studyPostDto) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid post ID: " + postId));

        // 작성자와 현재 유저 ID 비교
        if (!studyPost.getUser().getUserId().equals(testUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this post.");
        }

        // 기존 글 정보를 수정
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

        // 작성자와 현재 유저 ID 비교
        if (!studyPost.getUser().getUserId().equals(testUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this post.");
        }

        studyPostRepository.delete(studyPost);
    }

    @Override
    public StudyPostDto getStudyPostById(int postId) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));
        return convertToDto(studyPost);
    }

    private StudyPostDto convertToDto(StudyPost studyPost) {
        return new StudyPostDto(
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
