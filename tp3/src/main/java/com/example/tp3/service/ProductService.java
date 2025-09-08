package com.example.tp3.service;

import com.example.tp3.domain.Category;
import com.example.tp3.domain.Product;
import com.example.tp3.repository.CategoryRepository;
import com.example.tp3.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    public Product create(Product p) {
        if (p.getCategory() == null || p.getCategory().getId() == null) return null;
        Category c = categoryRepo.findById(p.getCategory().getId()).orElse(null);
        if (c == null) return null;
        p.setCategory(c);
        return repo.save(p);
    }

    public Product update(Long id, Product p) {
        Product cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        if (p.getCategory() == null || p.getCategory().getId() == null) return null;
        Category c = categoryRepo.findById(p.getCategory().getId()).orElse(null);
        if (c == null) return null;
        cur.setName(p.getName());
        cur.setUnitPrice(p.getUnitPrice());
        cur.setStock(p.getStock());
        cur.setCategory(c);
        return repo.save(cur);
    }

    @Transactional(readOnly = true)
    public Product get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Product> list() { return repo.findAll(); }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}