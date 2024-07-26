package com.lucas.gourmet_connect.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "tb_recipe_ingredient")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"recipe", "ingredient"})
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double quantity;

    @JsonProperty("ingredient")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Ingredient getIngredient() {
        return ingredient;
    }
}
