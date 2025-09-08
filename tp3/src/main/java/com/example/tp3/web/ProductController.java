package com.example.tp3.web;

import com.example.tp3.domain.Product;
import com.example.tp3.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@Valid @RequestBody Product p) {
        Product saved = service.create(p);
        return saved == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> list() { return ResponseEntity.ok(service.list()); }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product p = service.get(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product p) {
        Product up = service.update(id, p);
        return up == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(up);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}