package com.example.studywithme.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class StudyPostDto {
    private int postId;
    private String title;
    private String description;
    private String studyType;
    private LocalDate studyDate;
    private LocalDate endDate;
    private LocalDate deadline;
    private Integer maxMembers;
    private String userId;
    private String categoryId;
}
