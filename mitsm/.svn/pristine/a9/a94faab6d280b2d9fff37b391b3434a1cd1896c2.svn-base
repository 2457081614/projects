package com.meession.market.customer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.customer.dao.ICustomerDao;
import com.meession.market.customer.entity.Customer;

@Repository
public class CustomerDao extends DaoSupport implements ICustomerDao {

	@Override
	public void save(Customer customer) {
		getDaoTemplate().save(customer);
	}

	@Override
	public void delete(long id) {
		getDaoTemplate().delete(Customer.class, id);
	}

	@Override
	public void delete(List<Long> ids) {
		getDaoTemplate().delete(Customer.class, ids);

	}

	@Override
	public void update(Customer customer) {
		 getDaoTemplate().update(customer);

	}

	@Override
	public Customer find(String tel) {
		String sql = "select entity from Customer entity where entity.tel = :tel";
		return getDaoTemplate().find(Customer.class, sql,tel);
	}

	@Override
	public Customer find(long id) {
		return getDaoTemplate().find(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
		return getDaoTemplate().list(Customer.class);
	}

}
