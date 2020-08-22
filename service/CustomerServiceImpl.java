package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDAO;
import com.crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerdao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerdao.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomers(Customer customer) {
		customerdao.saveCustomers(customer);		
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerdao.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerdao.deleteCustomer(id);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String name) {
		return customerdao.searchCustomers(name);
	}

}
