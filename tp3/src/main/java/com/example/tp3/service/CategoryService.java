package com.example.tp3.service;

import com.example.tp3.domain.Category;
import com.example.tp3.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repo;
    public CategoryService(CategoryRepository repo) { this.repo = repo; }

    public Category create(Category c) { return repo.save(c); }

    public Category update(Long id, Category c) {
        Category cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(c.getName());
        cur.setDescription(c.getDescription());
        return repo.save(cur);
    }

    @Transactional(readOnly = true)
    public Category get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Category> list() { return repo.findAll(); }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}