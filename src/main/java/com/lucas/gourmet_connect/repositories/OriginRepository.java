package com.lucas.gourmet_connect.repositories;


import com.lucas.gourmet_connect.entities.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OriginRepository extends JpaRepository<Origin, UUID> {
}
