package com.meession.market.parttimestaff.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meession.market.common.util.Md5;
import com.meession.market.parttimestaff.dao.IParttimeStaffDao;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.parttimestaff.service.IParttimeStaffService;

@Service
public class ParttimeStaffService implements IParttimeStaffService {

	@Resource
	private IParttimeStaffDao parttimeStaffDao;

	@Override
	public void addParttimeStaff(ParttimeStaff parttimeStaff) {
		parttimeStaffDao.save(parttimeStaff);
	}

	public void addParttimeStaffs(List<ParttimeStaff> list) {
		if (list != null) {
			for (ParttimeStaff p : list) {
				parttimeStaffDao.save(p);
			}
		}
	}

	@Override
	public void deleteById(long id) {
		parttimeStaffDao.delete(id);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		parttimeStaffDao.delete(ids);
	}

	@Override
	public void updateParttimeStaff(ParttimeStaff parttimeStaff) {
		parttimeStaffDao.update(parttimeStaff);
	}

	@Override
	public List<ParttimeStaff> findAllParttimeStaffInfo() {
		return parttimeStaffDao.findAll();
	}

	@Override
	public ParttimeStaff findParttimeStaffByTel(String tel) {
		return parttimeStaffDao.findParttimeStaffByTel(tel);
	}
	

	@Override
	public ParttimeStaff findParttimeStaffById(long id) {
		return parttimeStaffDao.findById(id);
	}

	public ParttimeStaff login(String tel, String password) {
		if (tel == null || "".equals(tel.trim()) || password == null || "".equals(password)) {
			return null;
		} else {
			ParttimeStaff parttimeStaff = parttimeStaffDao.findParttimeStaffByTel(tel);
			//if (parttimeStaff == null || !password.equals(parttimeStaff.getPassword())) {
			if (parttimeStaff == null || !Md5.checkPassword(password, parttimeStaff.getPassword())) {
				return null;
			//} else if (password.equals(parttimeStaff.getPassword())) {
			} else if (Md5.checkPassword(password, parttimeStaff.getPassword())) {
				return parttimeStaff;
			}
			return null;
		}
	}


}
