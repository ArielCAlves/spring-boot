package com.example.tp1.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MathService {
    public double add(List<Double> v) { return v.stream().mapToDouble(Double::doubleValue).sum(); }
    public double sub(double a, double b) { return a - b; }
    public double mul(List<Double> v) { return v.stream().reduce(1.0, (x,y)->x*y); }
    public double div(double a, double b) {
        if (b == 0.0) throw new IllegalArgumentException("Divisão por zero"); return a/b;
    }
    public double pow(double a, double b) { return Math.pow(a,b); }
}