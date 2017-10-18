package com.meession.market.staff.dao;


import java.util.List;

import com.meession.market.common.support.dao.IDaoSupport;
import com.meession.market.staff.entity.Staff;

public interface IStaffDao extends IDaoSupport{
	/**
	 * 批量删除
	 * @param ids
	 */
	public  void deleteBatch(List<Long> ids);
	/**
	 * 更新员工信息
	 * @param staff
	 */
	public void updateStaff(Staff staff);
	/**
	 * 添加一个员工到数据库
	 * @param staff
	 */
	public void addStaff(Staff staff);
	/**
	 * 根据电话删除指定员工
	 * @param tel
	 */
	public void deleleByTel(String tel);
	public Staff findStaffById(long id);
	/**
	 * 通过电话查询指定员工
	 * @param tel
	 * @return
	 */
	public Staff findStaffByTel(String tel);
	
	public List<Staff> findAll();
}
