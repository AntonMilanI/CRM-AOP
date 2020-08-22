package com.crm.service;

import java.util.List;

import com.crm.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomers(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String name);
}
