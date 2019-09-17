package com.acme.calculator.presenter;

import com.acme.calculator.view.CalculatorView;
import com.acme.calculator.model.Screen;
import com.acme.calculator.model.State;
import static com.acme.calculator.model.State.equalState;
import static com.acme.calculator.model.State.initialState;
import static com.acme.calculator.model.State.op1State;
import static com.acme.calculator.model.State.op2State;

public class CalculatorPresenter implements Presenter {

    private CalculatorView view;
    private Screen model;
    String num1;
    String num2;
    String operation;
    String toDisplayScreen;
    String helperToDisplay ;
    State currentState;

    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        this.model = new Screen();
    }

    @Override
    public void onCreate() {
        model = new Screen();
        currentState = State.initialState;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    //this pesudo code...operads

    public String decodeAndCalculate(String tag) {

        //do all calculations (funtion from model and display number/operant in screen!

        if(tag.startsWith("number"))
        {
              // model.num1.append(); //combine the numbers and make it a screen
            // View.textbox.refresh(); //refresh the screen results

            toDisplayScreen =  model.decodeNumber(tag);
            toDisplayScreen = model.appendNumbers(toDisplayScreen);
            view.showCalculations(toDisplayScreen);  //dont thiks this it necessary
            return toDisplayScreen;

        }else if( tag.startsWith("operator"))
        {
           toDisplayScreen =   model.runCalculations(tag);
            helperToDisplay = toDisplayScreen;
            toDisplayScreen ="";
            return helperToDisplay;
        }else if (tag.startsWith("command"))
        {
            toDisplayScreen = model.doCommands(tag);
            return toDisplayScreen;
        }

        return "This should not show up";

    }

    public void onResetSelected() {
     //   view.clearWinnerDisplay();
        view.clearScreen();
        model.restart();
    }
   // public enum State { op1State , op2State, equalsState }


}
