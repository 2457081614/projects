package com.meession.education.core.service;

import java.util.List;

import com.meession.education.core.model.Score;

public interface ScoreService {
	
	public List<Score> listAllScore();
	
	public void saveScore(Score score);
}
