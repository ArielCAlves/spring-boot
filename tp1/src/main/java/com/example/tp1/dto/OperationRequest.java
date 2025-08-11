package com.example.tp1.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OperationRequest {
    @NotNull
    private String operation;
    @NotNull
    private List<Double> values;

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public List<Double> getValues() { return values; }
    public void setValues(List<Double> values) { this.values = values; }
}