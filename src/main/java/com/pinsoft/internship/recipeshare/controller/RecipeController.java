package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.service.IngredientsService;
import com.pinsoft.internship.recipeshare.service.RecipeService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @GetMapping("/recipe")
    @PermitAll
    public Collection<Recipe> get(){return recipeService.getAll();}
    @GetMapping("/recipe/{id}")
    @PermitAll
    public Recipe get(@PathVariable Long id){
        Optional<Recipe> optional = recipeService.getById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/recipe/{id}")

    public void delete(@PathVariable Long id){recipeService.delete(id);}
    @PostMapping("/recipe")
    public Recipe add(@RequestBody Recipe recipe){
        return recipeService.add(recipe);
    }
}
