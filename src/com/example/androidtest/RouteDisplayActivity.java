package com.example.androidtest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.RunningTracker.Checkpoint;
import com.example.RunningTracker.RunRoute;
import com.example.RunningTracker.RunningTracker;

public class RouteDisplayActivity extends ActionBarActivity implements OnItemClickListener {

	protected RunRoute route;
	protected ListAdapter listAdapter;
	private ListView listView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    
	    route = RunningTracker.createRoute();//= new RunRoute();
	    
	    //addRoute(RunningTracker.createRoute());
	    
	    ListAdapter adapter = new ListAdapter(this, R.layout.list_row,
	    		route.getCheckpointArrayList(), route);
	    this.listAdapter = adapter;
	    
	    listView = (ListView)findViewById(R.id.listview);
	    listView.setAdapter(listAdapter);
	    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    listView.setOnItemClickListener(this);
	}
	
	
	
	public void addRoute(RunRoute route) {
		this.route = route;
	}
	
	
	protected void updateView() {
		listAdapter.clear();
		listAdapter.addAll(route.getCheckpointArrayList());
		listAdapter.notifyDataSetChanged();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_list_save) {
			
			//menuSaveDialog();
			return true;
		}
		else if (id == R.id.menu_list_nope) {
			//menuAddCheckpointDialog();
			//this.addItem("test banana", 50.1);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}
	
	
	
	public class ListAdapter extends ArrayAdapter<Checkpoint> {

	    private ArrayList<Checkpoint> items;
	    //private RunRoute route;

	    public ListAdapter(Context context, int textViewResourceId, ArrayList<Checkpoint> items,
	    		RunRoute route) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	            //this.route = route;
	    }
	    
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
	                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                v = vi.inflate(R.layout.list_row, null);
	            }
	            Checkpoint o = items.get(position);
	            if (o != null) {
	                    TextView tt = (TextView) v.findViewById(R.id.toptext);
	                    TextView mt = (TextView) v.findViewById(R.id.middletext);
	                    TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                    if (tt != null) {
	                          tt.setText("Intersection: "+o.getIntersection());}
	                    if (mt != null) {
	                    	mt.setText("Distance: " + o.getDistanceFromStart() + " mi");
	                    }
	                    if(bt != null){
	                          bt.setText("Goal Time: "+ o.getGoalTimeString());
	                    }
	            }
	            return v;
	    }
	    
	    public int getSize() { return items.size(); }
	    
	}

}
