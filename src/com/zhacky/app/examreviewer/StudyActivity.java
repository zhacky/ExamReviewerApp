package com.zhacky.app.examreviewer;

import com.zhacky.app.examreviewer.model.Item;
import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StudyActivity extends Activity {
	Item item;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_study);
	// load item
	Bundle b = getIntent().getExtras();
	item = b.getParcelable(".model.Item");
	String description = item.getDescription();
	if(item.getImage() !="none"){
		
	}
	UIHelper.displayText(this, R.id.tvItemDesc, description);
	
	// load Swipe Listener
	InitListeners();
	
	// Check search intent
	Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      doMySearch(query);
    }
}

// Initialize the Listeners
private void InitListeners() {
	
	
}

private void doMySearch(String query) {
	Toast.makeText(this, query, Toast.LENGTH_LONG).show();
	
}

@Override
	public boolean onSearchRequested() {
		
	
		return super.onSearchRequested();
	}

//---  end code ---///
}
