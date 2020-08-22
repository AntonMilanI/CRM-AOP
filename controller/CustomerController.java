package com.crm.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.entity.Customer;
import com.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		List<Customer> theCustomers = customerService.getCustomers();
		model.addAttribute("customers", theCustomers);
		return "list-customers";
	}
	
	@GetMapping("/showAddForm")
	public String addCustomers(Model model) {
		model.addAttribute("customer", new Customer());
		return "add-customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomers(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomers(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showUpdateForm")
	public String updateCustomers(@RequestParam("customerId") int id, Model model) {
		 Customer customer = customerService.getCustomer(id);
		
		 model.addAttribute("customer", customer);
		return "add-customer";
	}
	
	@GetMapping("/delete")
	public String deleteCustomers(@RequestParam("customerId") int id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("searchName") String name, Model model) {
		List<Customer> theCustomers = customerService.searchCustomers(name);
		model.addAttribute("customers", theCustomers);
		return "list-customers";
	}
}
