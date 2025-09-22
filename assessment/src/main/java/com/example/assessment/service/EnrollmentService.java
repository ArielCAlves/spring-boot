package com.example.assessment.service;

import com.example.assessment.domain.Discipline;
import com.example.assessment.domain.Enrollment;
import com.example.assessment.domain.Student;
import com.example.assessment.repository.DisciplineRepository;
import com.example.assessment.repository.EnrollmentRepository;
import com.example.assessment.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final StudentRepository studentRepo;
    private final DisciplineRepository disciplineRepo;

    public EnrollmentService(EnrollmentRepository repo, StudentRepository studentRepo, DisciplineRepository disciplineRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.disciplineRepo = disciplineRepo;
    }

    public Enrollment enroll(Long studentId, Long disciplineId) {
        Student st = studentRepo.findById(studentId).orElse(null);
        Discipline dc = disciplineRepo.findById(disciplineId).orElse(null);
        if (st == null || dc == null) return null;
        if (repo.existsByStudent_IdAndDiscipline_Id(studentId, disciplineId)) throw new IllegalStateException("duplicate");
        Enrollment e = new Enrollment();
        e.setStudent(st);
        e.setDiscipline(dc);
        e.setGrade(null);
        return repo.save(e);
    }

    public Enrollment grade(Long enrollmentId, Double grade) {
        var en = repo.findById(enrollmentId).orElse(null);
        if (en == null) return null;
        en.setGrade(grade);
        return repo.save(en);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> listAll() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public Enrollment getById(Long id) { return repo.findById(id).orElse(null); }

    @Transactional(readOnly = true)
    public List<Enrollment> approved(Long disciplineId) {
        return repo.findByDisciplineIdAndGradeGreaterThanEqual(disciplineId, 7.0);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> reproved(Long disciplineId) {
        return repo.findByDisciplineIdAndGradeLessThan(disciplineId, 7.0);
    }
}