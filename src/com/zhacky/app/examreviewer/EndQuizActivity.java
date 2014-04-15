package com.zhacky.app.examreviewer;

import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.os.Bundle;

public class EndQuizActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_endquiz);
		DisplayScores();
	}

	private void DisplayScores() {
		UIHelper.displayText(
				this,
				R.id.tvCorrectAnswers,
				String.valueOf(21));
		UIHelper.displayText(this, R.id.tvUserScore, String.valueOf(20));
//		UIHelper.displayText(
//				this,
//				R.id.tvCorrectAnswers,
//				String.valueOf(this.getIntent().getExtras()
//						.getInt("total_correct")));
//		UIHelper.displayText(this, R.id.tvUserScore, String.valueOf(this
//				.getIntent().getExtras().getInt("userscore")));

	}

	// --- end code ---
}
