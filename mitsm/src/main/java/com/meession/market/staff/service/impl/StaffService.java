package com.meession.market.staff.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.common.util.Md5;
import com.meession.market.staff.dao.IStaffDao;
import com.meession.market.staff.entity.Staff;
import com.meession.market.staff.service.IStaffService;

@Repository
public class StaffService implements IStaffService{
	@Resource
	public IStaffDao staffDao;

	/**
	 * 批量删除
	 */
	@Override
	public void deleteBatch(List<Long> ids) {
		staffDao.deleteBatch(ids);
	}

	/**
	 * 添加员工
	 */
	@Override
	public void addStaff(Staff staff) {
		staffDao.addStaff(staff);
	}

	
	public void addStaffs(List<Staff> list){
		if(list != null && list.size() > 0){
			for(Staff s : list){
				staffDao.addStaff(s);
			}
		}
	}
	
	/**
	 * 通过tel删除员工
	 */
	@Override
	public void deleteByTel(String tel) {
		staffDao.deleleByTel(tel);
	}

	/**
	 * 更新员工
	 */
	@Override
	public void updateStaff(Staff staff) {
		staffDao.update(staff);
	}

	/**
	 * 通过tel找到指定的员工
	 */
	@Override
	public Staff getStaff(String tel) {
		return staffDao.findStaffByTel(tel);
	}

	@Override
	public Staff getStaff(long id) {
		return staffDao.findStaffById(id);
	}
	
	/**
	 * 得到所有的员工的集合
	 */
	@Override
	public List<Staff> getAllStaff() {
		return staffDao.findAll();
	}

	public Staff login(String tel,String password){
		if(tel == null || "".equals(tel.trim()) 
				|| password == null || "".equals(password)){
			return null;
		}else{
			Staff staff = staffDao.findStaffByTel(tel);
			//if(staff == null || !password.equals(staff.getPassword())){
			if(staff == null || !Md5.checkPassword(password, staff.getPassword())){
				return null;
			//}else if(password.equals(staff.getPassword())){
			}else if(Md5.checkPassword(password, staff.getPassword())){
				return staff;
			}
			return null;
		}
	}
	
}
