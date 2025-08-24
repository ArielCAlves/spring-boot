package com.example.tp2.domain.service;

import com.example.tp2.application.dto.AnimeRequest;
import com.example.tp2.application.dto.AnimeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimeService {
    Page<AnimeResponse> list(Pageable pageable);
    AnimeResponse getById(Long id);
    AnimeResponse create(AnimeRequest request);
    AnimeResponse update(Long id, AnimeRequest request);
    void delete(Long id);
}