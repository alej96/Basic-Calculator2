package com.acme.calculator.view;

public interface CalculatorView {
    void showCalculations(String winningPlayerDisplayLabel);
    void setButtonText(int row, int col, String text);
}
