package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateIngredientRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import com.pinsoft.internship.recipeshare.repository.IngredientsRepository;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
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
    public Ingredients add(CreateIngredientRequest ingredientRequest) {
        Ingredients ingredients = new Ingredients();
        ingredients.setName(ingredientRequest.getName());
        Recipe recipe = recipeService.getById(ingredientRequest.getRecipeId()).get();
        ingredients.setRecipe(recipe);
        return ingredientsRepository.save(ingredients);
    }
    public void delete(Long id) {
        ingredientsRepository.deleteById(id);
    }
    public List<Ingredients> getAll(){
        return ingredientsRepository.findAll();
    }
    public Optional<Ingredients> getById(Long id){
        return ingredientsRepository.findById(id);
    }
}

