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
//import com.acme.calculator.databinding.ActivityMainBinding;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static String TAG = CalculatorActivity.class.getName();

    private ViewGroup buttonGrid;
    private View CalculationResults;
    private TextView resultScreen;
    private Button clearButton; //C button
    private Button numberButton;
   // private TextView winnerPlayerLabel;

    CalculatorPresenter presenter = new CalculatorPresenter(this);

    //private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Tag1","here");
      //  resultScreen.setVisibility(View.VISIBLE);
        setContentView(R.layout.calculator);
//        winnerPlayerLabel = (TextView) findViewById(R.id.calculationResults);
        resultScreen = (TextView) findViewById(R.id.calculationResults); //display the calcualtions in the screen
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);
        clearButton = (Button) findViewById(R.id.action_reset);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCellClicked(View v) {

        Button button = (Button) v;
        String tag = button.getTag().toString();

        Log.i(TAG, "Tag name:" + tag);

        presenter.onButtonSelected(tag);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    //clear screen when press C
    public void clearScreen() {
        Log.i("TAG2","Here2");
        resultScreen.setVisibility(View.VISIBLE);
        resultScreen.setText("0");
    }


    public void showCalculations(String winningPlayerDisplayLabel) {
       // winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        CalculationResults.setVisibility(View.VISIBLE);
    }


    public void clearWinnerDisplay() {
        //CalculationResults.setVisibility(View.GONE);
        //winnerPlayerLabel.setText("");
    }
}
