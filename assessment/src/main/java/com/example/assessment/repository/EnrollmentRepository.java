package com.example.assessment.repository;

import com.example.assessment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByDisciplineIdAndGradeGreaterThanEqual(Long disciplineId, Double min);
    List<Enrollment> findByDisciplineIdAndGradeLessThan(Long disciplineId, Double max);
    boolean existsByStudent_IdAndDiscipline_Id(Long studentId, Long disciplineId);
}