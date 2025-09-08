package com.example.tp3.web;

import com.example.tp3.domain.Supplier;
import com.example.tp3.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService service;
    public SupplierController(SupplierService service) { this.service = service; }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Supplier> create(@Valid @RequestBody Supplier s) {
        Supplier saved = service.create(s);
        return ResponseEntity.created(URI.create("/api/suppliers/" + saved.getId())).body(saved);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Supplier>> list() { return ResponseEntity.ok(service.list()); }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Supplier> get(@PathVariable Long id) {
        Supplier s = service.get(id);
        return s == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Supplier> update(@PathVariable Long id, @Valid @RequestBody Supplier s) {
        Supplier up = service.update(id, s);
        return up == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(up);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}