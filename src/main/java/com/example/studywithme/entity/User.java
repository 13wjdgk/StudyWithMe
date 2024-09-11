package com.example.studywithme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Users")
@Getter
public class User {

	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	// Getters and Setters
}

