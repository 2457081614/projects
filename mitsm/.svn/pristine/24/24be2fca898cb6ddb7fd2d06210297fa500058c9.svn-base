package com.meession.market.bulletin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.bulletin.dao.IBulletinDao;
import com.meession.market.bulletin.entity.Bulletin;
import com.meession.market.common.support.dao.DaoSupport;

@Repository
public class BulletinDao extends DaoSupport implements IBulletinDao{
	
	public void addBulletin(Bulletin bulletin){
		getDaoTemplate().save(bulletin);
	}
	public void delete(Bulletin bulletin){
		getDaoTemplate().delete(bulletin);	
	}
	public void delete(long id){
		getDaoTemplate().delete(Bulletin.class, id);
	}
	public void deleteBatch(List<Long> ids){
		getDaoTemplate().delete(Bulletin.class, ids);
	}
	public void update(Bulletin bulletin){
		getDaoTemplate().update(bulletin);
	}
	public void find(long id){
		getDaoTemplate().find(Bulletin.class , id);
	}
	public List<Bulletin> findAll(){
		return getDaoTemplate().list(Bulletin.class);
	}

}
