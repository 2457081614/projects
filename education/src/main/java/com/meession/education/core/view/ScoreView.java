package com.meession.education.core.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.meession.education.core.service.ScoreService;

@ManagedBean
@ViewScoped
public class ScoreView {

	@ManagedProperty(value = "#{scoreService}")
	private ScoreService scoreService;

	
	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

}
