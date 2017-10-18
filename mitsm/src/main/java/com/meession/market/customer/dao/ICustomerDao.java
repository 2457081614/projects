package com.meession.market.customer.dao;

import java.util.List;

import com.meession.market.common.support.dao.IDaoSupport;
import com.meession.market.customer.entity.Customer;



public interface ICustomerDao extends IDaoSupport{
	public void save(Customer customer);
	public void delete(long id);
	public void delete(List<Long> ids);
	public void update(Customer customer);
	public Customer find(String tel);
	public Customer find(long id);
	public List<Customer> findAll();
}
