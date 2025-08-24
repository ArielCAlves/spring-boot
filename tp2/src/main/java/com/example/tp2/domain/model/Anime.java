package com.example.tp2.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animes")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WatchStatus status = WatchStatus.PLANNED;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "anime_genres", joinColumns = @JoinColumn(name = "anime_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Set<Genre> genres = new HashSet<>();

    @Min(0)
    private Integer episodesWatched;

    @Min(0)
    @Max(10)
    private Integer score;

    private LocalDate startedAt;
    private LocalDate finishedAt;

    @Column(length = 1000)
    private String synopsis;

    public Anime() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public WatchStatus getStatus() { return status; }
    public void setStatus(WatchStatus status) { this.status = status; }
    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }
    public Integer getEpisodesWatched() { return episodesWatched; }
    public void setEpisodesWatched(Integer episodesWatched) { this.episodesWatched = episodesWatched; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public LocalDate getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDate startedAt) { this.startedAt = startedAt; }
    public LocalDate getFinishedAt() { return finishedAt; }
    public void setFinishedAt(LocalDate finishedAt) { this.finishedAt = finishedAt; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
}