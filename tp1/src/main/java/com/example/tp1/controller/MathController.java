package com.example.tp1.controller;

import com.example.tp1.dto.OperationRequest;
import com.example.tp1.dto.OperationResponse;
import com.example.tp1.service.MathService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathService service;
    public MathController(MathService service) { this.service = service; }

    @RequestMapping(path="/add", method=RequestMethod.GET)
    public OperationResponse add(@RequestParam List<Double> a) {
        return new OperationResponse("add", service.add(a));
    }

    @RequestMapping(path="/sub", method=RequestMethod.GET)
    public OperationResponse sub(@RequestParam double a, @RequestParam double b) {
        return new OperationResponse("sub", service.sub(a,b));
    }

    @RequestMapping(path="/mul", method=RequestMethod.GET)
    public OperationResponse mul(@RequestParam List<Double> a) {
        return new OperationResponse("mul", service.mul(a));
    }

    @RequestMapping(path="/div", method=RequestMethod.GET)
    public OperationResponse div(@RequestParam double a, @RequestParam double b) {
        return new OperationResponse("div", service.div(a,b));
    }

    @RequestMapping(method=RequestMethod.POST)
    public OperationResponse operate(@Valid @RequestBody OperationRequest req) {
        String op = req.getOperation().toLowerCase();
        switch (op) {
            case "add": return new OperationResponse("add", service.add(req.getValues()));
            case "mul": return new OperationResponse("mul", service.mul(req.getValues()));
            case "sub": ensureSize(req.getValues(),2); return new OperationResponse("sub", service.sub(req.getValues().get(0), req.getValues().get(1)));
            case "div": ensureSize(req.getValues(),2); return new OperationResponse("div", service.div(req.getValues().get(0), req.getValues().get(1)));
            case "pow": ensureSize(req.getValues(),2); return new OperationResponse("pow", service.pow(req.getValues().get(0), req.getValues().get(1)));
            default: throw new IllegalArgumentException("Operação inválida: add, sub, mul, div, pow");
        }
    }

    private void ensureSize(List<Double> v, int n) {
        if (v == null || v.size() != n) throw new IllegalArgumentException("Operação requer exatamente " + n + " valores");
    }
}