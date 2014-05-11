package com.zhacky.app.examreviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.inmobi.commons.InMobi;
import com.inmobi.monetization.IMBanner;



public class MainActivity extends Activity {
	/** InMobi related **/
private IMBanner banner;
private final String PROPERTY_ID = "b3820ea1bf3840a19836b1b5658c6532";
private final String LOGTAG = "QUIZAPP";

	// -----------
	
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
					intent = new Intent(MainActivity.this,
							SearchableActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);

					break;
				case R.id.btnQuiz:
					intent = new Intent(MainActivity.this, QuizActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);

					break;
				case R.id.btnHighScores:
					intent = new Intent(MainActivity.this,
							HighScoresActivity.class);
					intent.putExtra("Username", "Zhack");
					startActivity(intent);

					break;
				default:
					break;
				}

			}
		};
		
		
		//Region - add listeners to buttons

		btnStudy.setOnClickListener(listener);
		btnQuiz.setOnClickListener(listener);
		btnHighScores.setOnClickListener(listener);

		//EndRegion

		/** Ads View **/
		try {
			
			InMobi.initialize(this, PROPERTY_ID);
			banner = (IMBanner)findViewById(R.id.banner);
			banner.loadBanner();
		} catch (Exception e) {
			Log.d(LOGTAG,"Error initializing banner - MainActivity.reason\n" + e.getMessage());
		return;
		}
		
		//---------
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	
	
	/** -- onResume and onDestroy (ads purposes) **/
	@Override
	protected void onResume() {
		super.onResume();
	  
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	   
	}
	
	/**      Banner Listeners***/
	
}
