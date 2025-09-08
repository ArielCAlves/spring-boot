package com.example.tp3.service;

import com.example.tp3.domain.Supplier;
import com.example.tp3.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceTest {

    private SupplierRepository repo;
    private SupplierService service;

    @BeforeEach
    void setup() {
        repo = mock(SupplierRepository.class);
        service = new SupplierService(repo);
    }

    private Supplier build() {
        Supplier s = new Supplier();
        s.setId(1L);
        s.setName("Fornecedor da Nintendo");
        s.setContactEmail("contato@nintendo.com");
        s.setPhone("21999999999");
        s.setCountry("JP");
        return s;
    }

    @Test
    void create_list_get_update_delete() {
        Supplier s = build();
        when(repo.save(any(Supplier.class))).thenReturn(s);
        when(repo.findAll()).thenReturn(List.of(s));
        when(repo.findById(1L)).thenReturn(Optional.of(s));
        when(repo.existsById(1L)).thenReturn(true);

        Supplier saved = service.create(s);
        assertNotNull(saved);

        assertEquals(1, service.list().size());

        assertNotNull(service.get(1L));

        Supplier upd = build();
        upd.setName("Fornecedor da Nintendo SA");
        when(repo.save(any(Supplier.class))).thenAnswer(inv -> inv.getArgument(0));
        assertEquals("Fornecedor da Nintendo SA", service.update(1L, upd).getName());

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