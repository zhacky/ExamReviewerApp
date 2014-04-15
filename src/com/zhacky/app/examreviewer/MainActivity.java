package com.zhacky.app.examreviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitViews();
	}

	private void InitViews() {
		/** Study Button **/
		Button btnStudy = (Button) findViewById(R.id.btnStudy);
		/** Quiz Button **/
		Button btnQuiz = (Button) findViewById(R.id.btnQuiz);
		/** High Scores Button **/
		Button btnHighScores = (Button) findViewById(R.id.btnHighScores);
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent;
				switch (v.getId()) {
				case R.id.btnStudy:
					intent = new Intent(MainActivity.this, StudyActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);
					break;
				case R.id.btnQuiz:
					intent = new Intent(MainActivity.this, QuizActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);
					break;
				case R.id.btnHighScores:
					intent = new Intent(MainActivity.this, HighScoresActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);
					break;
				default:
					break;
				}
				

			}
		};
		btnStudy.setOnClickListener(listener);
		btnQuiz.setOnClickListener(listener);
		btnHighScores.setOnClickListener(listener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
