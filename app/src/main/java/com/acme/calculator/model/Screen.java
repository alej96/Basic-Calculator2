package com.acme.calculator.model;

public class Screen {

   private String numbers;
   private String operants;
   private String result;

    public Screen() {
        restart();
    }

    public void restart() {
        result = "0";
        numbers = "";
        operants = "";
    }
}

