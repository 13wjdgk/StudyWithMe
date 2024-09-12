package com.example.studywithme.service;

import com.example.studywithme.dto.StudyPostDto;

public interface StudyPostService {

    StudyPostDto createStudyPost(StudyPostDto studyPostDto);

    StudyPostDto getStudyPostById(int postId);

    StudyPostDto updateStudyPost(int postId, StudyPostDto studyPostDto);

    void deleteStudyPost(int postId);
}