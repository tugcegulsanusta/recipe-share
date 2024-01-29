package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category add(Category category) {
        if(categoryRepository.findById(category.getId()).isPresent()){
            throw new ApiRequestException("The given id is already existed!");
        }else{
            return categoryRepository.save(category);
        }
    }
    public void delete(Long id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new ApiRequestException("Category id is not found!");
        }else{
            categoryRepository.deleteById(id);
        }
    }
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> getById(Long id){
        if(categoryRepository.findById(id).isEmpty()){
            throw new ApiRequestException("The given id is not existed");
        }else{
            return categoryRepository.findById(id);
        }

    }
}
