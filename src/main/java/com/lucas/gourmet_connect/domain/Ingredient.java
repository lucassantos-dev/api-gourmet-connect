package com.lucas.gourmet_connect.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "tb_ingredient")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
