package com.meession.market.revisit.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.common.RDU;
import com.meession.market.customer.dao.ICustomerDao;
import com.meession.market.parttimestaff.dao.IParttimeStaffDao;
import com.meession.market.parttimestaff.dao.impl.ParttimeStaffDao;
import com.meession.market.revisit.entity.Revisit;

public class RevisitDaoTest extends AbstractSpringTest {
	@Resource
	private IRevisitDao revisitDao;
	@Resource
	private ICustomerDao customerDao;
	@Resource
	private IParttimeStaffDao parttimeStaffDao;

	@Test
	public void testAdd() {
		Revisit revisit = null;
		for (int i = 0; i < 100; i++) {
			revisit = Revisit.mock();
			revisitDao.add(revisit);
		}
	}
}
