package com.example.studywithme.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.studywithme.service.LogService;

@Component
public class LogScheduler {

	@Autowired
	private LogService logService;

	// 매일 밤 12시에 실행되는 스케줄러
	@Scheduled(cron = "0 0 0 * * ?")
	public void scheduleLogSaving() {
		logService.saveLogs();
		System.out.println("Logs saved to MongoDB at midnight.");
	}
}
