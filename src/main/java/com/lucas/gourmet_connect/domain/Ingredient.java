package com.lucas.gourmet_connect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "tb_ingredient")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private  Origin origin;
    @ManyToMany
    @JoinTable(name = "tb_ingredient_categories" ,
            joinColumns = @JoinColumn(name="ingredient_id"),
            inverseJoinColumns = @JoinColumn(name="ingredient_category")
    )
    private Set<IngredientCategory> categories = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "tb_ingredient_substitutes",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "substitute_id")
    )
    private Set<Ingredient> substitutes = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "recipe")
    private Set<Recipe> recipes = new HashSet<>();
}
