package com.example.ezcounter;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }
    
    private TextView txv;
    private Button btn_count;
    int count;
    
    private void findViews(){
    	txv = (TextView) findViewById(R.id.textView1);
    	btn_count = (Button) findViewById(R.id.button1);
    }
    private OnClickListener plus = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			txv.setText(String.valueOf(++count));
		}
	};
	private OnLongClickListener reset = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.button1){
				count += 2;
				txv.setText(String.valueOf(count));
			}
			else if(v.getId() == R.id.textView1){
				count = 0;
				txv.setText(String.valueOf(count));
			}
			return true;
		}
	};
	private OnTouchListener vibrate = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			if(event.getAction() == MotionEvent.ACTION_DOWN)
				vb.vibrate(5000);
			else
				vb.cancel();
			return true;
		}
	};
	private void setListeners(){
		btn_count.setOnClickListener(plus);
		txv.setOnClickListener(plus);
		btn_count.setOnLongClickListener(reset);
		txv.setOnLongClickListener(reset);
		txv.setOnTouchListener(vibrate);
	}
}
