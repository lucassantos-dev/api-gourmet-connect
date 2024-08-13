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

    public static Origin toEntity(OriginDTO originDTO) {
        Origin origin = new Origin();
        origin.setId(originDTO.getId());
        origin.setName(originDTO.getName());
        return origin;
        }
}
