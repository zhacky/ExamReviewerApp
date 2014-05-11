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
	// public class name - ex. AppDataSource
	public static final String LOGTAG = "QUIZAPP";

	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	


	// step 1 - define a constant array of strings
	private static final String[] allColumns = { AppDBOpenHelper.COLUMN_ID,
			AppDBOpenHelper.COLUMN_TITLE, AppDBOpenHelper.COLUMN_DESC,
			AppDBOpenHelper.COLUMN_UNITS, AppDBOpenHelper.COLUMN_IMAGE };

	public AppDataSource(Context context) {
		dbhelper = new AppDBOpenHelper(context);
		
	}

	public void open() {
		Log.i(LOGTAG, "Database opened - @AppDataSource.open()");
	
		try {
			database = dbhelper.getWritableDatabase();
		} catch (Exception e) {
			Log.d(LOGTAG,"Error getting Writable Database:\n" + e.getMessage());
		}
		
	}

	public void close() {
		Log.i(LOGTAG, "Database closed - @AppDataSource.close()");
		dbhelper.close();
	}

	// step 2 - create a public method that inserts a passed data into the
	// database
	public Item create(Item item) {
		ContentValues values = new ContentValues();
		values.put(AppDBOpenHelper.COLUMN_CODE, item.getId());
		values.put(AppDBOpenHelper.COLUMN_TITLE, item.getTitle());
		values.put(AppDBOpenHelper.COLUMN_DESC, item.getDescription());
		values.put(AppDBOpenHelper.COLUMN_UNITS, item.getUnits());
		values.put(AppDBOpenHelper.COLUMN_IMAGE, item.getImage());
		long insertid = database
				.insert(AppDBOpenHelper.TABLE_APP, null, values);
		Log.d(LOGTAG,"created Item with id: " + insertid + "\n @AppDataSource.create(Item item)");
		//item.setId(insertid);
		return item;
	}

	// step 3 - create a public method to retrieve a list of values
	public List<Item> findAll() {

		Cursor cursor = database.query(AppDBOpenHelper.TABLE_APP, allColumns,
				null, null, null, null, null);
		
		Log.i(LOGTAG, "Returned " + String.valueOf(cursor.getCount())
				+ " rows @findAll-AppdataSource");

		List<Item> items = cursorToList(cursor);
		return items;
	}

	public List<Item> findFiltered(String selection, String orderBy) {

		Cursor cursor = database.query(AppDBOpenHelper.TABLE_APP, allColumns,
				selection, null, null, null, orderBy);

		Log.i(LOGTAG, "Returned " + String.valueOf(cursor.getCount())
				+ " rows @findFiltered-AppdataSource");

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
				Log.i(LOGTAG,
						"Populating List<Item> items\n - @AppDataSource.cursorToList()");
				items.add(item);
			}
		}
		return items;
	}

	/**
	 * Finds an item from database using an id as parameter
	 * 
	 * @param currentId
	 *            - Long - id of the item to search
	 * @returns Item item
	 */
//	public Item findItem(long currentId, int position) {
//		Cursor cursor2 = database.query(AppDBOpenHelper.TABLE_APP, allColumns,
//				null, null, null, null, null);
//		Log.i(LOGTAG, "Returned " + String.valueOf(cursor2.getCount())
//				+ " rows @findItem-AppdataSource");
//
//		if (cursor2.getCount() > 0) {
//			Item item = new Item();
//
//			if (position > 0) {
//				boolean itemFound = false;
//				while (cursor2.moveToNext()) {
//					if (itemFound) {
//						item.setId(cursor2.getLong(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_ID)));
//						item.setTitle(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_TITLE)));
//						item.setDescription(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_DESC)));
//						item.setUnits(cursor2.getInt(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_UNITS)));
//						item.setImage(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_IMAGE)));
//						Log.i(LOGTAG, "Returning item.title: "
//								+ item.getTitle().toString()
//								+ "\n@findItem-AppdataSource");
//					}
//					if (currentId == cursor2.getLong(cursor2
//							.getColumnIndex(AppDBOpenHelper.COLUMN_ID))) {
//						itemFound = true;
//					} else {
//						itemFound = false;
//					}
//				}
//			} else if (position < 0) {
//				boolean itemFound = false;
//				while (cursor2.moveToNext()) {
//
//					if (currentId == cursor2.getLong(cursor2
//							.getColumnIndex(AppDBOpenHelper.COLUMN_ID))) {
//						itemFound = true;
//					}
//					if(!itemFound){
//						item.setId(cursor2.getLong(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_ID)));
//						item.setTitle(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_TITLE)));
//						item.setDescription(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_DESC)));
//						item.setUnits(cursor2.getInt(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_UNITS)));
//						item.setImage(cursor2.getString(cursor2
//								.getColumnIndex(AppDBOpenHelper.COLUMN_IMAGE)));
//						Log.i(LOGTAG, "Returning item.title: " + item.getTitle().toString()
//								+ "\n@findItem-AppdataSource");
//						cursor2.moveToNext();
//						
//					} 
//				}
//
//			}
//		
//			return item;
//		
//		}
//
//		else {
//			Log.i(LOGTAG, "Returning null @findItem-AppdataSource");
//			cursor2.close();
//			return null;
//		}
//		
//	}

}
