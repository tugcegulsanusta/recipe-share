package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.dto.CreateIngredientRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.service.IngredientsService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
@RestController
public class IngredientsController {

    @Autowired
    IngredientsService ingredientsService;
    @GetMapping("/ingredients")
    @PermitAll
    public Collection<Ingredients> get(){return ingredientsService.getAll();}
    @GetMapping("/ingredients/{id}")
    @PermitAll
    public Ingredients get(@PathVariable Long id){
        Optional<Ingredients> optional = ingredientsService.getById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/ingredients/{id}")

    public void delete(@PathVariable Long id){ingredientsService.delete(id);}
    @PostMapping("/ingredients")
    public Ingredients add(@RequestBody CreateIngredientRequest ingredientRequest){
        return ingredientsService.add(ingredientRequest);
    }
}
