package com.zhacky.app.examreviewer;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.zhacky.app.examreviewer.data.AppXMLParser;
import com.zhacky.app.examreviewer.model.Question;
import com.zhacky.app.examreviewer.utils.UIHelper;

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
	int qchar_count;
	List<Question> questions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		InitViews();
		//Toast.makeText(this, "Test10", Toast.LENGTH_LONG).show();
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

		userAns = UIHelper.getSelected(this, R.id.rgChoices);
		// if user didn't pick any choice
		if (userAns == 0) {
			Toast.makeText(this, "Please select one", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		// if user reaches the final question
		if (question_no + 1 == total_count) {
			UIHelper.setButtonText(this, R.id.btnConfirm, "Confirm");
		}
		// if User got the correct answer
		if (userAns == correctAns) {
			current_points = points;
			current_correct = 1;

		} else {
			current_points = 0;
			current_correct = 0;
		}
		// add the points
		userscore += current_points;
		total_correct += current_correct;
		// if questions reach the total
		if (question_no >= total_count) {
			finalizeScore();
		} else {
			question_no += 1;
			setQuestion(question_no);
		}
	}

	/**
	 * Finalizes the score and passes values to the EndQuizActivity Values
	 * passed: userscore, total_correct, fraction
	 */
	private void finalizeScore() {
		Intent intent = new Intent(this, EndQuizActivity.class);
		intent.putExtra("Score", userscore);
		intent.putExtra("Corrects", total_correct);
		double fraction;
		fraction = (double) total_correct / total_count;
		Toast.makeText(
				this,
				"Fraction: " + fraction + "\nCorrect: " + total_correct
						+ "\nCount: " + total_count, Toast.LENGTH_SHORT).show();
		intent.putExtra("Fraction", fraction);
		startActivity(intent);
		this.finish();
	}

	/**
	 * Sets the question and the choices based on the question number
	 * 
	 * @param question_number
	 *            - the incremented question_no value
	 */
	private void setQuestion(long question_number) {
		Log.i(LOGTAG, "Question No. " + question_number);
		UIHelper.clearSelection(this, R.id.rgChoices);

		if (question_number > total_count) {

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

	// --end coding--
}
