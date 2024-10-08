package com.example.studywithme.entity;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ChattingRoom")
public class ChattingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chatRoom_id", nullable = false)
	private int chatroomId;

	@Column(name = "createdDate")
	private Timestamp createdDate;


	@Column(name = "chattingRoom_Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "recruitmentPostId", nullable = false)
	private StudyPost recruitmentPost;

	// Getters and Setters
}
