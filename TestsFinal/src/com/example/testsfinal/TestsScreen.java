package com.example.testsfinal;


import java.text.SimpleDateFormat;
import java.util.Date;


import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TestsScreen extends ActionBarActivity implements LocationListener {

	DB db;
	SQLiteDatabase sqdb;
	TextView tv;
	LocationManager lm;
	String eventName;
	String blockID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tests_screen);
		
//Переменные**********************************************************************************************		
		final ToggleButton carTB=(ToggleButton) findViewById(R.id.carTB);
        final ToggleButton lorryTB=(ToggleButton) findViewById(R.id.lorryTB);
        final ToggleButton motoTB=(ToggleButton) findViewById(R.id.motoTB);
        final ToggleButton pedestTB=(ToggleButton) findViewById(R.id.pedTB);
        final ToggleButton digTB=(ToggleButton) findViewById(R.id.digTB);
        
        Button lookRecordsBut=(Button) findViewById(R.id.lookRecordsBut);
        
                    
        final EditText carComED= (EditText) findViewById(R.id.carCommentED);
        final EditText lorryComED= (EditText) findViewById(R.id.lorryCommentED);
        final EditText motoComED= (EditText) findViewById(R.id.motoCommentED);
        final EditText pedComED= (EditText) findViewById(R.id.pedCommentED);
        final EditText digComED= (EditText) findViewById(R.id.digCommentED);
        
        final DB db=new DB(this);
        final SQLiteDatabase sqdb=db.getWritableDatabase();
                
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        
        eventName=getIntent().getExtras().getString("eventName");
        blockID=getIntent().getExtras().getString("blockID");
        
      //Прослушиватели******************************************************************************************
        /////////////////////////////////////////////////////////////////////////////
        lookRecordsBut.setOnClickListener(new OnClickListener() 
        {
        	
        	@Override
            public void onClick(View v) {
                    
        			Intent intent = new Intent(TestsScreen.this, EventsScreen.class);
        			intent.putExtra("eventName", eventName);
        			intent.putExtra("blockID", blockID);
                    startActivity(intent);
            }
        });
        
        /////////////////////////////////////////////////////////////////////////////
        carTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() 
        {
        	String event_start;
            String event_stop;
            String comment;
            String gps;
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
        	{
                if (isChecked) {
                	gps=formatLocation(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                	event_start=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();                	
            	}
                else
                {
                    event_stop=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();            		
                	comment=carComED.getText().toString();
                	ContentValues cv= new ContentValues();
                	cv.put(DB.TEST_TYPE,carTB.getTextOn().toString());
                	cv.put(DB.START, event_start);
                	cv.put(DB.STOP, event_stop);
                	cv.put(DB.GPS, gps /*formatLocation(lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER))*/);
                	cv.put(DB.COMMENTS, comment);
                	cv.put(DB.BLOCK_NUMBER, blockID);
                	cv.put(DB.EVENT_NAME,eventName);
                	sqdb.insert(DB.TABLE_NAME_MAIN, DB.TEST_TYPE, cv);          	            	
                }
            }
        });
        
        ///////////////////////////////////////////////////////////////////////////////
        lorryTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() 
        {
        	String event_start;
            String event_stop;
            String comment;
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
        	{
                if (isChecked) 
                {
                	db.getWritableDatabase();
                    event_start= new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                }
                else
                {
                    event_stop=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                	comment=lorryComED.getText().toString();
                	ContentValues cv= new ContentValues();
                	cv.put(DB.TEST_TYPE,lorryTB.getTextOn().toString());
                	cv.put(DB.START, event_start);
                	cv.put(DB.STOP, event_stop);
                	cv.put(DB.BLOCK_NUMBER, blockID);
                	cv.put(DB.EVENT_NAME,eventName);
                	cv.put(DB.GPS, formatLocation(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
                	cv.put(DB.COMMENTS, comment);
                	sqdb.insert(DB.TABLE_NAME_MAIN, DB.TEST_TYPE, cv);
                	
            	}
            }
        });
        
        //////////////////////////////////////////////////////////////////////////////
        motoTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() 
        {
        	String event_start;
            String event_stop;
            String comment;
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
        	{
                if (isChecked) 
                {
                	db.getWritableDatabase();
                    event_start=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                }
                else
                {
                   event_stop=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
               	   comment=motoComED.getText().toString();
               	   ContentValues cv= new ContentValues();
               	   cv.put(DB.TEST_TYPE,motoTB.getTextOn().toString());
               	   cv.put(DB.START, event_start);
               	   cv.put(DB.STOP, event_stop);
               	   cv.put(DB.BLOCK_NUMBER, blockID);
               	   cv.put(DB.EVENT_NAME,eventName);
               	   cv.put(DB.GPS, formatLocation(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
               	   cv.put(DB.COMMENTS, comment);
               	   sqdb.insert(DB.TABLE_NAME_MAIN, DB.TEST_TYPE, cv);
               	   
                }
            }
        });
        
        ////////////////////////////////////////////////////////////////////////////////
        pedestTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	String event_start;
            String event_stop;
            String comment;
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
        	{
                if (isChecked) {
                	db.getWritableDatabase();
                    event_start=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                    
            	}else{ 
                    event_stop=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                	comment=pedComED.getText().toString();
                	ContentValues cv= new ContentValues();
                	cv.put(DB.TEST_TYPE,pedestTB.getTextOn().toString());
                	cv.put(DB.START, event_start);
                	cv.put(DB.STOP, event_stop);
                	cv.put(DB.BLOCK_NUMBER, blockID);
                	cv.put(DB.EVENT_NAME,eventName);
                	cv.put(DB.GPS, formatLocation(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
                	cv.put(DB.COMMENTS, comment);
                	sqdb.insert(DB.TABLE_NAME_MAIN, DB.TEST_TYPE, cv);
                	
            	}
            }
        });
        
        /////////////////////////////////////////////////////////////////////////////
        digTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() 
        {
        	String event_start;
            String event_stop;
            String comment;
            
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
            {
            	if (isChecked) 
            	{
            		event_start=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();            		
            	}
            	else
            	{
                    event_stop=new SimpleDateFormat("dd.MM.yy_HH:mm:ss").format(new Date()).toString();
                	comment=digComED.getText().toString();
                	ContentValues cv= new ContentValues();
                	cv.put(DB.TEST_TYPE,digTB.getTextOn().toString());
                	cv.put(DB.START, event_start);
                	cv.put(DB.STOP, event_stop);
                	cv.put(DB.BLOCK_NUMBER, blockID);
                	cv.put(DB.EVENT_NAME,eventName);
                	cv.put(DB.GPS, formatLocation(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
                	cv.put(DB.COMMENTS, comment);
                	sqdb.insert(DB.TABLE_NAME_MAIN, DB.TEST_TYPE, cv);                	
            	}
            }
        });
	}

	@Override
    public void onLocationChanged(Location location) 
    {
		
		
		if (location != null) 
		{
          showLocation(location);
		}
    }

    @Override
    public void onProviderDisabled(String provider) 
    {
    	checkEnabled();
    	showLocation(lm.getLastKnownLocation(provider));
    }

    @Override
    public void onProviderEnabled(String provider) 
    {
    	checkEnabled();
        showLocation(lm.getLastKnownLocation(provider));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) 
    {
    	    	
    }
    
    private void showLocation(Location location) 
    {
    	
    }
	 
    private String formatLocation(Location location)
    {
	    if (location == null)
	      return "";
	    return String.format(" шир=%1$.4f, дол=%2$.4f, точ=%3$.4f, время=%4$tF %4$tT",
	    					 location.getLatitude(), 
	    					 location.getLongitude(), 
	    					 location.getAccuracy(),
	    					 new Date(location.getTime()));
	}
    
    private void checkEnabled()
    {
    	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tests_screen, menu);
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
