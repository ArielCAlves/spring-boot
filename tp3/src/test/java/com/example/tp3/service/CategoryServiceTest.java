package com.example.tp3.service;

import com.example.tp3.domain.Category;
import com.example.tp3.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private CategoryRepository repo;
    private CategoryService service;

    @BeforeEach
    void setup() {
        repo = mock(CategoryRepository.class);
        service = new CategoryService(repo);
    }

    private Category build() {
        Category c = new Category();
        c.setId(1L);
        c.setName("Consoles");
        c.setDescription("Retro");
        return c;
    }

    @Test
    void create_list_get_update_delete() {
        Category c = build();
        when(repo.save(any(Category.class))).thenReturn(c);
        when(repo.findAll()).thenReturn(List.of(c));
        when(repo.findById(1L)).thenReturn(Optional.of(c));
        when(repo.existsById(1L)).thenReturn(true);

        Category saved = service.create(c);
        assertNotNull(saved);

        assertEquals(1, service.list().size());

        assertNotNull(service.get(1L));

        Category upd = build();
        upd.setName("Consoles Clássicos");
        when(repo.save(any(Category.class))).thenAnswer(inv -> inv.getArgument(0));
        assertEquals("Consoles Clássicos", service.update(1L, upd).getName());

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