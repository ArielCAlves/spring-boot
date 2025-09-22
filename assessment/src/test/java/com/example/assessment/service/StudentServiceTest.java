package com.example.assessment.service;

import com.example.assessment.domain.Student;
import com.example.assessment.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    private final StudentRepository repo = mock(StudentRepository.class);
    private final StudentService service = new StudentService(repo);

    @Test
    void create() {
        Student s = new Student();
        when(repo.save(s)).thenReturn(s);
        assertEquals(s, service.create(s));
    }

    @Test
    void updateFound() {
        Student s = new Student(); s.setName("new");
        Student cur = new Student(); cur.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(cur));
        when(repo.save(cur)).thenReturn(cur);
        Student result = service.update(1L, s);
        assertNotNull(result);
        assertEquals("new", result.getName());
    }

    @Test
    void updateNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertNull(service.update(1L, new Student()));
    }

    @Test
    void deleteFound() {
        when(repo.existsById(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
        verify(repo).deleteById(1L);
    }

    @Test
    void deleteNotFound() {
        when(repo.existsById(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }

    @Test
    void getAndList() {
        Student s = new Student();
        when(repo.findById(1L)).thenReturn(Optional.of(s));
        assertEquals(s, service.get(1L));

        when(repo.findAll()).thenReturn(List.of(s));
        assertEquals(1, service.list().size());
    }
}