package com.example.studywithme.controller;

import com.example.studywithme.dto.StudyPostDTO;
import com.example.studywithme.service.StudyPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/study-posts")
public class StudyPostController {

    private final StudyPostService studyPostService;

    public StudyPostController(StudyPostService studyPostService) {
        this.studyPostService = studyPostService;
    }

    // 글 작성
    @PostMapping
    public ResponseEntity<StudyPostDTO> createStudyPost(@RequestBody StudyPostDTO studyPostDto) {
        StudyPostDTO createdPost = studyPostService.createStudyPost(studyPostDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // 글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<StudyPostDTO> updateStudyPost(@PathVariable int postId, @RequestBody StudyPostDTO studyPostDto) {
        StudyPostDTO updatedPost = studyPostService.updateStudyPost(postId, studyPostDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deleteStudyPost(@PathVariable int postId) {
        studyPostService.deleteStudyPost(postId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<StudyPostDTO> getStudyPostById(@PathVariable int postId, HttpSession session) {
        StudyPostDTO studyPostDto = studyPostService.getStudyPostById(postId);
        return new ResponseEntity<>(studyPostDto, HttpStatus.OK);
    }
}
