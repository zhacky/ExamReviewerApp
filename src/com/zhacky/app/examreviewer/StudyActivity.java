package com.zhacky.app.examreviewer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.zhacky.app.examreviewer.data.AppDataSource;
import com.zhacky.app.examreviewer.model.Item;
import com.zhacky.app.examreviewer.utils.GestureUtility;
import com.zhacky.app.examreviewer.utils.UIHelper;

public class StudyActivity extends Activity implements OnClickListener {
	Item item;
	Item displayItem;
	private final String LOGTAG = "QUIZAPP";
	AppDataSource datasource;

	private GestureDetector gestureDetector;
	OnTouchListener gestureListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study);
		
		/**            load item             **/
		Bundle b = getIntent().getExtras();
		item = b.getParcelable(".model.Item");
		displayItem = item;
		Log.d(LOGTAG,"loadItemToDisplay\n");
		loadItemToDisplay();

		/**    ---load Swipe Listener---     **/ 
		Log.d(LOGTAG,"InitListeners()\n");
		InitListeners();

	}//-----------end onCreate-------------

	

	/**
	 * After flinging to the right
	 */
//	public void moveNextView() {
//		long currentId = (item.getId());
//		Log.i(LOGTAG,
//				"Finding the item (right)\n - @StudyActivity.moveNextView()");
//		try {
//			displayItem = datasource.findItem(currentId, 1);
//		} catch (Exception e) {
//			Log.d(LOGTAG,
//					"Error finding item\n" + e.getMessage() + "- @StudyActivity.moveNextView()");
//			displayItem = null;
//		}
//		if (displayItem != null) {
//			Log.i(LOGTAG, "display Title (right)\n -"
//					+ displayItem.getTitle().toString()
//					+ "\n@StudyActivity.moveNextView()");
//			loadItemToDisplay();
//		} else {
//			displayItem = item;
//			loadItemToDisplay();
//		}
//	}
//
//	/**
//	 * After flinging to the left
//	 */
//	public void movePreviousView() {
//		long currentId = (item.getId());
//
//		try {
//			displayItem = datasource.findItem(currentId, -1);
//		} catch (Exception e) {
//			Log.d(LOGTAG,
//					"Error finding the item (left)\n"+ e.getMessage() +"\n- @StudyActivity.movePreviousView()");
//			displayItem = null;
//		}
//		if (displayItem != null) {
//			Log.i(LOGTAG, "display Title (left)\n -"
//					+ displayItem.getTitle().toString()
//					+ "\n@StudyActivity.moveNextView()");
//			loadItemToDisplay();
//		} else {
//
//			displayItem = item;
//			loadItemToDisplay();
//		}
//	}
	

	/**
	 * Display the current Item
	 */
	private void loadItemToDisplay() {
		String description = item.getDescription();
		if (item.getImage() != "none") {

		}
		UIHelper.displayText(this, R.id.tvItemDesc, description);
	}

	// Initialize the Listeners
		private void InitListeners() {
			gestureDetector = new GestureDetector(this, new GestureUtility(this));
			gestureListener = new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					return gestureDetector.onTouchEvent(event);
				}
			};
			UIHelper.setTouchListener(this, R.id.tvItemDesc, gestureListener);

		}
		
	@Override
	public void onClick(View v) {

	}

	@Override
	public boolean onSearchRequested() {

		return super.onSearchRequested();
	}

	// --- end code ---///
}
