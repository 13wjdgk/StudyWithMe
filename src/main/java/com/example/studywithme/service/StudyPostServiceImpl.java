package com.example.studywithme.service;

import com.example.studywithme.dto.StudyPostDto;
import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.repository.StudyPostRepository;
import com.example.studywithme.service.StudyPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyPostServiceImpl implements StudyPostService {

    private final StudyPostRepository studyPostRepository;

    @Autowired
    public StudyPostServiceImpl(StudyPostRepository studyPostRepository) {
        this.studyPostRepository = studyPostRepository;
    }

    @Override
    public StudyPostDto createStudyPost(StudyPostDto studyPostDto) {
        StudyPost studyPost = new StudyPost();
        studyPost.setTitle(studyPostDto.getTitle());
        studyPost.setDescription(studyPostDto.getDescription());
        studyPost.setStudyType(studyPostDto.getStudyType());
        studyPost.setStudyDate(studyPostDto.getStudyDate());
        studyPost.setEndDate(studyPostDto.getEndDate());
        studyPost.setDeadline(studyPostDto.getDeadline());
        studyPost.setMaxMembers(studyPostDto.getMaxMembers());

        // 테스트용으로 ID
        String testUserId = "user001";
        String categoryId = studyPostDto.getCategoryId();

        // 테스트를 위한 가짜 User 및 Category 객체 생성
        User user = new User();
        user.setUserId(testUserId);
        studyPost.setUser(user);

        Category category = new Category();
        category.setCategoryId(categoryId);
        studyPost.setCategory(category);

        StudyPost savedPost = studyPostRepository.save(studyPost);

        // 저장된 데이터를 다시 DTO로 변환하여 반환
        StudyPostDto savedPostDto = new StudyPostDto();
        savedPostDto.setPostId(savedPost.getPostId());
        savedPostDto.setTitle(savedPost.getTitle());
        savedPostDto.setDescription(savedPost.getDescription());
        savedPostDto.setStudyType(savedPost.getStudyType());
        savedPostDto.setStudyDate(savedPost.getStudyDate());
        savedPostDto.setEndDate(savedPost.getEndDate());
        savedPostDto.setDeadline(savedPost.getDeadline());
        savedPostDto.setMaxMembers(savedPost.getMaxMembers());

        // 테스트용 User와 Category의 ID를 DTO에 설정
        savedPostDto.setUserId(testUserId);
        savedPostDto.setCategoryId(categoryId);

        return savedPostDto;
    }

    // 글 상세
    @Override
    public StudyPostDto getStudyPostById(int postId) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));

        StudyPostDto studyPostDto = new StudyPostDto();
        studyPostDto.setPostId(studyPost.getPostId());
        studyPostDto.setTitle(studyPost.getTitle());
        studyPostDto.setDescription(studyPost.getDescription());
        studyPostDto.setStudyType(studyPost.getStudyType());
        studyPostDto.setStudyDate(studyPost.getStudyDate());
        studyPostDto.setEndDate(studyPost.getEndDate());
        studyPostDto.setDeadline(studyPost.getDeadline());
        studyPostDto.setMaxMembers(studyPost.getMaxMembers());
        studyPostDto.setUserId(studyPost.getUser().getUserId());
        studyPostDto.setCategoryId(studyPost.getCategory().getCategoryId());

        return studyPostDto;
    }

    @Override
    public StudyPostDto updateStudyPost(int postId, StudyPostDto studyPostDto) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));

        // 기존 글 정보를 수정
        studyPost.setTitle(studyPostDto.getTitle());
        studyPost.setDescription(studyPostDto.getDescription());
        studyPost.setStudyType(studyPostDto.getStudyType());
        studyPost.setStudyDate(studyPostDto.getStudyDate());
        studyPost.setEndDate(studyPostDto.getEndDate());
        studyPost.setDeadline(studyPostDto.getDeadline());
        studyPost.setMaxMembers(studyPostDto.getMaxMembers());

        // 수정된 정보를 저장
        StudyPost updatedPost = studyPostRepository.save(studyPost);

        // 수정된 데이터를 DTO로 변환하여 반환
        StudyPostDto updatedPostDto = new StudyPostDto();
        updatedPostDto.setPostId(updatedPost.getPostId());
        updatedPostDto.setTitle(updatedPost.getTitle());
        updatedPostDto.setDescription(updatedPost.getDescription());
        updatedPostDto.setStudyType(updatedPost.getStudyType());
        updatedPostDto.setStudyDate(updatedPost.getStudyDate());
        updatedPostDto.setEndDate(updatedPost.getEndDate());
        updatedPostDto.setDeadline(updatedPost.getDeadline());
        updatedPostDto.setMaxMembers(updatedPost.getMaxMembers());
        updatedPostDto.setUserId(updatedPost.getUser().getUserId());
        updatedPostDto.setCategoryId(updatedPost.getCategory().getCategoryId());

        return updatedPostDto;
    }

    @Override
    public void deleteStudyPost(int postId) {
        StudyPost studyPost = studyPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));

        studyPostRepository.delete(studyPost);
    }
}
