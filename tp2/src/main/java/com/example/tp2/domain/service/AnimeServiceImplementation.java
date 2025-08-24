package com.example.tp2.domain.service;

import com.example.tp2.application.dto.AnimeRequest;
import com.example.tp2.application.dto.AnimeResponse;
import com.example.tp2.application.mapper.AnimeMapper;
import com.example.tp2.domain.model.Anime;
import com.example.tp2.domain.repository.AnimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnimeServiceImplementation implements AnimeService {

    private final AnimeRepository repo;

    public AnimeServiceImplementation(AnimeRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnimeResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(AnimeMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public AnimeResponse getById(Long id) {
        Anime anime = repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Anime id " + id + " não encontrado"));
        return AnimeMapper.toResponse(anime);
    }

    @Override
    public AnimeResponse create(AnimeRequest request) {
        if (repo.existsByTitleIgnoreCase(request.title())) {
            throw new IllegalArgumentException("Já existe anime com este título");
        }
        Anime saved = repo.save(AnimeMapper.toEntity(request));
        return AnimeMapper.toResponse(saved);
    }

    @Override
    public AnimeResponse update(Long id, AnimeRequest request) {
        Anime entity = repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Anime id " + id + " não encontrado"));
        AnimeMapper.updateEntityFromRequest(request, entity);
        return AnimeMapper.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Anime id " + id + " não encontrado");
        }
        repo.deleteById(id);
    }
}