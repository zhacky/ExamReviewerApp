package com.zhacky.app.examreviewer.utils;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

import com.zhacky.app.examreviewer.StudyActivity;

public class GestureUtility extends SimpleOnGestureListener {
	private static final String LOGTAG ="QUIZAPP";
	private static final int SWIPE_MIN_DISTANCE = 80;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 100;
	final StudyActivity activity;

	public GestureUtility(StudyActivity studyActivity) {
		activity = studyActivity;
	}

	// --- onFling
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		try {
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
				// vertical movement is not detected
				return false;
			}
			// horizontal swipe is listened for
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// right swipe here
				Toast.makeText(activity, "",
						Toast.LENGTH_SHORT).show();
				//activity.moveNextView();
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// left swipe here
				Toast.makeText(activity, "",
						Toast.LENGTH_SHORT).show();
				//activity.movePreviousView();

			}

		} catch (Exception e) {
Log.i(LOGTAG, e.getMessage());
		}
		return false;
	}

	// --- onDown
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}
}
