package com.lucas.gourmet_connect.mapper;

import com.lucas.gourmet_connect.entities.Difficulty;
import com.lucas.gourmet_connect.dto.DifficultyDTO;

public class DifficultyMapper {
    public static DifficultyDTO toDTO(Difficulty difficulty) {
        DifficultyDTO dto = new DifficultyDTO();
        dto.setId(difficulty.getId());
        dto.setName(difficulty.getName());
        return  dto;
    }
}
