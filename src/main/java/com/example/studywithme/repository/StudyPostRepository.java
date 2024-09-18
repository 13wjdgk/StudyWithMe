package com.example.studywithme.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studywithme.dto.StudyPostDTO;

import com.example.studywithme.entity.StudyPost;

public interface StudyPostRepository extends JpaRepository<StudyPost, Integer> {
	default List<StudyPostDTO> findAllSortedByLatest(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
		Page<Object[]> results = findAllBy(pageable);
		return results.stream()
			.map(result -> new StudyPostDTO(
				(Integer) result[0], // postId
				(String) result[1],  // title
				(String) result[2],  // description
				(String) result[3],  // studyType
				(Date) result[4], // studyDate
				(Date) result[5], // endDate
				(Date) result[6], // deadline
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
	// 인기순 기준 정해야함
	default List<StudyPostDTO> findAllSortedByViewCount(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Object[]> results = findAllWithViewCount(pageable);
		return results.stream()
			.map(result -> new StudyPostDTO(
				(Integer) result[0], // postId
				(String) result[1],  // title
				(String) result[2],  // description
				(String) result[3],  // studyType
				(Date) result[4], // studyDate
				(Date) result[5], // endDate
				(Date) result[6], // deadline
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
				(String) result[19],
				(Integer) result[20]// meetType
			))
			.collect(Collectors.toList());
	}

	default List<StudyPostDTO> findAllSortedByRecommend(int page, int size, int categoryId) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "created_at"));
		Page<Object[]> results = findAllByCategory(categoryId, pageable);
		return results.stream()
			.map(result -> new StudyPostDTO(
				(Integer) result[0], // postId
				(String) result[1],  // title
				(String) result[2],  // description
				(String) result[3],  // studyType
				(Date) result[4], // studyDate
				(Date) result[5], // endDate
				(Date) result[6], // deadline
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

	default List<StudyPostDTO> findAllSortedByOldest(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "created_at"));
		Page<Object[]> results = findAllBy(pageable);
		return results.stream()
			.map(result -> new StudyPostDTO(
				(Integer) result[0], // postId
				(String) result[1],  // title
				(String) result[2],  // description
				(String) result[3],  // studyType
				(Date) result[4], // studyDate
				(Date) result[5], // endDate
				(Date) result[6], // deadline
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


	@Query(value = "SELECT post.post_id, post.title, post.description, post.study_type, post.study_date, post.end_date, post.deadline, "
		+ "post.max_members, post.created_at, post.user_id, c.language, c.certification, c.major, c.career, c.exam, c.hobbies, c.programming, c.self_directed, c.etc, c.meet_type "
		+ "FROM Study_Posts post "
		+ "INNER JOIN Category c ON post.category_id = c.category_id  ",
		countQuery = "SELECT count(*) FROM ( "
			+ "SELECT post.post_id, post.title, post.description, post.study_type, post.study_date, post.end_date, post.deadline, "
			+ "post.max_members, post.created_at, post.user_id, c.* "
			+ "FROM Study_Posts post "
			+ "INNER JOIN Category c ON post.category_id = c.category_id ) as sc ",		nativeQuery = true)
	Page<Object[]> findAllBy(Pageable pageable);

	@Query(value = "SELECT sc.post_id, sc.title, sc.description, sc.study_type, sc.study_date, sc.end_date, sc.deadline,sc.max_members, sc.created_at, sc.user_id, sc.language, sc.certification, sc.major, sc.career, sc.exam, sc.hobbies, sc.programming, sc.self_directed, sc.etc, sc.meet_type "
		+ "FROM ( SELECT post.post_id, post.title, post.description, post.study_type, post.study_date, post.end_date, post.deadline, post.max_members, post.created_at, post.user_id, c.category_id, c.language, c.certification, c.major, c.career, c.exam, c.hobbies, c.programming, c.self_directed, c.etc, c.meet_type "
		+ "FROM Study_Posts post JOIN Category c ON post.category_id = c.category_id ) as sc "
		+ ", Category c1  WHERE c1.category_id = :categoryId  and "
		+ "(sc.language = c1.language OR sc.certification = c1.certification OR sc.major = c1.major OR sc.career = c1.career OR sc.exam = c1.exam OR sc.hobbies = c1.hobbies OR sc.programming = c1.programming OR sc.self_directed = c1.self_directed OR sc.etc = c1.etc OR sc.meet_type = c1.meet_type) "
		,

		countQuery = "SELECT count(*) FROM ( SELECT post.post_id, post.title, post.description, post.study_type, post.study_date, post.end_date, post.deadline, post.max_members, post.created_at, post.user_id, c.category_id, c.language, c.certification, c.major, c.career, c.exam, c.hobbies, c.programming, c.self_directed, c.etc, c.meet_type "
			+ "FROM Study_Posts post JOIN Category c ON post.category_id = c.category_id ) as sc "
			+ ", Category c1  WHERE c1.category_id = :categoryId  and "
			+ "(sc.language = c1.language OR sc.certification = c1.certification OR sc.major = c1.major OR sc.career = c1.career OR sc.exam = c1.exam OR sc.hobbies = c1.hobbies OR sc.programming = c1.programming OR sc.self_directed = c1.self_directed OR sc.etc = c1.etc OR sc.meet_type = c1.meet_type) "
		,
		nativeQuery = true)
	Page<Object[]> findAllByCategory(int categoryId, Pageable pageable);

	@Query(value = " SELECT post.post_id, title, description, study_type, study_date, end_date, deadline,max_members, created_at, user_id, language, certification, major, career, exam, hobbies, programming, self_directed, etc, meet_type, vc.count "
		+ "FROM Study_Posts post JOIN Category c ON post.category_id = c.category_id JOIN View_Count vc ON post.post_id=vc.post_id order by vc.count desc",
		countQuery = "SELECT count(*) "
			+ "FROM Study_Posts post JOIN Category c ON post.category_id = c.category_id JOIN View_Count vc ON post.post_id=vc.post_id",
		nativeQuery = true)
	Page<Object[]> findAllWithViewCount( Pageable pageable);

}
