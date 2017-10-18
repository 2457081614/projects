package com.meession.market.staff.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.common.util.Md5;
import com.meession.market.staff.entity.Staff;

public class StaffDaoTest extends AbstractSpringTest{
	@Resource
	private IStaffDao staffDao;
	
	@Test
	public void testAddStaff(){
		for(int i=0;i<100;i++){
			staffDao.addStaff(Staff.mock());
		}
	}
	
//	@Test
//	public void test(){
//		foo();
//	}
	@Test
	public void foo(){
		Staff staff = Staff.mock();
		staff.setTel("17708488968");
		staff.setPassword(Md5.makeMD5("ranbow"));
		staff.setName("Ranbow");
		staff.setGender("男");
		staff.setIdentifier(Staff.MANAGER);
		staffDao.addStaff(staff);
		
		/*--------------------------------------------------------------------*/
		staff = Staff.mock();
		staff.setTel("tom");
		staff.setPassword(Md5.makeMD5("tom"));
		staff.setName("汤姆");
		staff.setGender("男");
		staff.setIdentifier(Staff.ORDINARY_STAFF);
		staffDao.addStaff(staff);
		
		/*--------------------------------------------------------------------*/
		staff = Staff.mock();
		staff.setTel("root");
		staff.setPassword("root");
		staff.setName("成旭元");
		staff.setGender("男");
		staff.setIdentifier(Staff.MANAGER);
		staffDao.addStaff(staff);
	}
	
}
