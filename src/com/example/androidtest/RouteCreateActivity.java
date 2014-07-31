package com.example.androidtest;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.RunningTracker.RunRoute;


public class RouteCreateActivity extends ListActivity {
	
	String[] stringList = new String[] { "Test 1", "Test 2", "Test 3",
    		"Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9",
    		"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15"  };
	
	private RunRoute route;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    // TODO Auto-generated method stub
	    
	    route = new RunRoute();
	    route.setStart("Test");
	    
	    ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_row, stringList);
	    
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.route_create, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.createAddCheckpoint) {
			addCheckpoint();
			return true;
		}
		else if (id == R.id.createSaveRoute) {
			shortToast("Save Route");
			return true;
		}
		return false;
	}
	
	public void addCheckpoint() {
		final EditText txtUrl = new EditText(this);
        txtUrl.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        Builder alert = new AlertDialog.Builder(this);
        
        alert.setTitle("Moustachify Link");
        alert.setMessage("Paste in the link of an image to moustachify!");
        alert.setView(txtUrl);    
        alert.setPositiveButton("Moustachify", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		String url = txtUrl.getText().toString();
        	}
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		shortToast("yay");
        	}
        });
        alert.show();
        
	}
	
	public void shortToast(CharSequence text) {
		Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text,  duration);
    	toast.show();
	}
}
