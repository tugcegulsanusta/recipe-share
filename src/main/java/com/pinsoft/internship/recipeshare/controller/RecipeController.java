package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.service.RecipeService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;
  @GetMapping("/recipe")
  @PermitAll
  public List<Recipe> getAllOrders() {
    try {
      return recipeService.getAllRecipe();
    } catch (NoSuchElementException noSuchElementException){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }


}
