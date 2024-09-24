package com.example.studywithme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.studywithme.entity.LogEntry;

@Service
public class LogService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private LogFileReader logFileReader;

	// 로그 데이터를 MongoDB에 저장하는 메서드
	public void saveLogs() {
		List<LogEntry> logEntries = logFileReader.readLogs();
		if (!logEntries.isEmpty()) {
			mongoTemplate.insertAll(logEntries);
		}
	}
}
