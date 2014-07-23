package com.example.androidtest;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
			settingsClick();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void settingsClick() {
		shortToast("WOOT");
		
		Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
		startActivity(intent);
		
	}
	
	public void onButtonClick(View view) {
    	//shortToast("HEY LOOK IT WORKED!!!!!\nlet's see if i can change it");
    	
    	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	boolean checkbox = sharedPref.getBoolean("checkboxBanana", false);
    	
    	boolean checkboxBanana = sharedPref.getBoolean("checkboxBanana", false);
    	String editTextPreference = sharedPref.getString("editTextPreference", "");
    	boolean checkboxWoot = sharedPref.getBoolean("checkboxWoot", false);
    	boolean checkboxTest = sharedPref.getBoolean("checkboxTest", false);
    	boolean checkboxNo = sharedPref.getBoolean("checkboxNo", false);
    	boolean checkboxYes = sharedPref.getBoolean("checkboxYes", false);
    	
    	String preferences = "" + checkboxBanana + "\n";
    	preferences += editTextPreference + "\n" + checkboxWoot + "\n" + checkboxTest + "\n";
    	preferences += checkboxNo + "\n" + checkboxYes;
    	
    	shortToast(preferences);
    	
    	
    	Intent intent = new Intent(MainActivity.this, TestActivity.class);
    	startActivity(intent);
    	
	}
	
	
	
	public void shortToast(CharSequence text) {
		Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text,  duration);
    	toast.show();
	}
	
	/*
	public void onClickSettings(View view) {
		Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
		startActivity(intent);
	}
	*/
}
