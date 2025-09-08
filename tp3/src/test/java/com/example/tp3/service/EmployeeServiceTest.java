package com.example.tp3.service;

import com.example.tp3.domain.Employee;
import com.example.tp3.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository repo;
    private EmployeeService service;

    @BeforeEach
    void setup() {
        repo = mock(EmployeeRepository.class);
        service = new EmployeeService(repo);
    }

    private Employee build() {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Maria");
        e.setRole("Marketing");
        e.setEmail("maria@gmail.com");
        e.setHireDate(LocalDate.now());
        e.setSalary(new BigDecimal("2500.00"));
        return e;
    }

    @Test
    void create_list_get_update_delete() {
        Employee e = build();
        when(repo.save(any(Employee.class))).thenReturn(e);
        when(repo.findAll()).thenReturn(List.of(e));
        when(repo.findById(1L)).thenReturn(Optional.of(e));
        when(repo.existsById(1L)).thenReturn(true);

        Employee saved = service.create(e);
        assertNotNull(saved);

        assertEquals(1, service.list().size());

        assertNotNull(service.get(1L));

        Employee update = build();
        update.setName("Maria S.");
        when(repo.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));
        assertEquals("Maria S.", service.update(1L, update).getName());

        assertTrue(service.delete(1L));
    }

    @Test
    void not_found_paths() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        when(repo.existsById(99L)).thenReturn(false);

        assertNull(service.get(99L));
        assertNull(service.update(99L, build()));
        assertFalse(service.delete(99L));
    }
}