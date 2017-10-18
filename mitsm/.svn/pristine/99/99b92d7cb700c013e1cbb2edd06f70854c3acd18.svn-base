package com.meession.market.bulletin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.bulletin.dao.IBulletinDao;
import com.meession.market.bulletin.entity.Bulletin;
import com.meession.market.bulletin.service.IBulletinService;

@Repository
public class BulletinService implements IBulletinService {

	@Resource
	private IBulletinDao bulletinDao;
	
	@Override
	public void saveBulletin(Bulletin bulletin) {
		bulletinDao.addBulletin(bulletin);
	}

	@Override
	public void deleteBulletin(Bulletin bulletin) {
		bulletinDao.delete(bulletin);
		
	}
	
	@Override
	public void deleteBulletin(long id) {
		bulletinDao.delete(id);
		
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		bulletinDao.deleteBatch(ids);
	}

	
	public void updateBulletin(Bulletin bulletin){
		bulletinDao.update(bulletin);
	}
	
	public void find(long id){
		bulletinDao.find(id);
	}
	public List<Bulletin> findAll(){
		return bulletinDao.findAll();
	}
}
