package com.zhacky.app.examreviewer;

import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EndQuizActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_endquiz);
		DisplayScores();
		DisplayRank();
		InitNameSave();
	}

	private void InitNameSave() {
		Button btnSave = (Button) findViewById(R.id.btnSaveName);
		Button btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btnSaveName:
					clickedSave();
					break;
				case R.id.btnTryAgain:
					clickedTryAgain();
					break;

				default:
					break;
				}

			}
		};

		btnSave.setOnClickListener(listener);
		btnTryAgain.setOnClickListener(listener);
	}

	private void clickedSave() {

	}

	private void clickedTryAgain() {

	}

	private void DisplayRank() {
		double fraction = getIntent().getExtras().getDouble("Fraction");
		String rank = "Unqualified";

		if (0.8 <= fraction && fraction < 1) {
			rank = "Passer";
		} else if (fraction >= 1) {
			rank = "Topper";
		} else {
			rank = "Unqualified";
		}
		UIHelper.displayText(this, R.id.tvRank, rank);

	}

	private void DisplayScores() {

		String userscore = String.valueOf(getIntent().getExtras().getInt(
				"Score"));
		String totalcorrect = String.valueOf(getIntent().getExtras().getInt(
				"Corrects"));
		UIHelper.displayText(this, R.id.tvCorrectAnswers, totalcorrect);
		UIHelper.displayText(this, R.id.tvUserScore, userscore);

	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	// --- end code ---
}
