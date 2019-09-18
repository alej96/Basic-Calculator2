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
       // number1 =  Double.parseDouble(decoded);
      // this.appendNumbers(decoded);
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

        //count the number of time I have pressed .
//        for(int i = 0 ; i < fullNum.length(); i++){
//           if(fullNum.charAt(i) == (DECIMAL)){
//               counterDecimal++;
//           }


        if(!fullNum.contains(DECIMAL) || !num.equals(DECIMAL)) {

            if((! num.equals(NEGATIVE))) {

                fullNum = fullNum + num;
                number1 = Double.parseDouble(fullNum);


            }else{

                number1 =  Double.parseDouble(fullNum) * -1;
                fullNum = Double.toString(number1);
            }

            Log.i(TAG, "Debug append numbers -> decoded: " + decoded + " number1: " + number1 + "num2: " + number2 + " full num: " + fullNum );
        }



        return fullNum;
    }

    public String runCalculations(){

        //operants = this.decodeOperator(tag);
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
                } /*else if ((operants.equals(NEGATIVE))) {
                    result = number1 * -1;
                    Log.i(TAG, "Calculation Debug : Change to -/+: " + number2 + " " + operants + " " + number1 + " = " + result);

                }*/
           // }
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

             fullNum = removeLastChar(fullNum);
             fullFormula =  removeLastChar(fullFormula);
             number1 = Double.parseDouble(fullNum);

            Log.i(TAG , "Debug BackLashed num: " +
             " number1: " + number1 + "num2: " + number2 + " full num: " + fullNum );
        }
        else if(command.equals(C)) {
            this.restart();
            return "0";
        }else if (command.equals(EQUAL)){

            fullNum = this.runCalculations();
             fullFormula = fullFormula  + EQUAL + result;

         }

         return fullNum;

    }
    public void stateAddNum_1_Only(String num){

       // number2 = 0;
       // operants = "";
        number1 = Double.parseDouble(num);

    }
    public String num2State(String num){
       num = runCalculations();

       return num;
    }
    public void EqualState(){

        number1 = 0;
        operants = "";
        fullNum = "";


    }
    public void InitialState(String num){

        number2 = Double.parseDouble(num);
        number1 = 0;
        operants = "";
        Log.i(TAG, "Debug Intial State:  num2:  " + number2);
    }

    public void saveOperationState(String opTag){

        operants = decodeOperator(opTag);
        number1 = 0;
        fullNum = "";

    }

    public void stateAddOperator(String tag){
        operants = this.decodeOperator(tag);
    }
    public String combineFormulas(){

        return fullFormula;
    }
    public void  resetFormula(){
        fullFormula = "";
    }
}

