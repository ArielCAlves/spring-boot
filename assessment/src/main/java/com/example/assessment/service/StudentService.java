package com.example.assessment.service;

import com.example.assessment.domain.Student;
import com.example.assessment.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }

    public Student create(Student s) { return repo.save(s); }

    public Student update(Long id, Student s) {
        var cur = repo.findById(id).orElse(null);
        if (cur == null) return null;
        cur.setName(s.getName());
        cur.setCpf(s.getCpf());
        cur.setEmail(s.getEmail());
        cur.setPhone(s.getPhone());
        cur.setAddress(s.getAddress());
        return repo.save(cur);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public Student get(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Student> list() { return repo.findAll(); }
}