package com.meession.market.revisit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.revisit.dao.IRevisitDao;
import com.meession.market.revisit.entity.Revisit;
import com.meession.market.revisit.service.IRevisitService;

@Repository
public class RevisitService implements IRevisitService {
	@Resource
	private IRevisitDao revisitDao;

	@Override
	public void addRevisitRecord(Revisit revisit) {
		revisitDao.add(revisit);
	}

	@Override
	public void deleteRevisitRecord(long id) {
		revisitDao.delete(id);
	}

	@Override
	public void deleteRevisitRecords(List<Long> ids) {
		revisitDao.delete(ids);
	}

	@Override
	public void updateRevisitRecord(Revisit revisit) {
		revisitDao.update(revisit);
	}

	@Override
	public Revisit findRevisitRecordById(long id) {
		return revisitDao.find(id);
	}

	@Override
	public List<Revisit> findAll() {
		return revisitDao.findAll();
	}
}
