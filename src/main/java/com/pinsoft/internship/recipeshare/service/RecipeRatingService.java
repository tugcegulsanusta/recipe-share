package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.RecipeRatingRequest;
import com.pinsoft.internship.recipeshare.entity.Recipe;
import com.pinsoft.internship.recipeshare.entity.RecipeRating;
import com.pinsoft.internship.recipeshare.entity.User;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.RecipeRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeRatingService {
    @Autowired
    RecipeRatingRepository recipeRatingRepository;
    @Autowired
    UserService userService;
    @Autowired
    RecipeService recipeService;

    public RecipeRating add(RecipeRatingRequest recipeRatingRequest) {
        RecipeRating recipeRating = new RecipeRating();
        recipeRating.setComment(recipeRatingRequest.getComment());
        recipeRating.setRating(recipeRatingRequest.getRating());
        recipeRating.setBase64img(recipeRatingRequest.getBase64img());
        Recipe recipe = recipeService.getById(recipeRatingRequest.getRecipeId()).get();
        recipeRating.setRecipe(recipe);
        User user = userService.getById(recipeRatingRequest.getUserId()).get();
        recipeRating.setUser(user);
        recipeRating.setCreatedBy(user.getUsername());
        return recipeRatingRepository.save(recipeRating);
    }
    public void delete(Long id) {
        if(recipeRatingRepository.findById(id).isEmpty()){
            throw new ApiRequestException("RecipeRatingId is not found");
        }else{
            recipeRatingRepository.deleteById(id);
        }
    }
   public List<RecipeRating> getById(Long id) {

       return  recipeRatingRepository.findAllByRecipeId(id);
   }
}
