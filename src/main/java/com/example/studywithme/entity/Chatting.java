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
@Entity
@Table(name = "Chatting")
public class Chatting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id", nullable = false)
	private Integer chatId;

	@Column(name = "message", length = 200)
	private String message;

	@Column(name = "sendTime")
	private Timestamp sendTime;

	@ManyToOne
	@JoinColumn(name = "chatRoomId", nullable = false)
	private ChattingRoom chatRoom;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	// Getters and Setters
}