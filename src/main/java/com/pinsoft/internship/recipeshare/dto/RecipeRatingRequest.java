package com.pinsoft.internship.recipeshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRatingRequest {
    private String comment;
    private String base64img;
    private int rating;
    private Long userId;
    private Long recipeId;
}
