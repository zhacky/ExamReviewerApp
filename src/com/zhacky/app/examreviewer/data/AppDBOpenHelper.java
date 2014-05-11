package com.zhacky.app.examreviewer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDBOpenHelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "QUIZAPP";

	private static final String DATABASE_NAME = "app.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_APP = "app";
	public static final String COLUMN_ID = "itemId";
	public static final String COLUMN_CODE = "itemCode";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESC = "description";
	public static final String COLUMN_UNITS = "units";
	public static final String COLUMN_IMAGE = "image";
	
	private static final String TABLE_CREATE = 
			"CREATE TABLE " + TABLE_APP + " (" +
			COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			COLUMN_CODE + " INTEGER, " +
			COLUMN_TITLE + " TEXT, " +
			COLUMN_DESC + " TEXT, " +
			COLUMN_UNITS + " NUMERIC, " +
			COLUMN_IMAGE + " TEXT " +
			")";
			
	
	public AppDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table has been created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP);
		onCreate(db);
	}

}


