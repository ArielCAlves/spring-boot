package com.example.tp3.service;

import com.example.tp3.domain.Customer;
import com.example.tp3.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo) { this.repo = repo; }

    public Customer create(Customer c) { return repo.save(c); }

    public Customer update(Long id, Customer c) {
        Customer cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(c.getName());
        cur.setEmail(c.getEmail());
        cur.setBirthDate(c.getBirthDate());
        cur.setLoyaltyPoints(c.getLoyaltyPoints());
        return repo.save(cur);
    }

    @Transactional(readOnly = true)
    public Customer get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Customer> list() { return repo.findAll(); }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}