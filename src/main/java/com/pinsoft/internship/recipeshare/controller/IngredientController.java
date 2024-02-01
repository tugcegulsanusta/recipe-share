package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.dto.UpdateRecipeRequest;
import com.pinsoft.internship.recipeshare.entity.Ingredient;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.service.IngredientService;
import com.pinsoft.internship.recipeshare.service.RecipeService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;
    @GetMapping("/ingredients")
    @PermitAll
    public Collection<String> get(){return ingredientService.getAllIngredients();}

}
