package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long> {
}
