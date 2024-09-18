package com.example.studywithme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Chatting_Participants")
@Data
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
