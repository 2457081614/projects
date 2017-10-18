package com.meession.market.revisit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.revisit.dao.IRevisitDao;
import com.meession.market.revisit.entity.Revisit;

@Repository
public class RevisitDao extends DaoSupport implements IRevisitDao{

	@Override
	public void add(Revisit revisit) {
		getDaoTemplate().save(revisit);
	}

	@Override
	public void delete(long id) {
		getDaoTemplate().delete(id);
	}

	@Override
	public void delete(List<Long> ids) {
		getDaoTemplate().delete(ids);
	}

	@Override
	public void update(Revisit revisit) {
		getDaoTemplate().update(revisit);
	}

	@Override
	public Revisit find(long id) {
		return getDaoTemplate().find(Revisit.class, id);
	}

	@Override
	public List<Revisit> findAll() {
		return getDaoTemplate().list(Revisit.class);
	}
	
}
