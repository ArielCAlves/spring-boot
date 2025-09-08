package com.example.tp3.web;

import com.example.tp3.domain.Employee;
import com.example.tp3.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee e) {
        Employee saved = service.create(e);
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> list() { return ResponseEntity.ok(service.list()); }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        Employee e = service.get(id);
        return e == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee e) {
        Employee up = service.update(id, e);
        return up == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(up);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}