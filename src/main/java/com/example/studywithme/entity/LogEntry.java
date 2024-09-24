package com.example.studywithme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")
public class LogEntry {

	@Id
	private String id;
	private String level;
	private String message;
	private String timestamp;

	// Getters and Setters

	public LogEntry(String level, String message, String timestamp) {
		this.level = level;
		this.message = message;
		this.timestamp = timestamp;
	}

	// toString(), hashCode(), equals() if needed
}
