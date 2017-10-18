package com.meession.market.parttimestaff.dao;


import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.parttimestaff.entity.ParttimeStaff;

public class ParttimeStaffDaoTest extends AbstractSpringTest {
	@Resource
	private IParttimeStaffDao parttimeStaffDao;

	@Test
	public void testAddParttimeStaff() {
		for (int i = 0; i < 200; i++) {
			ParttimeStaff parttimeStaff = ParttimeStaff.mock();

			parttimeStaffDao.save(parttimeStaff);

		}
	}

//	@Test
//	public void testDeleteParttimeStaff() {
//		ParttimeStaff parttimeStaff = parttimeStaffDao
//				.findParttimeStaffByTel("14508352901");
//
//		parttimeStaffDao.delete(parttimeStaff);
//	}
//    @Test
//	public void testFindAllParttimeStaffInfo() {
//		List<ParttimeStaff> parttimeStaffs = parttimeStaffDao.findAll();
//		for (ParttimeStaff parttimeStaff : parttimeStaffs) {
//			System.out.println(parttimeStaff);
//		}
//	}

}
