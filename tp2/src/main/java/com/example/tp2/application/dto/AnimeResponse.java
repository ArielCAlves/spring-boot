package com.example.tp2.application.dto;

import com.example.tp2.domain.model.Genre;
import com.example.tp2.domain.model.WatchStatus;
import java.time.LocalDate;
import java.util.Set;

public record AnimeResponse(
        Long id,
        String title,
        WatchStatus status,
        Set<Genre> genres,
        Integer episodesWatched,
        Integer score,
        LocalDate startedAt,
        LocalDate finishedAt,
        String synopsis
) {}