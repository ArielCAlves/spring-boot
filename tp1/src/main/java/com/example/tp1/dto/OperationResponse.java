package com.example.tp1.dto;

public class OperationResponse {
    private String operation;
    private double result;

    public OperationResponse() {}
    public OperationResponse(String operation, double result) {
        this.operation = operation; this.result = result;
    }
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }
}