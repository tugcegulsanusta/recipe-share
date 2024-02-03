package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository <Ingredient, Long> {
    Set<Ingredient> findByRecipe_Id(Long id);

}
