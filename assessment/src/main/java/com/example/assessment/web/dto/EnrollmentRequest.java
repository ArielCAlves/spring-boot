package com.example.assessment.web.dto;

public class EnrollmentRequest {
    private Long studentId;
    private Long disciplineId;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getDisciplineId() { return disciplineId; }
    public void setDisciplineId(Long disciplineId) { this.disciplineId = disciplineId; }
}