package com.meession.market.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.customer.dao.ICustomerDao;
import com.meession.market.customer.entity.Customer;
import com.meession.market.customer.service.ICustomerService;

@Repository
public class CustomerService implements ICustomerService {

	@Resource
	private ICustomerDao customerDao;
	
	@Override
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
	}
	public void addCustomers(List<Customer> list){
		if(list != null && list.size() > 0){
			for(Customer c : list){
				customerDao.save(c);
			}
		}
	}

	public void delete(long id){
		customerDao.delete(id);
	}
	public void delete(List<Long> ids){
		customerDao.delete(ids);
	}
	public void update(Customer customer){
		customerDao.update(customer);
	}
	
	@Override
	public Customer findById(long id) {
		return customerDao.find(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	

}
