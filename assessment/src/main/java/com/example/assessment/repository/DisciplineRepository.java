package com.example.assessment.repository;

import com.example.assessment.domain.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {}