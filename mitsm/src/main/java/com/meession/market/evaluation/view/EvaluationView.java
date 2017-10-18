package com.meession.market.evaluation.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.meession.market.common.util.DateUtils;
import com.meession.market.evaluation.entity.Evaluation;
import com.meession.market.evaluation.service.IEvaluationService;

@ManagedBean
@ViewScoped
public class EvaluationView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{evaluationService}")
	private IEvaluationService evaluationService;

	/**
	 * 保存评价的方法
	 */
	public void saveEvaluation(Evaluation evaluation) {
		evaluationService.addEvaluation(evaluation);
	}

	/**
	 * 通过兼职的id得到兼职的平均星级
	 * 
	 * @param id
	 *            兼职的id
	 * @return 返回兼职的综合平均星级
	 */
	public double getTheParttimeStaffAverageGrade(long id) {
		double result = 0;
		double grade = 0;
		// 放一个兼职的Evaluation集合
		List<Evaluation> evaluationOfPTF = new ArrayList<Evaluation>();
		for (Evaluation evaluation : evaluationService.findAll()) {
			if (evaluation.getType().equals(Evaluation.PARTTIMESTAFF) && evaluation.getEvaluatedObjectId() == id) {
				evaluationOfPTF.add(evaluation);
				grade += evaluation.getGrade();
			}
		}
		long length = evaluationOfPTF.size();
		if (length == 0)
			result = 0;
		else
			result = DateUtils.changeDouble(grade / length);
		return result;
	}
	

	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

}
