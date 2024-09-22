package com.example.studywithme.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.example.studywithme.enums.MeetType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//dto
@Getter
@Setter
@NoArgsConstructor
public class StudyPostDTO {

	private Integer postId;
	private String title;
	private String description;
	private String studyType;
	private Date studyDate;
	private Date endDate;
	private Date deadline;
	private Integer maxMembers;
	private Timestamp createdAt;
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
	private int viewCount = 0;
	private String currentUserId;

	public StudyPostDTO(Integer postId, String title, String description, String studyType, Date studyDate,
		Date endDate, Date deadline, Integer maxMembers, Timestamp createdAt, int categoryId,
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
	public StudyPostDTO(Integer postId, String title, String description, String studyType, Date studyDate,
		Date endDate, Date deadline, Integer maxMembers, Timestamp createdAt, int categoryId,
		Boolean language, Boolean certification, Boolean major, Boolean career, Boolean exam,
		Boolean hobbies, Boolean programming, Boolean selfDirected, Boolean etc, String meetType , int viewCount) {
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
		this.viewCount = viewCount;
	}


}