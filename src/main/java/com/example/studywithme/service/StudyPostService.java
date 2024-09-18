package com.example.studywithme.service;

import com.example.studywithme.dto.StudyPostDTO;

public interface StudyPostService {

    StudyPostDTO createStudyPost(StudyPostDTO studyPostDto);

    StudyPostDTO getStudyPostById(int postId);

    StudyPostDTO updateStudyPost(int postId, StudyPostDTO studyPostDto);

    void deleteStudyPost(int postId);
}