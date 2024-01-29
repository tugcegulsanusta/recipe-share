package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateIngredientRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import com.pinsoft.internship.recipeshare.repository.IngredientsRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import liquibase.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {
    @Autowired
    IngredientsRepository ingredientsRepository;
    @Autowired
    RecipeService recipeService;
    public void add(CreateIngredientRequest ingredientRequest) {
        Ingredients ingredients = new Ingredients();
        if(ingredientRequest.getName().isEmpty()){
            throw new ApiRequestException("Name cannot be empty!");
        }else if(!ingredientsRepository.findByName(ingredientRequest.getName()).isEmpty()){
            throw new ApiRequestException("Ingredient name is already exist! Please check again!");
        }else {
            ingredients.setName(ingredientRequest.getName());
            Recipe recipe = recipeService.getById(ingredientRequest.getRecipe_table_id()).get();
            ingredients.setRecipe(recipe);
            ingredientsRepository.save(ingredients);
        }

    }
    public void delete(Long id) {
        if(ingredientsRepository.findById(id).isEmpty()){
            throw new ApiRequestException("Ingredient id is not found!");
        }else{
            ingredientsRepository.deleteById(id);
        }
    }
    public List<Ingredients> getAll(){
        return ingredientsRepository.findAll();
    }
    public Optional<Ingredients> getById(Long id){
        if(ingredientsRepository.findById(id).isEmpty()){
            throw new ApiRequestException("The given id is not existed");
        }else{
            return ingredientsRepository.findById(id);
        }
    }
}

