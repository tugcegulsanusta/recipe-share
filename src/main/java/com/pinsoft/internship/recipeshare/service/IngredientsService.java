package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import com.pinsoft.internship.recipeshare.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {
    @Autowired
    IngredientsRepository ingredientsRepository;
    public Ingredients add(Ingredients ingredients) {return ingredientsRepository.save(ingredients);}
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

