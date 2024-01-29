package com.pinsoft.internship.recipeshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {
    private String name;
    private String explanation;
    private Long categoryId;
}
