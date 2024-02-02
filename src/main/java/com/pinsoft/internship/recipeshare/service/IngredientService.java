package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.Ingredient;
import com.pinsoft.internship.recipeshare.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;
    public List<String> getAllIngredients(){
        return ingredientRepository.findAll().stream().map(Ingredient::getName).distinct().toList();
    }

}
