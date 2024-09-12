package com.example.studywithme.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String categoryId;
    private Boolean language;
    private Boolean certification;
    private Boolean major;
    private Boolean career;
    private Boolean exam;
    private Boolean hobbies;
    private Boolean programming;
    private Boolean selfDirected;
    private Boolean etc;
    private String meetType;
}
