package com.example.assessment.web;

import com.example.assessment.domain.Enrollment;
import com.example.assessment.service.EnrollmentService;
import com.example.assessment.web.dto.EnrollmentRequest;
import com.example.assessment.web.dto.EnrollmentSummary;
import com.example.assessment.web.dto.GradeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestBody EnrollmentRequest req) {
        Enrollment e = service.enroll(req.getStudentId(), req.getDisciplineId());
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.created(URI.create("/api/enrollments/" + e.getId())).body(e);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<EnrollmentSummary> updateGrade(@PathVariable Long id, @RequestBody GradeRequest req) {
        Enrollment e = service.grade(id, req.getGrade());
        if (e == null) return ResponseEntity.notFound().build();
        EnrollmentSummary dto = new EnrollmentSummary(e.getId(), e.getStudent().getId(), e.getDiscipline().getId(), e.getGrade());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentSummary>> list() {
        var data = service.listAll().stream()
                .map(e -> new EnrollmentSummary(e.getId(), e.getStudent().getId(), e.getDiscipline().getId(), e.getGrade()))
                .toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentSummary> get(@PathVariable Long id) {
        var e = service.getById(id);
        if (e == null) return ResponseEntity.notFound().build();
        var dto = new EnrollmentSummary(e.getId(), e.getStudent().getId(), e.getDiscipline().getId(), e.getGrade());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/discipline/{disciplineId}/approved")
    public ResponseEntity<List<EnrollmentSummary>> approved(@PathVariable Long disciplineId) {
        var data = service.approved(disciplineId).stream()
                .map(e -> new EnrollmentSummary(e.getId(), e.getStudent().getId(), e.getDiscipline().getId(), e.getGrade()))
                .toList();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/discipline/{disciplineId}/reproved")
    public ResponseEntity<List<EnrollmentSummary>> reproved(@PathVariable Long disciplineId) {
        var data = service.reproved(disciplineId).stream()
                .map(e -> new EnrollmentSummary(e.getId(), e.getStudent().getId(), e.getDiscipline().getId(), e.getGrade()))
                .toList();
        return ResponseEntity.ok(data);
    }
}