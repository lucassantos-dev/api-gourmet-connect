package com.lucas.gourmet_connect.domain.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDTO {
    private UUID id;
    private String name;
    private String description;
    private String unit;
    private String imageUrl;
}
