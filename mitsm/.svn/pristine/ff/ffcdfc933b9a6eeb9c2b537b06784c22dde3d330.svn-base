package com.meession.market.parttimestaff.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.parttimestaff.dao.IParttimeStaffDao;
import com.meession.market.parttimestaff.entity.ParttimeStaff;

@Repository
public class ParttimeStaffDao extends DaoSupport implements IParttimeStaffDao {

	@Override
	public void save(ParttimeStaff parttimeStaff) {
		getDaoTemplate().save(parttimeStaff);

	}

	@Override
	public void delete(long id) {
              getDaoTemplate().delete(ParttimeStaff.class, id);
	}

	@Override
	public void delete(List<Long> ids) {
		getDaoTemplate().delete(ParttimeStaff.class, ids);
	}

	@Override
	public void update(ParttimeStaff parttimeStaff) {
		getDaoTemplate().update(parttimeStaff);
	}
	
	
	
	public ParttimeStaff findById(long id){
		return getDaoTemplate().find(ParttimeStaff.class, id);
	}

	@Override
	public ParttimeStaff findParttimeStaffByTel(String tel) {
		String sql = "select entity from ParttimeStaff entity where entity.tel = ?";

		return getDaoTemplate().find(ParttimeStaff.class, sql, tel);

	}

	@Override
	public List<ParttimeStaff> findAll() {
		
		return getDaoTemplate().list(ParttimeStaff.class);
	}

	
}
