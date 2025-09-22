package com.example.assessment.service;

import com.example.assessment.domain.Discipline;
import com.example.assessment.repository.DisciplineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DisciplineService {
    private final DisciplineRepository repo;
    public DisciplineService(DisciplineRepository repo) { this.repo = repo; }

    public Discipline create(Discipline d) { return repo.save(d); }

    public Discipline update(Long id, Discipline d) {
        var cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(d.getName());
        cur.setCode(d.getCode());
        return repo.save(cur);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public Discipline get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Discipline> list() { return repo.findAll(); }
}