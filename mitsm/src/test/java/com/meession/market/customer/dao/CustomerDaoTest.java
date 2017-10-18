package com.meession.market.customer.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.customer.entity.Customer;

public class CustomerDaoTest extends AbstractSpringTest {

	@Resource
	private ICustomerDao customerDao;

	@Test
	public void testSave() {
		for (int i = 0; i < 200; i++)
			customerDao.save(Customer.mock());
	}

}
