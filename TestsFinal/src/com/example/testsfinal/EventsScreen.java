package com.example.testsfinal;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventsScreen extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events_screen);
		
		//Переменные*****************************************************************************************************************************************
				DB db=new DB(getBaseContext());
				SQLiteDatabase sqdb=db.getReadableDatabase();
				ArrayList<String> events = new ArrayList<String>();
				ListView eventList=(ListView) findViewById(R.id.events_list);
				String eventName;
				String blockID;
				Cursor cursor;
				
				eventName=getIntent().getExtras().getString("eventName");
				blockID=getIntent().getExtras().getString("blockID");
				
				registerForContextMenu(eventList);

		//Заполнение списка с записями событий////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				
					cursor = sqdb.query(DB.TABLE_NAME_MAIN, new String[] {
							DB.UID, DB.TEST_TYPE, DB.START, DB.STOP, DB.GPS, DB.COMMENTS}, 
							DB.EVENT_NAME+"=? and "+ DB.BLOCK_NUMBER+"=?", // The columns for the WHERE clause
							new String[]{eventName,blockID}, // The values for the WHERE clause
							null, // don't group the rows
							null, // don't filter by row groups
							null // The sort order
							);
				
				    
				    if (cursor.moveToLast()) {
			            do {	            	
			                events.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));	 
			            } while (cursor.moveToPrevious());
			        }
			        if (cursor != null && !cursor.isClosed()) {
			            cursor.close();
			        }	         
				    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				            android.R.layout.simple_list_item_1, events);
				    eventList.setAdapter(dataAdapter);
				    sqdb.close();
				    db.close();
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.events_screen, menu);
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
	
	
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        
        AdapterContextMenuInfo aMenuInfo = (AdapterContextMenuInfo) menuInfo;
        int position = aMenuInfo.position;
       
}
	
	public boolean onContextItemSelected(MenuItem item) {
        
       
        return super.onContextItemSelected(item);
    }
}
