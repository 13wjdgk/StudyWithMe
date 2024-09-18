package com.example.studywithme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studywithme.dto.StudyPostDto;
import com.example.studywithme.entity.User;
import com.example.studywithme.enums.SortType;
import com.example.studywithme.repository.StudyPostRepository;
import com.example.studywithme.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyPostSearchService {
	private final StudyPostRepository studyPostRepository;
	private final UsersRepository usersRepository;
	public List<StudyPostDto> searchStudyPostList(SortType sortType , int page, int size,String userId) {
		User user = usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
		if(sortType.equals(SortType.LATEST)){
			studyPostRepository.findAllSortedByLatest(page, size);
			return null;
		}
		else if(sortType.equals(SortType.POPULAR)){
			studyPostRepository.findAllSortedByPopular(page, size);
			return null;
		}
		else if(sortType.equals(SortType.RECOMMEND)){
			return studyPostRepository.findAllSortedByRecommend(page, size,user.getCategory().getCategoryId());
		}
		else if(sortType.equals(SortType.OLDEST)){
			studyPostRepository.findAllSortedByOldest(page, size);
			return null;
		}

		return null;

	}
}
