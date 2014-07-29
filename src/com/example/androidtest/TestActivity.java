package com.example.androidtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.RunningTracker.Checkpoint;
import com.example.RunningTracker.RunRoute;
import com.example.RunningTracker.RunningTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TestActivity extends ActionBarActivity  implements OnItemClickListener {
	static final String LOG_TAG = "TestActivity";
	
	private String[] stringList;
	private ListAdapter listAdapter;
	private ListView listView;
	private RunRoute route = RunningTracker.createRoute();
	
	private Bundle savedInstanceState;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.savedInstanceState = savedInstanceState;
	    setContentView(R.layout.list);
	    
	    /*
	    String[] stringList = new String[] { "Test 1", "Test 2", "Test 3",
	    		"Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9",
	    		"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15"  };
	    		*/
	    
	    String[] stringList = RunningTracker.createRoute().getStringList();
	    this.stringList = stringList;
	    /*
	    final ArrayAdapter adapter = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_list_item_activated_1, stringList);
	    */
	    
	    /*
	    final ArrayAdapter adapter = new ArrayAdapter<String>(this,
	    		R.layout.list_row, stringList);
	    */
	    
	    ListAdapter adapter = new ListAdapter(this, R.layout.list_row, 
	    		RunningTracker.createRoute().getCheckpointArrayList(),
	    		RunningTracker.createRoute());
	    this.listAdapter = adapter;
	    
	    listView = (ListView)findViewById(R.id.listview);
	    listView.setAdapter(listAdapter);
	    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    listView.setOnItemClickListener(this);
	    
	    /*
	    listView.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		//item clicked
	    		
	    		shortToast(String.valueOf(position));
	    	}
	    });
	    */
	    
	    
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		//shortToast(String.valueOf(this.listAdapter.items.size()));
		//shortToast(route.toString());
		if (position+1 != route.getCheckpointArrayList().size()) {
			return;
		}
		route.addCheckpoint("test", position+1);
		listAdapter.clear();
		listAdapter.addAll(route.getCheckpointArrayList());
		listAdapter.notifyDataSetChanged();
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
			menuSave();
			return true;
		}
		else if (id == R.id.menu_list_nope) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void menuSave() {
		if (isExternalStorageReadable() == false) {
			shortToast("No external storage detected.");
			return;
		}
		shortToast("Saving file to sdcard...");
		File testFile = makeFolder("testFile");
		
		String filename = testFile.getAbsolutePath() + "/test.csv";
		
		try {
			RunningTracker.createRoute().saveToCSV(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}
	
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) || 
			Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	public File makeFolder(String filename) {
		File file = new File(Environment.getExternalStoragePublicDirectory(
				""), filename);
		if (!file.mkdirs()) {
			Log.e(LOG_TAG, "Directory not created.");
		}
		return file;
	}

/*
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    final ListView listview = (ListView)findViewById(R.id.listview);
	    
	    String[] stringList = new String[] { "Test 1", "Test 2", "Test 3",
	    		"Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9",
	    		"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15"  };
	    
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i=0; i<stringList.length; ++i) {
	    	list.add(stringList[i]);
	    }
	    
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	    		android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        view.animate().setDuration(2000).alpha(0)
	            .withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                list.remove(item);
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	            });
	      }

	    });
	}
	
	
	
	private class StableArrayAdapter extends ArrayAdapter<String> {
		
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		
		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i=0; i<objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
			// TODO Auto-generated constructor stub
		}
		
		@Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }
		
	}

}
*/

	public void shortToast(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text,  duration);
		toast.show();
	}

}