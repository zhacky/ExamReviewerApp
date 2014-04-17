package com.zhacky.app.examreviewer.utils;

import java.util.ArrayList;
import java.util.List;

import com.zhacky.app.examreviewer.model.Scorer;

public class HighScoreHelper {
List<Scorer> scores;


	public List<Scorer> getScores(){
		List<Scorer> _scores = new ArrayList<Scorer>();
		Scorer scorer = new Scorer(1,"Din",45);
		_scores.add(scorer);
		scorer.setId(2);
		scorer.setName("Zhack");
		scorer.setScore(25);
		_scores.add(scorer);
		
		
		return _scores;
	}

	

}
