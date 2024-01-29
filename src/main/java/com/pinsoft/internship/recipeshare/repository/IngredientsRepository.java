package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.Ingredients;
import com.pinsoft.internship.recipeshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    List<Ingredients> findByName(String name);
}
