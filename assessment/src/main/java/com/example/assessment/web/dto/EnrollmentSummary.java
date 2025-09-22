package com.example.assessment.web.dto;

public class EnrollmentSummary {
    private Long id;
    private Long studentId;
    private Long disciplineId;
    private Double grade;

    public EnrollmentSummary() {}

    public EnrollmentSummary(Long id, Long studentId, Long disciplineId, Double grade) {
        this.id = id;
        this.studentId = studentId;
        this.disciplineId = disciplineId;
        this.grade = grade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getDisciplineId() { return disciplineId; }
    public void setDisciplineId(Long disciplineId) { this.disciplineId = disciplineId; }
    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }
}