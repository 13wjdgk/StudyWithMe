package com.example.studywithme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studywithme.enums.SortType;
import com.example.studywithme.service.StudyPostSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/StudyList")
public class MainController {
	private final StudyPostSearchService studyPostSearchService;
	@GetMapping("/")
	public void getStudyPostList() {
		System.out.println(studyPostSearchService.searchStudyPostList(SortType.RECOMMEND, 1, 10, "user001"));
	}
}
