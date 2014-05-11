package com.zhacky.app.examreviewer;

import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhacky.app.examreviewer.data.AppDataSource;
import com.zhacky.app.examreviewer.data.LessonsXMLParser;
import com.zhacky.app.examreviewer.model.Item;

public class SearchableActivity extends ListActivity {
	private final String LOGTAG = "QUIZAPP";
	AppDataSource datasource;
	List<Item> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		checkSearchIntent();
		loadItemList();
	}

	private void loadItemList() {
		Log.i(LOGTAG, "instantiating datasource - @SearchableActivity-loadItemList()");
		datasource = new AppDataSource(this);
		datasource.open();
		Log.i(LOGTAG, "datasource opened - @SearchableActivity-loadItemList()");
		try {
			items = datasource.findAll();
			if (items.size() == 0) {
				createData();
				items = datasource.findAll();
				Log.i(LOGTAG, "find All items - @SearchableActivity-loadItemList()\nItem count: " + items.size());
			}
		} 
		catch (SQLiteException e) {
			Log.i(LOGTAG, "Error creating Data:\n" + e.getMessage());
		}
		catch (Exception e) {
			Log.i(LOGTAG, "Error creating Data:\n" + e.getMessage());
		}
	
		Log.i(LOGTAG, "Refreshing Display - @SearchableActivity.loadItemList");
		refreshDisplay();
	}

	/**
	 * Creates a temporary database data - only used if an existing database is not found
	 */
	private void createData() {
		Log.i(LOGTAG, "creating data- @SearchableActivity-loadItemList()");
		LessonsXMLParser parser = new LessonsXMLParser();
		Log.i(LOGTAG, "parsing - @SearchableActivity-loadItemList()");
		List<Item> items = parser.parseXML(this);
		
		for (Item item : items) {
			Log.i(LOGTAG, "creating item...\n" + item.getTitle().toString() + "\n - @SearchableActivity-loadItemList()");
			datasource.create(item);
		}
		
	}
	
	
	private void checkSearchIntent() {
		// Check search intent
		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doSearch(query);
		}

	}

	private void doSearch(String query) {

	}

	@Override
	protected void onPause() {
		super.onPause();
		 datasource.close();
	}

	@Override
	protected void onResume() {
		super.onResume();
		 datasource.open();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		 switch (item.getItemId()) {
//		 case R.id.list_all:
//		 items = datasource.findAll();
//		 refreshDisplay();
//		 break;
//		
//		 case R.id.list_general:
//		 items = datasource.findFiltered("units = 1", "title ASC");
//		 refreshDisplay();
//		 break;
//		 case R.id.list_english:
//		 items = datasource.findFiltered("units = 2", "title ASC");
//		 refreshDisplay();
//		 break;
//		 case R.id.list_math:
//		 items = datasource.findFiltered("units = 3", "title ASC");
//		 refreshDisplay();
//		 break;
//		
//		 default:
//		 break;
//		 }
		return super.onOptionsItemSelected(item);
	}


	private void refreshDisplay() {

		ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
				android.R.layout.simple_list_item_1, items);
		setListAdapter(adapter);
	}

	//
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Item item = items.get(position);
		Intent intent = new Intent(this, StudyActivity.class);
		intent.putExtra(".model.Item", item);
		startActivity(intent);
	}

	// --- end code ---//
}
