package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}
