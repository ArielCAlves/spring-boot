package com.example.tp2.application.mapper;

import com.example.tp2.application.dto.AnimeRequest;
import com.example.tp2.application.dto.AnimeResponse;
import com.example.tp2.domain.model.Anime;

public class AnimeMapper {

    public static Anime toEntity(AnimeRequest req) {
        Anime a = new Anime();
        a.setTitle(req.title());
        if (req.status() != null) a.setStatus(req.status());
        if (req.genres() != null) a.setGenres(req.genres());
        a.setEpisodesWatched(req.episodesWatched());
        a.setScore(req.score());
        a.setStartedAt(req.startedAt());
        a.setFinishedAt(req.finishedAt());
        a.setSynopsis(req.synopsis());
        return a;
    }

    public static AnimeResponse toResponse(Anime e) {
        return new AnimeResponse(
                e.getId(),
                e.getTitle(),
                e.getStatus(),
                e.getGenres(),
                e.getEpisodesWatched(),
                e.getScore(),
                e.getStartedAt(),
                e.getFinishedAt(),
                e.getSynopsis()
        );
    }

    public static void updateEntityFromRequest(AnimeRequest req, Anime e) {
        if (req.title() != null) e.setTitle(req.title());
        if (req.status() != null) e.setStatus(req.status());
        if (req.genres() != null) e.setGenres(req.genres());
        if (req.episodesWatched() != null) e.setEpisodesWatched(req.episodesWatched());
        if (req.score() != null) e.setScore(req.score());
        if (req.startedAt() != null) e.setStartedAt(req.startedAt());
        if (req.finishedAt() != null) e.setFinishedAt(req.finishedAt());
        if (req.synopsis() != null) e.setSynopsis(req.synopsis());
    }
}