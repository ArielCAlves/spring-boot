package com.example.assessment.service;

import com.example.assessment.domain.Discipline;
import com.example.assessment.repository.DisciplineRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisciplineServiceTest {
    private final DisciplineRepository repo = mock(DisciplineRepository.class);
    private final DisciplineService service = new DisciplineService(repo);

    @Test
    void create() {
        Discipline d = new Discipline();
        when(repo.save(d)).thenReturn(d);
        assertEquals(d, service.create(d));
    }

    @Test
    void updateFound() {
        Discipline d = new Discipline(); d.setName("new");
        Discipline cur = new Discipline(); cur.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(cur));
        when(repo.save(cur)).thenReturn(cur);
        Discipline result = service.update(1L, d);
        assertEquals("new", result.getName());
    }

    @Test
    void updateNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertNull(service.update(1L, new Discipline()));
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
        Discipline d = new Discipline();
        when(repo.findById(1L)).thenReturn(Optional.of(d));
        assertEquals(d, service.get(1L));

        when(repo.findAll()).thenReturn(List.of(d));
        assertEquals(1, service.list().size());
    }
}