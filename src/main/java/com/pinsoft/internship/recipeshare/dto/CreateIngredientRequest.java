package com.pinsoft.internship.recipeshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngredientRequest {
    private String name;
    private Long recipeId;
}
