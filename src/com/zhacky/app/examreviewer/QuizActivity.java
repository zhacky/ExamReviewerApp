package com.zhacky.app.examreviewer;

import java.util.List;
import com.zhacky.app.examreviewer.data.AppXMLParser;
import com.zhacky.app.examreviewer.model.Question;
import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private final String LOGTAG = "QUIZAPP";
	long question_no = 1;
	int total_count;
	int total_correct;
	int correctAns;
	int userAns;
	int current_points = 0;
	int current_correct = 0;
	int points;
	int difficulty;
	int userscore;
	List<Question> questions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		try {
			InitViews();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void InitViews() {
		AppXMLParser parser = new AppXMLParser();
		questions = parser.parseXML(this);
		total_count = questions.size();
		total_correct = 0;
		userscore = 0;
		UIHelper.setButtonText(this, R.id.btnConfirm, "Next");
		Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickFunction();

			}
		};
		btnConfirm.setOnClickListener(listener);
		setQuestion(question_no);
	}

	private void clickFunction() {
		if (UIHelper.getButtonText(this, R.id.btnConfirm).toString() == "Confirm") {
			finalizeScore();
		}
		userAns = UIHelper.getSelected(this, R.id.rgChoices);
		if (userAns == 0) {
			Toast.makeText(this, "Please select one", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (question_no >= total_count) {
			displayUpdate();
			UIHelper.setButtonText(this, R.id.btnConfirm, "Confirm");
			return;
		}
		if (userAns == correctAns) {
			current_points = points;
			current_correct += 1;

		} else {
			current_points = 0;
			current_correct = 0;
		}
		userscore += current_points;
		total_correct += current_correct;
		question_no += 1;
		setQuestion(question_no);
	}

	private void finalizeScore() {
		Intent intent = new Intent(this,EndQuizActivity.class);
		intent.putExtra("Score", userscore);
		intent.putExtra("Corrects", total_correct);
		startActivity(intent);
	}

	private void setQuestion(long question_number) {
		Log.i(LOGTAG, "Question No. " + question_number);
		UIHelper.clearSelection(this, R.id.rgChoices);

		if (question_number > total_count) {
			UIHelper.setButtonText(this, R.id.btnConfirm, "Confirm");
			// show results
			//displayUpdate();
		} else {

			for (Question question : questions) {
				if (question.getId() == question_number) {
					UIHelper.displayText(this, R.id.tvQuestion,
							question.getQuestion());
					UIHelper.setRadioText(this, R.id.radio1,
							question.getChoiceA());
					UIHelper.setRadioText(this, R.id.radio2,
							question.getChoiceB());
					UIHelper.setRadioText(this, R.id.radio3,
							question.getChoiceC());
					UIHelper.setRadioText(this, R.id.radio4,
							question.getChoiceD());
					correctAns = question.getAnswer();
					points = question.getPoints();
					difficulty = question.getDifficulty();
				}
			}
		}

	}

	private void displayUpdate() {
		String update = "Score: " + userscore + "\nCorrect: " + total_correct
				+ "/" + total_count;
		Toast.makeText(this, update, Toast.LENGTH_LONG).show();
	}

	// --end coding--
}
