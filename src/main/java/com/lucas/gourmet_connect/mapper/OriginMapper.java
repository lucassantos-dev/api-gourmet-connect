package com.lucas.gourmet_connect.mapper;

import com.lucas.gourmet_connect.entities.Origin;
import com.lucas.gourmet_connect.dto.OriginDTO;

public class OriginMapper {

    public static OriginDTO toDTO(Origin origin) {
        OriginDTO dto = new OriginDTO();
        dto.setId(origin.getId());
        dto.setName(origin.getName());
        return  dto;
    }
}
