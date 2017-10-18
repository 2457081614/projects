package com.meession.market.bulletin.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.bulletin.entity.Bulletin;

@Repository
public interface IBulletinService {
	public void saveBulletin(Bulletin bulletin);
	public void deleteBulletin(Bulletin bulletin);
	public void deleteBulletin(long id);
	public void deleteBatch(List<Long> ids);
	public void updateBulletin(Bulletin bulletin);
	public void find(long id);
	public List<Bulletin> findAll();
}
