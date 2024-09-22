package com.example.studywithme.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studywithme.dto.StudyPostDTO;
import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.entity.ViewCount;
import com.example.studywithme.enums.MeetType;
import com.example.studywithme.enums.SortType;
import com.example.studywithme.repository.CategoryRepository;
import com.example.studywithme.repository.StudyPostRepository;
import com.example.studywithme.repository.UsersRepository;
import com.example.studywithme.repository.ViewCountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyPostSearchService {
	private final StudyPostRepository studyPostRepository;
	private final UsersRepository usersRepository;
	private final ViewCountRepository viewCountService;
	private final CategoryRepository categoryRepository;
	public List<StudyPostDTO> searchStudyPostList(SortType sortType , int page, int size) {
		if(sortType.equals(SortType.LATEST)){
			return studyPostRepository.findAllSortedByLatest(page, size);
		}
		else if(sortType.equals(SortType.POPULAR)){
			return studyPostRepository.findAllSortedByViewCount(page, size);
		}
		else if(sortType.equals(SortType.OLDEST)){
			return studyPostRepository.findAllSortedByOldest(page, size);
		}

		return null;

	}
	public List<StudyPostDTO> searchRecommendStudyPostList(int page, int size,String userId) {
		User user = usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
		return studyPostRepository.findAllSortedByRecommend(page, size,user.getCategory().getCategoryId());
	}
	public void createStudyPost(){
		User user = usersRepository.findById("user006").orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

		Category category = new Category();
		category.setCategoryId(13);
		category.setExam(true);
		category.setCareer(false);
		category.setCertification(false);
		category.setEtc(false);
		category.setHobbies(false);
		category.setLanguage(false);
		category.setMajor(false);
		category.setProgramming(false);
		category.setSelfDirected(false);
		category.setMeetType(MeetType.대면);

		StudyPost studyPost = new StudyPost();
		studyPost.setTitle("test");
		studyPost.setDescription("test");
		studyPost.setStudyType("Programming");
		studyPost.setMaxMembers(4);
		Date date = new Date(System.currentTimeMillis());
		studyPost.setStudyDate(date);
		studyPost.setEndDate(date);
		studyPost.setDeadline(date);
		studyPost.setCategory(categoryRepository.save(category));
		studyPost.setUser(user);
		StudyPost savedStudyPost = studyPostRepository.save(studyPost);
		ViewCount viewCount = new ViewCount();
		viewCount.setPostId(savedStudyPost.getPostId());
		viewCount.setCount(0);
		viewCountService.save(viewCount);

	}
	public void getStudyPost(int postId){

		ViewCount viewCount = viewCountService.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		viewCount.setCount(viewCount.getCount()+1);
		viewCountService.save(viewCount);
	}
}
