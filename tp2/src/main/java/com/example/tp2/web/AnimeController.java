package com.example.tp2.web;

import com.example.tp2.application.dto.AnimeRequest;
import com.example.tp2.application.dto.AnimeResponse;
import com.example.tp2.domain.service.AnimeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/animes")
public class AnimeController {

    private final AnimeService service;

    public AnimeController(AnimeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<AnimeResponse> list(@PageableDefault(size = 20, sort = "title") Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    public AnimeResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<AnimeResponse> create(@Valid @RequestBody AnimeRequest request, UriComponentsBuilder uri) {
        AnimeResponse created = service.create(request);
        return ResponseEntity.created(uri.path("/api/v1/animes/{id}").buildAndExpand(created.id()).toUri()).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeResponse> update(@PathVariable Long id, @Valid @RequestBody AnimeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}