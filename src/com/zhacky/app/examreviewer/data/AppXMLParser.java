package com.zhacky.app.examreviewer.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.zhacky.app.examreviewer.R;
import com.zhacky.app.examreviewer.model.Question;;

public class AppXMLParser {
private static final String LOGTAG = "QUIZAPP";
	
	private static final String QUESTION_ID = "itemId";
	private static final String QUESTION_QUESTION = "question";
	private static final String QUESTION_CHOICEA = "choiceA";
	private static final String QUESTION_CHOICEB = "choiceB";
	private static final String QUESTION_CHOICEC = "choiceC";
	private static final String QUESTION_CHOICED = "choiceD";
	private static final String QUESTION_ANSWER = "answer";
	private static final String QUESTION_POINTS = "points";
	private static final String QUESTION_DIFFICULTY = "difficulty";
	private static final String QUESTION_CATEGORY = "category";
	
	private Question currentQuestion  = null;
	private String currentTag = null;
	List<Question> questions = new ArrayList<Question>();

	public List<Question> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.getResources().openRawResource(R.raw.exam_cs);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return questions;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentQuestion != null && currentTag != null) {
			if (currentTag.equals(QUESTION_ID)) {
				Long id = Long.parseLong(xmlText);
				currentQuestion.setId(id);
			} 
			else if (currentTag.equals(QUESTION_QUESTION)) {
				currentQuestion.setQuestion(xmlText);
			}
			else if (currentTag.equals(QUESTION_CHOICEA)) {
				currentQuestion.setChoiceA(xmlText);
			}
			else if (currentTag.equals(QUESTION_CHOICEB)) {
				currentQuestion.setChoiceB(xmlText);
			}
			else if (currentTag.equals(QUESTION_CHOICEC)) {
				currentQuestion.setChoiceC(xmlText);
			}
			else if (currentTag.equals(QUESTION_CHOICED)) {
				currentQuestion.setChoiceD(xmlText);
			}
			else if (currentTag.equals(QUESTION_ANSWER)) {
				int Ans = Integer.parseInt(xmlText);
				currentQuestion.setAnswer(Ans);
			}
			else if (currentTag.equals(QUESTION_POINTS)) {
				int points = Integer.parseInt(xmlText);
				currentQuestion.setPoints(points);
			}
			else if (currentTag.equals(QUESTION_DIFFICULTY)) {
				int difficulty = Integer.parseInt(xmlText);
				currentQuestion.setDifficulty(difficulty);
			}
			else if (currentTag.equals(QUESTION_CATEGORY)) {
				currentQuestion.setCategory(xmlText);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("item")) {
			currentQuestion = new Question();
			questions.add(currentQuestion);
		}
		else {
			currentTag = name;
		}
	}
}
