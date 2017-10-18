package com.meession.market.revisit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.IDaoSupport;
import com.meession.market.revisit.entity.Revisit;

@Repository
public interface IRevisitDao extends IDaoSupport{
	public void add(Revisit revisit);
	public void delete(long id);
	public void delete(List<Long> ids);
	public void update(Revisit revisit);
	public Revisit find(long id);
	public List<Revisit> findAll();
}
