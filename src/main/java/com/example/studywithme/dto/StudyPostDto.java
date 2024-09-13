package com.example.studywithme.dto;

import com.example.studywithme.enums.MeetType;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class StudyPostDto {

    private Integer postId;
    private String title;
    private String description;
    private String studyType;
    private LocalDate studyDate;
    private LocalDate endDate;
    private LocalDate deadline;
    private Integer maxMembers;
    private Timestamp createdAt;
    private String userId;
    private int categoryId;

    private Boolean language;
    private Boolean certification;
    private Boolean major;
    private Boolean career;
    private Boolean exam;
    private Boolean hobbies;
    private Boolean programming;
    private Boolean selfDirected;
    private Boolean etc;
    private MeetType meetType;

    public StudyPostDto(Integer postId, String title, String description, String studyType, LocalDate studyDate,
                        LocalDate endDate, LocalDate deadline, Integer maxMembers, Timestamp createdAt, int categoryId,
                        Boolean language, Boolean certification, Boolean major, Boolean career, Boolean exam,
                        Boolean hobbies, Boolean programming, Boolean selfDirected, Boolean etc, String meetType) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.studyType = studyType;
        this.studyDate = studyDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.maxMembers = maxMembers;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.language = language;
        this.certification = certification;
        this.major = major;
        this.career = career;
        this.exam = exam;
        this.hobbies = hobbies;
        this.programming = programming;
        this.selfDirected = selfDirected;
        this.etc = etc;
        this.meetType = MeetType.valueOf(meetType);
    }
}
