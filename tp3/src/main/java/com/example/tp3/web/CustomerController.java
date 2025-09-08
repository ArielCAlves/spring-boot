package com.example.tp3.web;

import com.example.tp3.domain.Customer;
import com.example.tp3.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) { this.service = service; }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer c) {
        Customer saved = service.create(c);
        return ResponseEntity.created(URI.create("/api/customers/" + saved.getId())).body(saved);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> list() { return ResponseEntity.ok(service.list()); }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        Customer c = service.get(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody Customer c) {
        Customer up = service.update(id, c);
        return up == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(up);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}