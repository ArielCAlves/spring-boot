package com.example.tp3.service;

import com.example.tp3.domain.Supplier;
import com.example.tp3.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierService {
    private final SupplierRepository repo;
    public SupplierService(SupplierRepository repo) { this.repo = repo; }

    public Supplier create(Supplier s) { return repo.save(s); }

    public Supplier update(Long id, Supplier s) {
        Supplier cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(s.getName());
        cur.setContactEmail(s.getContactEmail());
        cur.setPhone(s.getPhone());
        cur.setCountry(s.getCountry());
        return repo.save(cur);
    }

    @Transactional(readOnly = true)
    public Supplier get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Supplier> list() { return repo.findAll(); }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}