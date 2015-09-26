package com.example.olxdemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends /* ActionBar */Activity {

	HashMap<String, String> items = new HashMap<String, String>();
	// HashMap<String, MobileData> car = new HashMap<String, MobileData>();
	// ArrayList<String> cars = new ArrayList<String>();
	// ArrayList<String> electronics = new ArrayList<String>();

	int resid;
	AutoCompleteTextView text;
	AutoCompleteTextView text1;
	
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private Button search;
	String[] autoSearchList = {"mobile", "car","electronics","airConditioner", "tV","refrigerator", "camera", "volkswagon", 
			"washingmachine","nokia","iphone","blackberry","android","fiat","honda","maruti","hyundai","skoda","iphone2","iphone3"};
	String[] cityList = {"delhi", "noida","gurgaon","rohtak", "chandigarh","patiala", "ambala"};
	private Context mContext;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		fillMobiledata();
		fillCardata();
		fillElectronicsdata();

        mTitle = mDrawerTitle = getTitle();
		
		mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
//		 Spinner spinner = (Spinner) findViewById(R.id.spinner);
//		 
//	     spinner.setOnItemSelectedListener(this);
	     
	     ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList);
	     
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
//	        spinner.setAdapter(dataAdapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];
		 
		drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_launcher, "Mobile");
		drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_launcher, "Car");
		drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_launcher, "Electronics");
		
		DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);

		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener() );
		
		mDrawerToggle = new ActionBarDrawerToggle(
		        this,
		        mDrawerLayout,
		        R.string.drawer_open, 
		        R.string.drawer_close 
		        ) {
		    
		    /** Called when a drawer has settled in a completely closed state. */
		    public void onDrawerClosed(View view) {
		    	text.setVisibility(View.VISIBLE);
		        text1.setVisibility(View.VISIBLE);
		        search.setVisibility(View.VISIBLE);
		        super.onDrawerClosed(view);
		        
		        setTitle(mTitle);
		    }
		 
		    /** Called when a drawer has settled in a completely open state. */
		    public void onDrawerOpened(View drawerView) {
		    	text.setVisibility(View.INVISIBLE);
		        text1.setVisibility(View.INVISIBLE);
		        search.setVisibility(View.INVISIBLE);
		        super.onDrawerOpened(drawerView);
		        
		        setTitle(mDrawerTitle);
		    }
		};
		 
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		 
		text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		text1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
		search = (Button) findViewById(R.id.button1);
		
		ArrayAdapter autoadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, autoSearchList);
		ArrayAdapter cityadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, cityList);

		text.setAdapter(autoadapter);
		text.setThreshold(1);

		text1.setAdapter(cityadapter);
		text1.setThreshold(1);
		
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(text!=null){
					String s = text.getText().toString();
					if (items.get(s) != null) {
						Toast.makeText(mContext, "category is " + items.get(s),
								Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});


	}
	/*@Override
    public void onItemSelected(AdapterView parent, View view, int position) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
 
    }*/
 
	public void fillMobiledata() {

		items.put("windows", "mobile");
		items.put("nokia", "mobile");
		items.put("iphone", "mobile");
		items.put("blackberry", "mobile");
		items.put("android", "mobile");
		items.put("mobile", "mobile");
	}

	public void fillCardata() {

		items.put("fiat", "car");
		items.put("honda", "car");
		items.put("maruti", "car");
		items.put("hyundai", "car");
		items.put("volkswagon", "car");
		items.put("skoda", "car");
		items.put("car", "car");

		
	}

	public void fillElectronicsdata() {

		items.put("AirConditioner", "electronics");
		items.put("TV", "electronics");
		items.put("Refrigerator", "electronics");
		items.put("Camera", "electronics");
		items.put("Volkswagon", "electronics");
		items.put("WashingMachine", "electronics");
		items.put("electronics", "electronics");
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
		       return true;
		 }
		return super.onOptionsItemSelected(item);
	}
	
	class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}

		private void selectItem(int position) {

			Fragment fragment = null;

			switch (position) {
			case 0:
				fragment = new CreateFragment();
				break;
//			case 1:
//				fragment = new ReadFragment();
//				break;
//			case 2:
//				fragment = new HelpFragment();
//				break;

			default:
				break;
			}

//			if (fragment != null) {
//				FragmentManager fragmentManager = getSupportFragmentManager();
//				fragmentManager.beginTransaction()
//						.replace(R.id.content_frame, fragment).commit();
//
//				mDrawerList.setItemChecked(position, true);
//				mDrawerList.setSelection(position);
//				setTitle(mNavigationDrawerItemTitles[position]);
//				mDrawerLayout.closeDrawer(mDrawerList);
//
//			} else {
//				Log.e("MainActivity", "Error in creating fragment");
//			}
		}
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    mDrawerToggle.syncState();
	}
}
