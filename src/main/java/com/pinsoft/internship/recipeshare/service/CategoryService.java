package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.CreateCategoryRequest;
import com.pinsoft.internship.recipeshare.entity.Category;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.CategoryRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category add(CreateCategoryRequest categoryRequest) {
        if(!categoryRepository.findByName(categoryRequest.getName()).isEmpty()){
            throw new ApiRequestException("name is already exist!");
        }else{
            Category category = new Category();
            category.setName(categoryRequest.getName());
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
