package com.example.exerciseconsumer;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }
    private Button btn_calc;
    private TextView total, txvRate;
    private EditText weight, timeSpan;
    private Spinner sports;
    private double[] energyRate={3.1,4.4,13.2,9.7,5.1,3.7};
    private void findViews(){
    	btn_calc = (Button) findViewById(R.id.button1);
    	total = (TextView) findViewById(R.id.total);
    	txvRate = (TextView) findViewById(R.id.txvRate);
    	weight = (EditText) findViewById(R.id.weight);
    	timeSpan = (EditText) findViewById(R.id.timeSpan);
    	sports = (Spinner) findViewById(R.id.sports);
    }
    private void setListeners(){
    	btn_calc.setOnClickListener(calc);
    	sports.setOnItemSelectedListener(select);
    }
    private OnClickListener calc = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(weight.getText().length() == 0 || timeSpan.getText().length() == 0)
				return;
			double w = Double.parseDouble(weight.getText().toString());
			double t =Double.parseDouble(timeSpan.getText().toString());
			int selected = sports.getSelectedItemPosition();
			long consumed = Math.round(energyRate[selected]*w*t);
			total.setText(String.format("消耗能量\n  %d仟卡", consumed));
		}
	};
	private OnItemSelectedListener select = new OnItemSelectedListener() {
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			txvRate.setText(String.valueOf(energyRate[position]));
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};
}
