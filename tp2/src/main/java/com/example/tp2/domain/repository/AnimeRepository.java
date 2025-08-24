package com.example.tp2.domain.repository;

import com.example.tp2.domain.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    boolean existsByTitleIgnoreCase(String title);
}