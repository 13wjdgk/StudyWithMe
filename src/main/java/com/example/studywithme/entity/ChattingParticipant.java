package com.example.studywithme.entity;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Chatting_Participants")
public class ChattingParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id", nullable = false)
	private Integer participantId;

	@ManyToOne
	@JoinColumn(name = "chatRoomId", nullable = false)
	private ChattingRoom chatRoom;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	// Getters and Setters
}
