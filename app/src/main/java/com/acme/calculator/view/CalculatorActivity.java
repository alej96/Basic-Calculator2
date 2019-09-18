package com.acme.calculator.view;

//import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.acme.calculator.R;
import com.acme.calculator.presenter.CalculatorPresenter;
import com.acme.calculator.model.Screen;
//import com.acme.calculator.databinding.ActivityMainBinding;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static String TAG = CalculatorActivity.class.getName();


    private ViewGroup buttonGrid;
    private TextView calculationFormula;
    private TextView resultScreen;
    private Button clearButton; //C button
    private Button numberButton;
    String screenResult;
    String formulaCalcualtions;

   // private TextView winnerPlayerLabel;

    CalculatorPresenter presenter = new CalculatorPresenter(this);

    //private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Tag1","Debug here OnCreate()");
      //  resultScreen.setVisibility(View.VISIBLE);
        setContentView(R.layout.calculator);
//        winnerPlayerLabel = (TextView) findViewById(R.id.calculationResults);
        resultScreen = (TextView) findViewById(R.id.calculationResults); //display the calcualtions in the screen
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);
       // clearButton = (Button) findViewById(R.id.action_reset);
        calculationFormula = (TextView)findViewById(R.id.calculationFormula);
        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_reset:
//                presenter.onResetSelected();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public void onCellClicked(View v) {
    //do a if statement to recognize if it a number or operant
        Button button = (Button) v;
        String tag = button.getTag().toString();


        Log.i(TAG, "Debug Button clicked:" + tag);
        screenResult =  presenter.decodeAndCalculate(tag);
        formulaCalcualtions = presenter.getFormula();

        this.showCalculations(screenResult);
       this.displayFormula(formulaCalcualtions);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    //clear screen when press Reset
    public void clearScreen() {
        Log.i("TAG2","Debug Reset Screen");

        resultScreen.setText("0");
        resultScreen.setVisibility(View.VISIBLE);
    }

    //display number in the screen
    public void showCalculations(String calculationsResult) {
        resultScreen.setText(calculationsResult);
        resultScreen.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        //CalculationResults.setVisibility(View.GONE);
        //winnerPlayerLabel.setText("");
    }
    public void refreshScreen(){
        resultScreen.refreshDrawableState();
    }
    public void displayFormula(String formula){
        calculationFormula.setText(formula);
        calculationFormula.setVisibility(View.VISIBLE);
    }
}
