package com.example.tp3.service;

import com.example.tp3.domain.Customer;
import com.example.tp3.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository repo;
    private CustomerService service;

    @BeforeEach
    void setup() {
        repo = mock(CustomerRepository.class);
        service = new CustomerService(repo);
    }

    private Customer build() {
        Customer c = new Customer();
        c.setId(1L);
        c.setName("João");
        c.setEmail("joao@gmail.com");
        c.setBirthDate(LocalDate.of(1990, 8, 15));
        c.setLoyaltyPoints(100);
        return c;
    }

    @Test
    void create_list_get_update_delete() {
        Customer c = build();
        when(repo.save(any(Customer.class))).thenReturn(c);
        when(repo.findAll()).thenReturn(List.of(c));
        when(repo.findById(1L)).thenReturn(Optional.of(c));
        when(repo.existsById(1L)).thenReturn(true);

        Customer saved = service.create(c);
        assertNotNull(saved);

        assertEquals(1, service.list().size());

        assertNotNull(service.get(1L));

        Customer upd = build();
        upd.setName("João Teste.");
        when(repo.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));
        assertEquals("João Teste.", service.update(1L, upd).getName());

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