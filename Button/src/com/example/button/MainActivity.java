package com.example.button;

import java.util.jar.Attributes.Name;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
	private EditText name;
	private Button btn;
    private void findViews(){
    	txv = (TextView) findViewById(R.id.txv);
    	name = (EditText) findViewById(R.id.name);
    	btn = (Button) findViewById(R.id.button1);	
    }
    private void setListeners() {
    	btn.setOnClickListener(sayHello);
	}
    
    private OnClickListener sayHello = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			txv.setTextSize(30);
	    	String str = name.getText().toString();
	    	if(str.length() == 0)
	    		txv.setText(R.string.please_input_your_name);
	    	else
	    		txv.setText(str + ", Hello!");
		}
	};
        
}
