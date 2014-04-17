package com.zhacky.app.examreviewer.data;

import java.util.ArrayList;
import java.util.List;

import com.zhacky.app.examreviewer.model.Item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDataSource {
	//public class name - ex. AppDataSource
	public static final String LOGTAG = "QUIZAPP";

	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	// step 1 - define a constant array of strings
	private static final String[] allColumns = { AppDBOpenHelper.COLUMN_ID,
			AppDBOpenHelper.COLUMN_TITLE, AppDBOpenHelper.COLUMN_DESC,AppDBOpenHelper.COLUMN_UNITS,
			AppDBOpenHelper.COLUMN_IMAGE };

	public AppDataSource(Context context) {
		dbhelper = new AppDBOpenHelper(context);
	}

	public void open() {
		Log.i(LOGTAG, "Database opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}

	// step 2 - create a public method that inserts a passed data into the database 
	public Item create(Item item) {
		ContentValues values = new ContentValues();
		values.put(AppDBOpenHelper.COLUMN_TITLE, item.getTitle());
		values.put(AppDBOpenHelper.COLUMN_DESC, item.getDescription());
		values.put(AppDBOpenHelper.COLUMN_UNITS, item.getUnits());
		values.put(AppDBOpenHelper.COLUMN_IMAGE, item.getImage());
		long insertid = database.insert(AppDBOpenHelper.TABLE_APP, null,
				values);
		item.setId(insertid);
		return item;
	}

	// step 3 - create a public method to retrieve a list of values
	public List<Item> findAll() {

		Cursor cursor = database.query(AppDBOpenHelper.TABLE_APP,
				allColumns, null, null, null, null, null);
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		
		List<Item> items = cursorToList(cursor);
		return items;
	}
	
	public List<Item> findFiltered(String selection, String orderBy) {
		
		Cursor cursor = database.query(AppDBOpenHelper.TABLE_APP,
				allColumns, selection, null, null, null, orderBy);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		
		List<Item> items = cursorToList(cursor);
		return items;
	}

	private List<Item> cursorToList(Cursor cursor) {
		List<Item> items = new ArrayList<Item>();
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Item item = new Item();
				item.setId(cursor.getLong(cursor
						.getColumnIndex(AppDBOpenHelper.COLUMN_ID)));
				item.setTitle(cursor.getString(cursor
						.getColumnIndex(AppDBOpenHelper.COLUMN_TITLE)));
				item.setDescription(cursor.getString(cursor
						.getColumnIndex(AppDBOpenHelper.COLUMN_DESC)));
				item.setUnits(cursor.getInt(cursor
						.getColumnIndex(AppDBOpenHelper.COLUMN_UNITS)));
				item.setImage(cursor.getString(cursor
						.getColumnIndex(AppDBOpenHelper.COLUMN_IMAGE)));
				items.add(item);
			}
		}
		return items;
	}


}
