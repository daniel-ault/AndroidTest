package com.example.androidtest;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class RouteCreateActivity extends ListActivity {
	
	String[] stringList = new String[] { "Test 1", "Test 2", "Test 3",
    		"Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9",
    		"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15"  };
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    // TODO Auto-generated method stub
	    
	    ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_row, stringList);
	    
	}

}
