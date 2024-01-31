package com.pinsoft.internship.recipeshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequest {
    private Long id;
    private String name;
    private String explanation;
    private Long categoryId;
    private String base64img;
    private String ingredients;
    private Long userId;
}
