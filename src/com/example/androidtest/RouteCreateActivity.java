package com.example.androidtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class RouteCreateActivity extends RouteDisplayActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	}
	
	public void addItem(String intersection, double distanceFromStart) {
		this.route.addCheckpoint(intersection, distanceFromStart);
		//updateView();
		listAdapter.clear();
		listAdapter.addAll(route.getCheckpointArrayList());
		listAdapter.notifyDataSetChanged();
	}
	
	public void deleteItem(int position) throws IndexOutOfBoundsException {
		route.deleteCheckpoint(position);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.route_create, menu);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//route.deleteCheckpoint(position);
		//shortToast(route.toString());
		shortToast(String.valueOf(position));
		//updateView();
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_create_add_checkpoint) {
			addCheckpointDialog();
			return true;
		}
		else if (id == R.id.menu_create_save) {
			shortToast(route.toString());
			return true;
		}
		return false;
	}
	
	public void addCheckpointDialog() {
		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.add_checkpoint_dialog, null);
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Add Checkpoint");
		alert.setView(textEntryView);
		
		final EditText input1 = (EditText)textEntryView.findViewById(R.id.editText1);
		final EditText input2 = (EditText)textEntryView.findViewById(R.id.editText2);
		
		alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				addItem(input1.getText().toString(), Double.valueOf(input2.getText().toString()));
			}
		});
		
		alert.setNegativeButton("Cancel", null);
		
		alert.show();
	}
	
	public void shortToast(CharSequence text) {
		Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text,  duration);
    	toast.show();
	}	
	
}
