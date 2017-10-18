package com.meession.market.staff.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.staff.dao.IStaffDao;
import com.meession.market.staff.entity.Staff;

@Repository
public class StaffDao extends DaoSupport implements IStaffDao {

	/**
	 * 批量删除
	 */
	@Override
	public void deleteBatch(List<Long> ids) {
		getDaoTemplate().delete(Staff.class, ids);
	}

	/**
	 * 更新员工
	 */
	@Override
	public void updateStaff(Staff staff) {
		getDaoTemplate().update(staff);
	}

	/**
	 * 添加员工
	 */
	@Override
	public void addStaff(Staff staff) {
		getDaoTemplate().save(staff);
	}

	/**
	 * 通过电话删除员工
	 */
	@Override
	public void deleleByTel(String tel) {
		long id = getIdByTel(tel);
		getDaoTemplate().delete(Staff.class, id);
	}
	
	

	/**
	 * 通过tel找到指定的员工
	 */
	@Override
	public Staff findStaffByTel(String tel) {
		String sql = "select entity from Staff entity where entity.tel=?";
		return getDaoTemplate().find(Staff.class, sql, tel);
	}

	@Override
	public Staff findStaffById(long id) {
		String sql = "select entity from Staff entity where entity.id=?";
		return getDaoTemplate().find(Staff.class, sql, id);
	}
	/**
	 * 列出所有的员工
	 */
	@Override
	public List<Staff> findAll() {
		return getDaoTemplate().list(Staff.class);
	}

	/**
	 * 通过tel得到相应员工的id
	 * 
	 * @param tel
	 * @return
	 */
	public long getIdByTel(String tel) {
		Staff staff = findStaffByTel(tel);
		return staff.getId();
	}

}
