package com.example.androidtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;

public class TestActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    
	    String[] stringList = new String[] { "Test 1", "Test 2", "Test 3",
	    		"Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9",
	    		"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15"  };
	    
	    final ArrayAdapter adapter = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_list_item_checked, stringList);
	    
	    ListView listView = (ListView)findViewById(R.id.listview);
	    listView.setAdapter(adapter);
	    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		//item clicked
	    		
	    	}
	    });
	    
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