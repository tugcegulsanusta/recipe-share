package com.pinsoft.internship.recipeshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe_rating")
public class RecipeRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private String base64img;
    private int rating;
    private String createdBy;
    @ManyToOne
    @JoinColumn(name = "user_account_id",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "recipe_table_id")
    private Recipe recipe;

}
