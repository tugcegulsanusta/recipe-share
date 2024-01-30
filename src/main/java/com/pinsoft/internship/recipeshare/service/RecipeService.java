package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    CategoryService categoryService;
    public Recipe add(CreateRecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        if(recipeRequest.getName().isEmpty()){
            throw new ApiRequestException("Name cannot be empty!");
        }else{
            recipe.setName(recipeRequest.getName());
            recipe.setExplanation(recipeRequest.getExplanation());
            Category category = categoryService.getById(recipeRequest.getCategoryId()).get();
            recipe.setCategory(category);
            return recipeRepository.save(recipe);
        }

    }
    public void delete(Long id) {
        if(recipeRepository.findById(id).isEmpty()){
            throw new ApiRequestException("Recipe id is not found!");
        }else{
            recipeRepository.deleteById(id);
        }
    }
    public List<Recipe> getAll(){
        return recipeRepository.findAll();
    }
    public Optional<Recipe> getById(Long id){
        if(recipeRepository.findById(id).isEmpty()){
            throw new ApiRequestException("The given id is not exist!");
        }else{
            return recipeRepository.findById(id);
        }
    }
}
