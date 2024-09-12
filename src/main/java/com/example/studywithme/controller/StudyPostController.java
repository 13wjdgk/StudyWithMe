package com.example.studywithme.controller;

import com.example.studywithme.dto.StudyPostDto;
import com.example.studywithme.service.StudyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-posts")
@RequiredArgsConstructor
public class StudyPostController {

    private final StudyPostService studyPostService;

    @PostMapping
    public ResponseEntity<StudyPostDto> createStudyPost(@RequestBody StudyPostDto studyPostDto) {
        StudyPostDto createdPost = studyPostService.createStudyPost(studyPostDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<StudyPostDto> getStudyPostById(@PathVariable int postId) {
        StudyPostDto studyPostDto = studyPostService.getStudyPostById(postId);
        return new ResponseEntity<>(studyPostDto, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<StudyPostDto> updateStudyPost(
            @PathVariable int postId,
            @RequestBody StudyPostDto studyPostDto) {
        StudyPostDto updatedPost = studyPostService.updateStudyPost(postId, studyPostDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteStudyPost(@PathVariable int postId) {
        studyPostService.deleteStudyPost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
