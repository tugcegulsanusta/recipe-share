package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.dto.UpdateRecipeRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Ingredient;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.entity.User;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.IngredientRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    IngredientRepository ingredientRepository;
    public Recipe add(CreateRecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        if(recipeRequest.getName().isEmpty()){
            throw new ApiRequestException("Name cannot be empty!");
        }else{
            recipe.setName(recipeRequest.getName());
            recipe.setExplanation(recipeRequest.getExplanation());
            //Category set
            Category category = categoryService.getById(recipeRequest.getCategoryId()).get();
            recipe.setCategory(category);
            recipe.setBase64img(recipeRequest.getBase64img());
            //Ingredient set
            Set<Ingredient> ingredients = new HashSet<>();
            for(String newIngredient : recipeRequest.getIngredients()){
                Ingredient savedIngredients = ingredientRepository.save(new Ingredient(null, newIngredient, recipe));
                ingredients.add(savedIngredients);
            }
            recipe.setIngredients(ingredients);
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
    public void update(UpdateRecipeRequest updateRecipeRequest) {
        Optional<Recipe> recipeRequest=recipeRepository.findById(updateRecipeRequest.getId());
        if(recipeRequest.isPresent()){
            Recipe recipe = new Recipe();
            recipe.setId(updateRecipeRequest.getId());
            recipe.setName(updateRecipeRequest.getName());
            recipe.setExplanation(updateRecipeRequest.getExplanation());
            recipe.setBase64img(updateRecipeRequest.getBase64img());
            Category category = categoryService.getById(updateRecipeRequest.getCategoryId()).get();
            User user = userService.getById(updateRecipeRequest.getUserId()).get();
            recipe.setCreatedBy(user.getUsername());
            recipe.setCategory(category);
            recipeRepository.save(recipe);
        }
    }

}
