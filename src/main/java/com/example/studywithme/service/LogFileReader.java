package com.example.studywithme.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.example.studywithme.entity.LogEntry;

@Service
public class LogFileReader {

	// 로그 파일 경로 설정
	private static final String LOG_FILE_PATH = "src/logs/studywithme.log";

	public List<LogEntry> readLogs() {
		List<LogEntry> logEntries = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				// 로그 포맷에 맞게 파싱
				String[] logData = line.split("\\s+", 3);
				if (logData.length == 3) {
					String level = logData[0];  // 로그 레벨 (INFO, ERROR 등)
					String timestamp = logData[1];  // 타임스탬프
					String message = logData[2];  // 로그 메시지
					logEntries.add(new LogEntry(level, message, timestamp));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logEntries;
	}
}
