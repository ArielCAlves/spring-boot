package com.example.tp3.service;

import com.example.tp3.domain.Employee;
import com.example.tp3.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public Employee create(Employee e) { return repo.save(e); }

    public Employee update(Long id, Employee e) {
        Employee cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(e.getName());
        cur.setRole(e.getRole());
        cur.setEmail(e.getEmail());
        cur.setHireDate(e.getHireDate());
        cur.setSalary(e.getSalary());
        return repo.save(cur);
    }

    @Transactional(readOnly = true)
    public Employee get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Employee> list() { return repo.findAll(); }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}