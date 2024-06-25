package com.lucas.gourmet_connect.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "tb_recipe")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String instructions;
    private Integer prepTime;
    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private  Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "tb_recipe_categories" ,
            joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="recipe_category")
    )
    private Set<RecipeCategory> categories = new HashSet<>();

}
