package com.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject HIBERNATE session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	//@Transactional - implemented in service layer
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query =session.createQuery("from Customer order by firstName,lastName", Customer.class);
		List<Customer> customer = query.getResultList();
		return customer;
	}


	@Override
	public void saveCustomers(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}


	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("delete from Customer where id=:theId");
		query.setParameter("theId", id);
		query.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String name) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = null;
		if(name != null && name.trim().length() > 0) {
			
			query =session.createQuery("from Customer where lower(firstName) like:theName or lower(lastName) like:theName", Customer.class);
			query.setParameter("theName", "%" + name.toLowerCase() + "%");
		}else {
			query =session.createQuery("from Customer order by firstName,lastName", Customer.class);
		}
		List<Customer> customer = query.getResultList();
		return customer;
	}

}
