package com.acme.calculator.presenter;

import com.acme.calculator.view.CalculatorView;
import com.acme.calculator.model.Screen;
import com.acme.calculator.model.State;
import static com.acme.calculator.model.State.equalState;
import static com.acme.calculator.model.State.initialState;
import static com.acme.calculator.model.State.op1State;
import static com.acme.calculator.model.State.saveOperationState;
import static com.acme.calculator.model.State.runCalculationsState;



public class CalculatorPresenter implements Presenter {

    private CalculatorView view;
    private Screen model;

    String toDisplayScreen;
    State currentState;
    String fullFormula;

    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        this.model = new Screen();
    }

    @Override
    public void onCreate() {
        model = new Screen();
        currentState = initialState;
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

    public String decodeAndCalculate(String tag) {

        //do all calculations (funtion from model and display number/operant in screen!
        if(tag.startsWith("number"))
        {
            if(currentState == saveOperationState){
                currentState = op1State;
            }
                else if(currentState == equalState){
                currentState = initialState;
                model.resetFormula();
            }

            toDisplayScreen =  model.decodeNumber(tag);
            toDisplayScreen = model.appendNumbers(toDisplayScreen);


            switch (currentState) {
                case initialState:

                    model.InitialState(toDisplayScreen);

                    break;
                case op1State:

                    model.stateAddNum_1_Only(toDisplayScreen);
                    break;

            }
            return toDisplayScreen;

        }else if( tag.startsWith("operator"))
        {
            if(currentState == initialState) {
                currentState = saveOperationState;
            }else if(currentState == op1State){
                currentState = runCalculationsState;
            }else if (currentState == equalState){
                currentState = saveOperationState;
            }

            switch (currentState) {
                case saveOperationState:
                    model.saveOperationState(tag);

                     break;

                case runCalculationsState:
                    toDisplayScreen = model.runCalculations();
                    model.saveOperationState(tag);
                    currentState = op1State;
                    break;
            }
            return toDisplayScreen;
        }else if (tag.startsWith("command"))
        {
            toDisplayScreen = model.doCommands(tag);
            if(tag.equals("command=")){
                model.EqualState();
                currentState = equalState;
            }else if(tag.equals("commandC")){
                currentState = initialState;
              }
            return toDisplayScreen;
        }

        return "This should not show up";

    }

    public String getFormula(){

        fullFormula = model.combineFormulas();
        return fullFormula;
    }

}
