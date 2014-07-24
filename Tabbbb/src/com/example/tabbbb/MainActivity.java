package com.example.tabbbb;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        
        tabHost.addTab(tabHost.newTabSpec("Apple")
        					  .setIndicator("Apple"),
        			   AppleFragment.class, 
        			   null);
        tabHost.addTab(tabHost.newTabSpec("Bee")
				  			  .setIndicator("Bee"),
				  	   BeeFragment.class, 
				  	   null);
        tabHost.addTab(tabHost.newTabSpec("Cat")
				  	  		  .setIndicator("Cat"),
				  	   CatFragment.class, 
				  	   null);
        tabHost.addTab(tabHost.newTabSpec("Dog")
				  			  .setIndicator("Dog"),
				  	   DogFragment.class, 
				  	   null);
    }

    public String getAppleData(){
        return "Apple 123";
    }
    public String getBeeData(){
        return "Bee 456";
    }
    public String getCatData(){
        return "Car 789";
    }
    public String getDogData(){
        return "Dog abc";
    }
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
