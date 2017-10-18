package com.meession.market.staff.service;

import java.util.List;
import java.util.function.ObjLongConsumer;

import org.springframework.stereotype.Repository;

import com.meession.market.staff.entity.Staff;

@Repository
public interface IStaffService {
	public void deleteBatch(List<Long> ids);

	public void addStaff(Staff staff);
	
	public void addStaffs(List<Staff> list);

	public void deleteByTel(String tel);

	public void updateStaff(Staff staff);

	public Staff getStaff(String tel);
	
	public Staff getStaff(long id);

	public List<Staff> getAllStaff();

	public Staff login(String tel,String password);
	
}
