package com.zhacky.app.examreviewer;

import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhacky.app.examreviewer.data.AppDataSource;
import com.zhacky.app.examreviewer.data.LessonsXMLParser;
import com.zhacky.app.examreviewer.model.Item;

public class SearchableActivity extends ListActivity {
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
		datasource = new AppDataSource(this);
		datasource.open();
		items = datasource.findAll();
		if (items.size() == 0) {
			createData();
			items = datasource.findAll();
		}

		refreshDisplay();
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
		// TODO Do some search function with the query

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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.list_all:
			items = datasource.findAll();
			refreshDisplay();
			break;

		case R.id.list_general:
			items = datasource.findFiltered("units = 1", "title ASC");
			refreshDisplay();
			break;
		case R.id.list_english:
			items = datasource.findFiltered("units = 2", "title ASC");
			refreshDisplay();
			break;
		case R.id.list_math:
			items = datasource.findFiltered("units = 3", "title ASC");
			refreshDisplay();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void createData() {
		LessonsXMLParser parser = new LessonsXMLParser();
		List<Item> items = parser.parseXML(this);

		for (Item item : items) {
			datasource.create(item);
		}

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
