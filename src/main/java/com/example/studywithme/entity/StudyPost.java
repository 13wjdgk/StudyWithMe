package com.example.studywithme.entity;
import java.sql.Timestamp;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Study_Posts")
public class StudyPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Integer postId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "study_type")
	private String studyType;

	@Column(name = "study_date")
	private Date studyDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "max_members")
	private Integer maxMembers;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
