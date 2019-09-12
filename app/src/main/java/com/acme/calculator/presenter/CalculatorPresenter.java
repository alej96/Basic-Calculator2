package com.acme.calculator.presenter;

import com.acme.calculator.model.Board;
import com.acme.calculator.model.Player;
import com.acme.calculator.view.CalculatorView;
import com.acme.calculator.model.Screen;

public class CalculatorPresenter implements Presenter {

    private CalculatorView view;
    private Screen model;
    String num1;
    String num2;
    String operation;

    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        this.model = new Screen();
    }

    @Override
    public void onCreate() {
        model = new Screen();
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

    public void onButtonSelected(String button) {
//        Player playerThatMoved = model.mark(row, col);
//
//        if(playerThatMoved != null) {
//            view.setButtonText(row, col, playerThatMoved.toString());
//
//            if (model.getWinner() != null) {
//                view.showCalculations(playerThatMoved.toString());
//            }
//        }
    }

    public void onResetSelected() {
     //   view.clearWinnerDisplay();
        view.clearScreen();
        model.restart();
    }


}
