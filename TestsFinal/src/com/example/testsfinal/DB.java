package com.example.testsfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DB extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "tests.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME_MAIN="test_records";
	public static final String TABLE_NAME_EVENTS="events_names";
	public static final String TABLE_NAME_BLOCKS="blocks_numbers";
	public static final String UID="_id";
	public static final String TEST_TYPE="Тип";
	public static final String START="Начало";
	public static final String STOP="Конец";
	public static final String GPS="Координаты";
	public static final String TEST_NAME="Испытание";
	public static final String BLOCK_NUMBER="Блок";
	public static final String COMMENTS="Комментарии";
	public static final String EVENT_NAME="Испытание";
	
	
	private static final String SQL_CREATE_ENTRIES_MAIN_TABLE="CREATE TABLE " 
													+ TABLE_NAME_MAIN 
													+ " ( " 
													+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
													+ TEST_TYPE + " VARCHAR(255), "
													+ START + " VARCHAR(255), "
													+ STOP + " VARCHAR(255), "
													+ GPS + " VARCHAR(255), "
													+ EVENT_NAME + " VARCHAR(255), "
													+ BLOCK_NUMBER + " VARCHAR(255), "
													+ COMMENTS + " VARCHAR(255));";
	
	private static final String SQL_CREATE_ENTRIES_EVENT_TABLE ="CREATE TABLE " 
																+ TABLE_NAME_EVENTS 
																+ " ( " 
																+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
																+ EVENT_NAME +  " VARCHAR(255));";
	
	private static final String SQL_CREATE_ENTRIES_BLOCKS ="CREATE TABLE " 
															+ TABLE_NAME_BLOCKS
															+ " ( " 
															+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"															
															+ BLOCK_NUMBER + " VARCHAR(255));";
																
	
			
	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_ENTRIES_MAIN_TABLE);
		db.execSQL(SQL_CREATE_ENTRIES_EVENT_TABLE);
		db.execSQL(SQL_CREATE_ENTRIES_BLOCKS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
