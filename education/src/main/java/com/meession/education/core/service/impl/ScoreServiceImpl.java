package com.meession.education.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meession.education.core.model.Score;
import com.meession.education.core.repository.ScoreRepository;
import com.meession.education.core.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreRepository scoreRepository;
	
	@Override
	public List<Score> listAllScore() {
		return scoreRepository.findAll();
	}

	@Override
	public void saveScore(Score score) {
		scoreRepository.save(score);
	}

	public ScoreRepository getScoreRepository() {
		return scoreRepository;
	}

	public void setScoreRepository(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}

}
