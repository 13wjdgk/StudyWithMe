package com.example.studywithme.entity;


import com.example.studywithme.enums.MeetType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "category")
public class Category {

	@Column(name = "career")
	private Boolean career = false;

	@Column(name = "certification")
	private Boolean certification = false;

	@Column(name = "etc")
	private Boolean etc = false;

	@Column(name = "exam")
	private Boolean exam = false;

	@Column(name = "hobbies")
	private Boolean hobbies  = false;

	@Column(name = "language")
	private Boolean language = false;

	@Column(name = "major")
	private Boolean major = false;

	@Column(name = "programming")
	private Boolean programming = false;

	@Column(name = "self_directed")
	private Boolean selfDirected = false;

	@Id
	@Column(name = "category_id", nullable = false)
	private String id ;

	@Enumerated(EnumType.STRING)
	@Column(name = "meet_type")
	private MeetType meetType = MeetType.대면;

	// Getters and Setters
}
