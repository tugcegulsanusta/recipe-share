package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.dto.CreateRecipeRequest;
import com.pinsoft.internship.recipeshare.dto.RecipeRatingRequest;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.entity.RecipeRating;
import com.pinsoft.internship.recipeshare.repository.RecipeRepository;
import com.pinsoft.internship.recipeshare.service.RecipeRatingService;
import com.pinsoft.internship.recipeshare.service.RecipeService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RecipeRatingController {
    @Autowired
    RecipeRatingService recipeRatingService;

    @GetMapping("/reciperating/{id}")
    @PermitAll
    public List<RecipeRating> get(@PathVariable Long id){
        return recipeRatingService.getById(id);
    }
    @DeleteMapping("/reciperating/{id}")
    public void delete(@PathVariable Long id){recipeRatingService.delete(id);}

    @PostMapping("/reciperating")
    public RecipeRating add(@RequestBody RecipeRatingRequest recipeRatingRequest){
        return recipeRatingService.add(recipeRatingRequest);
    }

}
