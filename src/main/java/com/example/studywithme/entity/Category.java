package com.example.studywithme.entity;


import com.example.studywithme.enums.MeetType;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@Table(name = "Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false)
	private int categoryId ;

	@Column(name = "language")
	private Boolean language = false;

	@Column(name = "certification")
	private Boolean certification = false;

	@Column(name = "major")
	private Boolean major = false;

	@Column(name = "career")
	private Boolean career = false;

	@Column(name = "exam")
	private Boolean exam = false;

	@Column(name = "hobbies")
	private Boolean hobbies  = false;

	@Column(name = "programming")
	private Boolean programming = false;

	@Column(name = "self_directed")
	private Boolean selfDirected = false;

	@Column(name = "etc")
	private Boolean etc = false;

	@Enumerated(EnumType.STRING)
	@Column(name = "meet_type")
	private MeetType meetType = MeetType.대면;

}
