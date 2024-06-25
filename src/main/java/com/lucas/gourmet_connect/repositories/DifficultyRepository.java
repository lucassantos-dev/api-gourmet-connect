package com.lucas.gourmet_connect.repositories;

import com.lucas.gourmet_connect.domain.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
}
