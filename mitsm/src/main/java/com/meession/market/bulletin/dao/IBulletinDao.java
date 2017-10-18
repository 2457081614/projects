package com.meession.market.bulletin.dao;

import java.util.List;

import com.meession.market.bulletin.entity.Bulletin;
import com.meession.market.common.support.dao.IDaoSupport;

public interface IBulletinDao extends IDaoSupport{
	public void addBulletin(Bulletin bulletin);
	public void delete(Bulletin bulletin);
	public void delete(long id);
	public void deleteBatch(List<Long> ids);
	public void update(Bulletin bulletin);
	public void find(long id);
	public List<Bulletin> findAll();
}
