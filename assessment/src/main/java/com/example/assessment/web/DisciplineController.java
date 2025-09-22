package com.example.assessment.web;

import com.example.assessment.domain.Discipline;
import com.example.assessment.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {
    private final DisciplineService service;
    public DisciplineController(DisciplineService service) { this.service = service; }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Discipline> create(@Valid @RequestBody Discipline d) {
        var saved = service.create(d);
        return ResponseEntity.created(URI.create("/api/disciplines/" + saved.getId())).body(saved);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> list() {
        return ResponseEntity.ok(service.list());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Discipline> get(@PathVariable Long id) {
        var d = service.get(id);
        return d == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(d);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Discipline> update(@PathVariable Long id, @Valid @RequestBody Discipline d) {
        var up = service.update(id, d);
        return up == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(up);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}