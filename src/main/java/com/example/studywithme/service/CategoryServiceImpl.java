package com.example.studywithme.service;

import com.example.studywithme.dto.CategoryDto;
import com.example.studywithme.entity.Category;
import com.example.studywithme.enums.MeetType;
import com.example.studywithme.repository.CategoryRepository;
import com.example.studywithme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // DTO -> 엔티티 변환
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setLanguage(categoryDto.getLanguage());
        category.setCertification(categoryDto.getCertification());
        category.setMajor(categoryDto.getMajor());
        category.setCareer(categoryDto.getCareer());
        category.setExam(categoryDto.getExam());
        category.setHobbies(categoryDto.getHobbies());
        category.setProgramming(categoryDto.getProgramming());
        category.setSelfDirected(categoryDto.getSelfDirected());
        category.setEtc(categoryDto.getEtc());
        category.setMeetType(MeetType.valueOf(categoryDto.getMeetType()));  // Enum으로 변환

        // 카테고리 저장
        Category savedCategory = categoryRepository.save(category);

        // 저장된 카테고리를 다시 DTO로 반환
        CategoryDto savedCategoryDto = new CategoryDto();
        savedCategoryDto.setCategoryId(savedCategory.getCategoryId());
        savedCategoryDto.setLanguage(savedCategory.getLanguage());
        savedCategoryDto.setCertification(savedCategory.getCertification());
        savedCategoryDto.setMajor(savedCategory.getMajor());
        savedCategoryDto.setCareer(savedCategory.getCareer());
        savedCategoryDto.setExam(savedCategory.getExam());
        savedCategoryDto.setHobbies(savedCategory.getHobbies());
        savedCategoryDto.setProgramming(savedCategory.getProgramming());
        savedCategoryDto.setSelfDirected(savedCategory.getSelfDirected());
        savedCategoryDto.setEtc(savedCategory.getEtc());
        savedCategoryDto.setMeetType(savedCategory.getMeetType().name());  // Enum -> String으로 변환

        return savedCategoryDto;
    }
}
