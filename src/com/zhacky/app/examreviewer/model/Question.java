package com.zhacky.app.examreviewer.model;

public class Question {
private long id;	
private String question;
private String choiceA;
private String choiceB;
private String choiceC;
private String choiceD;
private int answer;
private int points;
private int difficulty;

public Question() {
	this.question = "Nothing";
	this.choiceA = "Nothing";
	this.choiceB = "Nothing";
	this.choiceC = "Nothing";
	this.choiceD = "Nothing";
	this.answer = 0;
	this.points = 0;
	this.difficulty = 0;
}

public Question(String question, String choiceA, String choiceB,
		String choiceC, String choiceD, int answer, int points, int difficulty) {
	super();
	this.question = question;
	this.choiceA = choiceA;
	this.choiceB = choiceB;
	this.choiceC = choiceC;
	this.choiceD = choiceD;
	this.answer = answer;
	this.points = points;
	this.difficulty = difficulty;
	}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getChoiceA() {
	return choiceA;
}
public void setChoiceA(String choiceA) {
	this.choiceA = choiceA;
}
public String getChoiceB() {
	return choiceB;
}
public void setChoiceB(String choiceB) {
	this.choiceB = choiceB;
}
public String getChoiceC() {
	return choiceC;
}
public void setChoiceC(String choiceC) {
	this.choiceC = choiceC;
}
public String getChoiceD() {
	return choiceD;
}
public void setChoiceD(String choiceD) {
	this.choiceD = choiceD;
}
public int getAnswer() {
	return answer;
}
public void setAnswer(int answer) {
	this.answer = answer;
}
public int getPoints() {
	return points;
}
public void setPoints(int points) {
	this.points = points;
}
public int getDifficulty() {
	return difficulty;
}
public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
}

}
