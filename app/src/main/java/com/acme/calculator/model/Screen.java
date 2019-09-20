package com.acme.calculator.model;

import android.util.Log;
import java.text.DecimalFormat;

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
    String fullFormula;


    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String DECIMAL = ".";
    private static final String NEGATIVE = "*-1";
    private static final String EQUAL = "=";
    private static final String BACKSLASH = "BackSlash";
    private static final String C = "C";



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
        fullFormula = "";
    }


    public String decodeNumber(String tag){

        decoded =  tag.substring(6);
        fullFormula = fullFormula + decoded;


       return  decoded;
    }
    public String decodeOperator(String tag){

        operants =  tag.substring(8);
        fullFormula = fullFormula  + operants;
        return operants;
    }

    public String decodeCommand(String tag){

        command =  tag.substring(7);
        return command;
    }

    public String appendNumbers(String num){

         if(!fullNum.contains(DECIMAL) || !num.equals(DECIMAL)) {

            if((! num.equals(NEGATIVE))) {

                try {
                    fullNum = fullNum + num;
                    number1 = Double.parseDouble(fullNum);
                }catch(Exception e){
                    Log.i(TAG, "Debug: Error!->" + e);
                }


            }else{
                try{
                    number1 =  Double.parseDouble(fullNum) * -1;
                    fullNum = Double.toString(number1);
                }catch (Exception e){
                    Log.i(TAG, "Debug: Error! The first input should be a number, " +
                             "not the negative ->" + e);
                    fullNum = "";
                    fullFormula = "";
                }


            }
            Log.i(TAG, "Debug append numbers -> decoded: " + decoded + " number1: " + number1
                    + " num2: " + number2 + " full num: " + fullNum );
        }



        return fullNum;
    }

    public String runCalculations(){

                if (operants.equals(ADDITION)) {
                    result = number2 + number1;
                    Log.i(TAG, "Calculation Debug : Addition: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else if (operants.equals(SUBTRACTION)) {
                    Log.i(TAG, "Calculation Debug : Subtraction: " + number2 + " " + operants + " " + number1 + " = " + result);
                    result = number2 - number1;
                } else if (operants.equals(MULTIPLICATION)) {
                    result = number2 * number1;
                    Log.i(TAG, "Calculation Debug : Multiplication: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else if (operants.equals(DIVISION)) {
                    result = number2 / number1;
                    Log.i(TAG, "Calculation Debug : Division: " + number2 + " " + operants + " " + number1 + " = " + result);
                } else{
                    //if you press = before any operants are pressed
                    return Double.toString(number2);
                }


            number2 = result;
            number1 = 0;
            fullNum = "";

        return Double.toString(result);
    }

    public String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    public String doCommands(String tag){

        command = this.decodeCommand(tag);
         if(command.equals(BACKSLASH)){

             try {
                 //  Block of code to try
                 fullNum = removeLastChar(fullNum);
                 fullFormula =  removeLastChar(fullFormula);
                 number1 = Double.parseDouble(fullNum);
             }
             catch(Exception e) {
                 //  Block of code to handle errors
                 Log.i(TAG, "Debug: Empty String when you try to delete the last character" + e);
                 fullFormula = "";
             }


            Log.i(TAG , "Debug BackLashed num: " +
             " number1: " + number1 + "num2: " + number2 + " full num: " + fullNum );
        }
        else if(command.equals(C)) {
            this.restart();
            return "0";
        }else if (command.equals(EQUAL)){

            fullNum = this.runCalculations();
             fullFormula = fullFormula  + EQUAL + fullNum;

         }

         return fullNum;

    }
    public void stateAddNum_1_Only(String num){
        try {
            number1 = Double.parseDouble(num);
        } catch(Exception e){
            Log.i(TAG, "Debug" + e);
        }



}
    public String num2State(String num){
       num = runCalculations();

       return num;
    }
    public void EqualState(){
        try {
            number1 = 0;
            operants = "";
            fullNum = "";
        }catch(Exception e){
            Log.i(TAG, "Debug" + e);
        }



    }
    public void InitialState(String num){
        try {
            number2 = Double.parseDouble(num);
        }catch (Exception e){
            Log.i(TAG, "" + e);
            number2 = 0;
            if(num.equals(DECIMAL)){
                number2 = 0;
            }
        }
        number1 = 0;
        operants = "";
        Log.i(TAG, "Debug Intial State:  num2:  " + number2);
    }

    public void saveOperationState(String opTag){

        try {

            operants = decodeOperator(opTag);
            number1 = 0;
            fullNum = "";
        }catch(Exception e){
            Log.i(TAG, "Debug: " + e);
        }

    }

    public String combineFormulas(){

        return fullFormula;
    }
    public void  resetFormula(){
        fullFormula = "";
    }
}

