package com.example.tp3.service;

import com.example.tp3.domain.Category;
import com.example.tp3.domain.Product;
import com.example.tp3.repository.CategoryRepository;
import com.example.tp3.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepo;
    private CategoryRepository categoryRepo;
    private ProductService service;

    @BeforeEach
    void setup() {
        productRepo = mock(ProductRepository.class);
        categoryRepo = mock(CategoryRepository.class);
        service = new ProductService(productRepo, categoryRepo);
    }

    private Category cat() {
        Category c = new Category();
        c.setId(1L);
        c.setName("Acessórios");
        c.setDescription("Linha");
        return c;
    }

    private Product build(Long categoryId) {
        Product p = new Product();
        p.setId(1L);
        p.setName("Controle USB");
        p.setUnitPrice(new BigDecimal("99.90"));
        p.setStock(5);
        Category c = new Category();
        c.setId(categoryId);
        p.setCategory(c);
        return p;
    }

    @Test
    void create_list_get_update_delete_valid_category() {
        Category c = cat();
        Product p = build(c.getId());

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(c));
        when(productRepo.save(any(Product.class))).thenAnswer(inv -> {
            Product saved = inv.getArgument(0);
            saved.setId(1L);
            return saved;
        });
        when(productRepo.findAll()).thenReturn(List.of(p));
        when(productRepo.findById(1L)).thenReturn(Optional.of(p));
        when(productRepo.existsById(1L)).thenReturn(true);

        Product saved = service.create(p);
        assertNotNull(saved);
        assertNotNull(saved.getId());

        assertEquals(1, service.list().size());

        assertNotNull(service.get(1L));

        Product upd = build(1L);
        upd.setName("Controle USB Pro");
        when(productRepo.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(c));
        assertEquals("Controle USB Pro", service.update(1L, upd).getName());

        assertTrue(service.delete(1L));
    }

    @Test
    void invalid_paths() {
        when(categoryRepo.findById(999L)).thenReturn(Optional.empty());
        Product p = build(999L);
        assertNull(service.create(p));

        when(productRepo.findById(999L)).thenReturn(Optional.empty());
        Product upd = build(1L);
        assertNull(service.update(999L, upd));

        Category c = cat();
        when(categoryRepo.findById(123L)).thenReturn(Optional.empty());
        Product existing = build(c.getId());
        when(productRepo.findById(1L)).thenReturn(Optional.of(existing));
        assertNull(service.update(1L, build(123L)));

        when(productRepo.existsById(999L)).thenReturn(false);
        assertFalse(service.delete(999L));
    }
}