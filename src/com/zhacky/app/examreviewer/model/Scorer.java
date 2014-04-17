package com.zhacky.app.examreviewer.model;

import java.util.Comparator;

public class Scorer implements Comparable<Scorer>{
	private long id;
	private String name;
	private int score;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int compareTo(Scorer comparedScorer) {
		int comparedScore = ((Scorer) comparedScorer).getScore();
		return comparedScore - this.getScore();
	}
	public Scorer(long id, String name, int score) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
	}
	public static Comparator<Scorer> ScorerComparator = new Comparator<Scorer>() {

		@Override
		public int compare(Scorer scorer1, Scorer scorer2) {
			
			return 0;
		}
	
	};
}
