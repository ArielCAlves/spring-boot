package com.example.tp2.application.dto;

import com.example.tp2.domain.model.Genre;
import com.example.tp2.domain.model.WatchStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public record AnimeRequest(
        @NotBlank String title,
        @NotNull WatchStatus status,
        @Size(max = 8) Set<Genre> genres,
        @Min(0) Integer episodesWatched,
        @Min(0) @Max(10) Integer score,
        LocalDate startedAt,
        LocalDate finishedAt,
        @Size(max = 1000) String synopsis
) {}