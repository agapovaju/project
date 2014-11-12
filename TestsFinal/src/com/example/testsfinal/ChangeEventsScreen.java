package com.example.testsfinal;



import java.util.ArrayList;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChangeEventsScreen extends ActionBarActivity {

	DB db;
	SQLiteDatabase sqdb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_events_screen);
		
		final DB db=new DB(this);
        final SQLiteDatabase sqdb=db.getWritableDatabase();
		final Spinner eventsSpinner=(Spinner) findViewById(R.id.eventsSpinner);
		final Spinner blocksSpinner=(Spinner) findViewById(R.id.blocksSpinner);
		Button lookEventsButton=(Button) findViewById(R.id.lookRecordsButton);
		
		ArrayList<String> events = new ArrayList<String>();
		final ArrayList<String> blocks = new ArrayList<String>();
		
//Заполнение спиннера с испытаниями////////////////////////////////////////////////////////////////////////////////////
				Cursor cursorEvents = sqdb.query(DB.TABLE_NAME_EVENTS, new String[] {
						DB.EVENT_NAME}, 
						null, // The columns for the WHERE clause
						null, // The values for the WHERE clause
						null, // don't group the rows
						null, // don't filter by row groups
						null // The sort order
						);
			    
			    if (cursorEvents.moveToFirst()) {
		            do {	            	
		                events.add(cursorEvents.getString(0));	 
		            } while (cursorEvents.moveToNext());
		        }
		        if (cursorEvents != null && !cursorEvents.isClosed()) {
		        	cursorEvents.close();
		        }	         
			    
		        ArrayAdapter<String> dataAdapterEvents = new ArrayAdapter<String>(this,
			            android.R.layout.simple_list_item_1, events);
			    eventsSpinner.setAdapter(dataAdapterEvents);			    
			    
//Заполнение спиннера с ID блоков////////////////////////////////////////////////////////////////////////////////////			  				  	    
			  	    eventsSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							
							Cursor cursorBlocks = sqdb.query(true,DB.TABLE_NAME_MAIN, new String[] {
					  				DB.BLOCK_NUMBER}, 
					  				DB.EVENT_NAME+"=?", // The columns for the WHERE clause
					  				new String[]{eventsSpinner.getSelectedItem().toString()}, // The values for the WHERE clause
					  				null, // don't group the rows
					  				null, // don't filter by row groups
					  				null, // The sort order
					  				null
					  				);
					  	    blocks.clear();
					  	    if (cursorBlocks.moveToFirst()) {
					              do {	            	
					                  blocks.add(cursorBlocks.getString(0));					                  
					              } while (cursorBlocks.moveToNext());
					          }
					          if (cursorBlocks != null && !cursorBlocks.isClosed()) {
					        	  cursorBlocks.close();
					          }	         
					  	    ArrayAdapter<String> dataAdapterBlocks = new ArrayAdapter<String>(getBaseContext(),
					  	            android.R.layout.simple_list_item_1, blocks);
					  	    blocksSpinner.setAdapter(dataAdapterBlocks);
						}
						

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
			  	    	
			  	    });
			  	    
			  	    
			  	    
			  	    
//Прослушиватель на кнопке просмотра записей//////////////////////////////////////////////////////////////////////			  	    
			  	  lookEventsButton.setOnClickListener(new OnClickListener() 
			        {
			        	
			        	@Override
			            public void onClick(View v) {
			                    
			        			Intent intent = new Intent(ChangeEventsScreen.this, EventsScreen.class);
			        			intent.putExtra("eventName", eventsSpinner.getSelectedItem().toString());
			        			intent.putExtra("blockID", blocksSpinner.getSelectedItem().toString());
			                    startActivity(intent);
			            }
			        });
			  	  
			  	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_events_screen, menu);
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
