package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {


  @Autowired
  RecipeRepository recipeRepository;


  public Recipe create(CreateRecipeRequest recipeRequest) {
    Recipe recipe = new Recipe();
    recipe.setName(recipeRequest.getName());
    recipe.setRecipeName(recipeRequest.getRecipeName());
    recipe.setCategory(recipeRequest.getCategory());

    return recipeRepository.save(recipe);
  }

  public List<Recipe> getAllRecipe() {
    return recipeRepository.findAll();
  }

}
