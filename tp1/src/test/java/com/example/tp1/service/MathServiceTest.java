package com.example.tp1.service;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class MathServiceTest {

    MathService service = new MathService();

    @Test
    void add() {
        assertEquals(6.0, service.add(Arrays.asList(1.0, 2.0, 3.0)));
    }

    @Test
    void sub() {
        assertEquals(5.0, service.sub(10.0, 5.0));
    }

    @Test
    void mul() {
        assertEquals(24.0, service.mul(Arrays.asList(2.0, 3.0, 4.0)));
    }

    @Test
    void div() {
        assertEquals(2.0, service.div(10.0, 5.0));
        assertThrows(IllegalArgumentException.class, () -> service.div(10.0, 0.0));
    }

    @Test
    void pow() {
        assertEquals(8.0, service.pow(2.0, 3.0));
    }
}