package com.example.studywithme.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studywithme.dto.StudyPostDTO;
import com.example.studywithme.dto.UserDto;
import com.example.studywithme.enums.SortType;
import com.example.studywithme.service.StudyPostSearchService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/StudyList")
public class MainController {
	private final StudyPostSearchService studyPostSearchService;
	@GetMapping("/{sortType}/{page}/{size}")
	public List<StudyPostDTO> getStudyPostList(@PathVariable String sortType, @PathVariable int page,@PathVariable int size, HttpSession session) {
		if(sortType.equals("RECOMMEND")){
			UserDto userDto = (UserDto) session.getAttribute("userDto");
			List<StudyPostDTO> list = studyPostSearchService.searchRecommendStudyPostList(page, size, userDto.getUserId());
			System.out.println("list : "+list);
			return list;
		}else{
			SortType type = SortType.valueOf(sortType);
			return studyPostSearchService.searchStudyPostList(type, page, size);
		}
	}
	@GetMapping("/studyPost")
	public void getStudyPost() {
		studyPostSearchService.getStudyPost(7);
	}
}
