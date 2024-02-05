package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.dto.UpdateRecipeRequest;
import com.pinsoft.internship.recipeshare.entity.*;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.IngredientRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRatingRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Transactional
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
    @Autowired
    RecipeRatingRepository recipeRatingRepository;

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
            recipe.setCreatedBy(recipeRequest.getCreatedBy());
            recipe = recipeRepository.save(recipe);
            //Ingredient set
            Set<Ingredient> ingredients = new HashSet<>();
            for(String ingredientName : recipeRequest.getIngredients()){
                Ingredient ingredient = new Ingredient(null, ingredientName, recipe);
                Ingredient savedIngredients = ingredientRepository.save(ingredient);
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
            ingredientRepository.deleteAllByRecipe_Id(id);
            recipeRatingRepository.deleteAllByRecipe_Id(id);
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
            System.out.println(recipeRepository.findById(id).get().getRecipeRating().size());
            return recipeRepository.findById(id);
        }
    }
    public void update(UpdateRecipeRequest updateRecipeRequest) {
        Optional<Recipe> recipeRequest=recipeRepository.findById(updateRecipeRequest.getId());
        if(recipeRequest.isPresent()){
            Recipe recipe = recipeRepository.getById(updateRecipeRequest.getId());
            recipe.setName(updateRecipeRequest.getName());
            recipe.setExplanation(updateRecipeRequest.getExplanation());
            recipe.setBase64img(updateRecipeRequest.getBase64img());
            Category category = categoryService.getById(updateRecipeRequest.getCategoryId()).get();
            User user = userService.getById(updateRecipeRequest.getUserId()).get();
            recipe.setCreatedBy(user.getUsername());
            recipe.setCategory(category);
            ingredientRepository.deleteAllByRecipe_Id(recipe.getId());
            Set<Ingredient> ingredients = new HashSet<>();
            for(String ingredientName : updateRecipeRequest.getIngredients()){
                Ingredient ingredient = new Ingredient(null, ingredientName, recipe);
                Ingredient savedIngredients = ingredientRepository.save(ingredient);
                ingredients.add(savedIngredients);
            }
            recipe.setIngredients(ingredients);
            recipeRepository.save(recipe);

        }else{
            throw new ApiRequestException("The given id is not exist!");
        }
    }

}
