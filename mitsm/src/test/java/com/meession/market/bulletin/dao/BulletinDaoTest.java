package com.meession.market.bulletin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.bulletin.entity.Bulletin;
import com.meession.market.common.RDU;
import com.meession.market.staff.dao.IStaffDao;
import com.meession.market.staff.entity.Staff;

public class BulletinDaoTest extends AbstractSpringTest {
	@Resource
	private IBulletinDao bulletinDao;
	@Resource
	private IStaffDao staffDao;

	@Test
	public void testAddBulletin() {
		Bulletin bulletin = null;
		Staff staff = null;
		for (int i = 0; i < 50; i++) {
			bulletin = Bulletin.mock();
			List<Staff> staffList = staffDao.findAll();
			do{
				staff = staffList.get(RDU.random(staffList.size()));
			}while(staff !=null && staff.getIdentifier() != Staff.MANAGER);
			bulletin.setMaker(staff);
			bulletinDao.addBulletin(bulletin);
		}
		System.gc();
	}

	

}
