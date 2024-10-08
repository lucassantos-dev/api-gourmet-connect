package com.lucas.gourmet_connect.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "tb_recipe")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    @ElementCollection
    @CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "instruction")
    private List<String> instructions;
    private Integer prepTime;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private  Origin origin;
    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private  Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "tb_recipe_categories" ,
            joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="recipe_category")
    )
    private Set<RecipeCategory> categories = new HashSet<>();
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeIngredients> recipeIngredients;
//
}
