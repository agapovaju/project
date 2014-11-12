package com.example.testsfinal;




import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewEventScreen extends ActionBarActivity {

	DB db;
	SQLiteDatabase sqdb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event_screen);
		
		final DB db=new DB(this);
        final SQLiteDatabase sqdb=db.getWritableDatabase();
        Button newEventBut = (Button) findViewById(R.id.newEventButton);
        final EditText eventNameED= (EditText) findViewById(R.id.eventNameED);
        final EditText blockIDED= (EditText) findViewById(R.id.blockIDED);
        final ArrayList<String> events = new ArrayList<String>();
		final ArrayList<String> blocks = new ArrayList<String>();
		
        	         
        
        newEventBut.setOnClickListener(new OnClickListener() 
        {
        	
        	@Override
            public void onClick(View v) {
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
        	    
                Cursor cursorBlocks = sqdb.query(DB.TABLE_NAME_BLOCKS, new String[] {
          				DB.BLOCK_NUMBER}, 
          				null, // The columns for the WHERE clause
          				null, // The values for the WHERE clause
          				null, // don't group the rows
          				null, // don't filter by row groups
          				null // The sort order
          				);
          	    
          	    if (cursorBlocks.moveToFirst()) {
                      do {	            	
                          blocks.add(cursorBlocks.getString(0));	 
                      } while (cursorBlocks.moveToNext());
                  }
                  if (cursorBlocks != null && !cursorBlocks.isClosed()) {
                	  cursorBlocks.close();
                  }	
        		
        		if (events.contains(eventNameED.getText().toString())==false)
        			{
        				ContentValues cvEvents= new ContentValues();
        				cvEvents.put(DB.EVENT_NAME,eventNameED.getText().toString());
        				sqdb.insert(DB.TABLE_NAME_EVENTS, DB.EVENT_NAME, cvEvents);
        			}
        			if (blocks.contains(blockIDED.getText().toString())==false){
        				ContentValues cvBlocks= new ContentValues();
        				cvBlocks.put(DB.BLOCK_NUMBER,blockIDED.getText().toString());
        				sqdb.insert(DB.TABLE_NAME_BLOCKS, DB.BLOCK_NUMBER, cvBlocks);
        			}
        			Intent intent = new Intent(NewEventScreen.this, TestsScreen.class);
        			intent.putExtra("eventName", eventNameED.getText().toString());
        			intent.putExtra("blockID", blockIDED.getText().toString());
                    startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_event_screen, menu);
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
