package com.acme.calculator.model;

import android.util.Log;

import com.acme.calculator.view.CalculatorActivity;

public class Screen {

    private static String TAG = CalculatorActivity.class.getName();

    double number1;
    double number2;
    String operants;
    String command;
    double result;
    String decoded;
    String fullNum;
    String tempNum;

    int operationsCounter;

    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String DECIMAL = ".";
    private static final String NEGATIVE = "*-1";
    private static final String EQUAL = "=";
    private static final String BACKSLASH = "BackSlash";
    private static final String C = "C";

    //turns
    boolean num1Turn;
    boolean num2Turn;


    public Screen() {
        restart();
    }

    public void restart() {
        result = 0;
        number1 = 0;
        number2 = 0;
        fullNum = "";
        operants = "";
        decoded = "";
        tempNum = "";
        command = "";
        num1Turn = false;
        num2Turn = false;
        operationsCounter = 0;
    }


    public String decodeNumber(String tag){

        decoded =  tag.substring(6);
       // number1 =  Double.parseDouble(decoded);
      // this.appendNumbers(decoded);
        num1Turn = true;

       return  decoded;
    }
    public String decodeOperator(String tag){

        operants =  tag.substring(8);
        return operants;
    }

    public String decodeCommand(String tag){

        command =  tag.substring(7);
        return command;
    }

    public String appendNumbers(String num){
        Log.i(TAG , "===BEFORE=== append: " + decoded + " number1: " + number1 +" full num: " + fullNum );

        //count the number of time I have pressed .
//        for(int i = 0 ; i < fullNum.length(); i++){
//           if(fullNum.charAt(i) == (DECIMAL)){
//               counterDecimal++;
//           }


        if(!fullNum.contains(DECIMAL) || !num.equals(DECIMAL)) {
            fullNum = fullNum + num;
            number1 = Double.parseDouble(fullNum);
            Log.i(TAG, "append numbers: " + decoded + " number1: " + number1 + " full num: " + fullNum);
        }



        return fullNum;
    }

    public String runCalculations(String tag){

        operants = this.decodeOperator(tag);
//            if(operationsCounter < 1)
//            {
//                number2 = number1;
//            }
//            if ((operants.equals(EQUAL))) {
//                num2Turn = true;
//                num1Turn = true;
//            }

           // if(num1Turn == true && num2Turn == true) {
                if (operants.equals(ADDITION)) {
                    result = number2 + number1;
                    Log.i(TAG, "Calculation Debug : Addition: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else if (operants.equals(SUBTRACTION)) {
                    Log.i(TAG, "Calculation Debug : Substraction: " + number2 + " " + operants + " " + number1 + " = " + result);
                    result = number2 - number1;
                } else if (operants.equals(MULTIPLICATION)) {
                    result = number2 * number1;
                    Log.i(TAG, "Calculation Debug : Multiplicaion: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else if (operants.equals(DIVISION)) {
                    result = number2 / number1;
                    Log.i(TAG, "Calculation Debug : Division: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else if ((operants.equals(NEGATIVE))) {
                    result = number2 * -1;
                    Log.i(TAG, "Calculation Debug : Change to -/+: " + number2 + " " + operants + " " + number1 + " = " + result);

                }
           // }
            number2 = result;
            number1 = 0;
            fullNum = "";
            num2Turn = false;
            operationsCounter++;

        return Double.toString(result);
    }

    public String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    public String doCommands(String tag){

        command = this.decodeCommand(tag);
         if(command.equals(BACKSLASH)){

                 fullNum = removeLastChar(fullNum);


            Log.i(TAG , "BackLashed num: " + fullNum);
        }
        else if(command.equals(C)) {
            this.restart();
            return "0";
        }else if (command.equals(EQUAL)){
            return fullNum;
         }

         return fullNum;

    }
    public String num1State(String num){

        number2 = 0;
        operants = "";
        number1 = Double.parseDouble(num);
        return num;
    }
    public String num2State(String num){
       num = runCalculations(num);

       return num;
    }
    public void EqualState(){


    }
    public void InitialState(){


    }
}

