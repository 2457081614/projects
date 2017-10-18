package com.meession.market.parttimestaff.service;

import java.util.List;

import com.meession.market.parttimestaff.entity.ParttimeStaff;

public interface IParttimeStaffService {
	public void addParttimeStaff(ParttimeStaff parttimeStaff);
	public void addParttimeStaffs(List<ParttimeStaff> list);
	public void deleteById(long id);
	public void deleteByIds(List<Long> ids);
	public void updateParttimeStaff(ParttimeStaff parttimeStaff);
	public List<ParttimeStaff> findAllParttimeStaffInfo();
	public ParttimeStaff findParttimeStaffByTel(String tel);
	public ParttimeStaff findParttimeStaffById(long id);
	public ParttimeStaff login(String tel,String password);
}
