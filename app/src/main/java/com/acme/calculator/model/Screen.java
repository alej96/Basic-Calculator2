package com.acme.calculator.model;

public class Screen {


    private String numbers;
   private String operants;
   private String result;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char DECIMAL = '.';
    private static final String NEGATIVE = "*-1";

    public Screen() {
        restart();
    }

    public void restart() {
        result = "0";
        numbers = "";
        operants = "";
    }

    public String getOperants() {
        return operants;
    }

    public void setOperants(String operants) {
        this.operants = operants;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}

