package com.example.studywithme.repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studywithme.dto.StudyPostDTO;
import com.example.studywithme.entity.Category;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.enums.MeetType;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public interface StudyPostRepository extends JpaRepository<StudyPost, Integer> {
	default List<StudyPost> findAllSortedByLatest(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		return findAllBy(pageable).getContent();
	}
	// 인기순 기준 정해야함
	default List<StudyPost> findAllSortedByPopular(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		return findAllBy(pageable).getContent();
	}

	default List<StudyPostDTO> findAllSortedByRecommend(int page, int size, String categoryId) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
		Page<Object[]> results = findAllByCategory(categoryId, pageable);
		return results.stream()
			.map(result -> new StudyPostDTO(
				(Integer) result[0], // postId
				(String) result[1],  // title
				(String) result[2],  // description
				(String) result[3],  // studyType
				(LocalDate) result[4], // studyDate
				(LocalDate) result[5], // endDate
				(LocalDate) result[6], // deadline
				(Integer) result[7],  // maxMembers
				(Timestamp) result[8], // createdAt
				(String) result[9],  // userId
				(Boolean) result[10], // language
				(Boolean) result[11], // certification
				(Boolean) result[12], // major
				(Boolean) result[13], // career
				(Boolean) result[14], // exam
				(Boolean) result[15], // hobbies
				(Boolean) result[16], // programming
				(Boolean) result[17], // selfDirected
				(Boolean) result[18], // etc
				(String) result[19] // meetType
			))
			.collect(Collectors.toList());
	}

	default List<StudyPost> findAllSortedByOldest(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
		return findAllBy(pageable).getContent();
	}


	Page<StudyPost> findAllBy(Pageable pageable);
	@Query(value = "select s_c.* from ( "
		+ "SELECT post.post_id, post.title, post.description, post.study_type, post.study_date, post.end_date, post.deadline ,  post.max_members , post.created_at , post.user_id , c.* "
		+ "FROM Study_Posts post JOIN Category c ON post.category_id = c.category_id "
		+ ") as s_c "
		+ "join Category c1 "
		+ "where c1.category_id = :categoryId "
		+ "and ( s_c.language = c1.language or s_c.certification = c1.certification or s_c.major = c1.major or s_c.career = c1.career "
		+ "or s_c.exam = c1.exam or s_c.hobbies = c1.hobbies or s_c.programming = c1.programming or s_c.self_directed = c1.self_directed "
		+ "or s_c.etc = c1.etc or s_c.meet_type = c1.meet_type ) ",nativeQuery = true)
	Page<Object[]> findAllByCategory(String categoryId, Pageable pageable);


}
