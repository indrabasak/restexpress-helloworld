package com.basaki.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basaki.customer.exception.CustomerNotFoundException;
import com.basaki.customer.model.Address;
import com.basaki.customer.model.Customer;

/**
 * 
 * The <tt>CustomerService</tt> is the business service layer. It is responsible
 * for actual CRUD operations on the <tt>Customer</tt> and <tt>Address</tt>
 * object
 * 
 * @author Indra Basak
 *
 */
public class CustomerService {
    private int counter = 1;
    private Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

    /**
     * Constructs a in-memory hash map for storing customer objects.
     */
    public CustomerService() {
	Customer cust = new Customer();
	cust.setId(counter);
	cust.setFirstName("Robert");
	cust.setLastName("Land");
	Address addr = new Address();
	addr.setStreet("2486 Maxwell Farm Road");
	addr.setCity("Waynesboro");
	addr.setState("VA");
	addr.setZipCode("22980");
	cust.setAddress(addr);
	customers.put(cust.getId(), cust);
	counter++;

	cust = new Customer();
	cust.setId(counter);
	cust.setFirstName("James");
	cust.setLastName("Brenner");
	addr = new Address();
	addr.setStreet("585 Pennsylvania Avenue");
	addr.setCity("New Brunswick");
	addr.setState("NJ");
	addr.setZipCode("08901");
	cust.setAddress(addr);
	customers.put(cust.getId(), cust);
	counter++;

	cust = new Customer();
	cust.setId(counter);
	cust.setFirstName("Billy");
	cust.setLastName("Jones");
	addr = new Address();
	addr.setStreet("1201 Still Pastures Drive");
	addr.setCity("Columbia");
	addr.setState("SC");
	addr.setZipCode("29210");
	cust.setAddress(addr);
	customers.put(cust.getId(), cust);
	counter++;
    }

    /**
     * Retrieves a customer based on customer id.
     * 
     * @param id
     *            customer id
     * @return customer object corresponding to the id
     */
    public Customer getCustomer(int id) {
	Customer cust = customers.get(id);
	
	if (cust != null) {
	    return cust;
	}
	
	throw new CustomerNotFoundException("Customer with id " + id + " not found.", id);
    }

    /**
     * Retrieves all the customers.
     * 
     * @return a list of customers
     */
    public List<Customer> getCustomers() {
	return new ArrayList<Customer>(customers.values());
    }

    /**
     * Creates a new customer
     * 
     * @param customer
     *            customer details for the new object
     * @return newly created customer object
     */
    public Customer createCustomer(Customer customer) {
	Customer cust = null;
	if (customer != null) {
	    cust = new Customer();
	    cust.setId(counter);
	    counter++;
	    copy(customer, cust);
	    customers.put(cust.getId(), cust);
	}

	return cust;
    }

    /**
     * Deletes a customer based on id.
     * 
     * @param id
     *            customer identifier
     * @return deleted customer
     */
    public Customer deleteCustomer(int id) {
	return customers.remove(id);
    }

    /**
     * Updates an existing customer with new information. All the information is
     * updated exception the identifier
     * 
     * @param id
     *            customer identifier
     * @param customer
     *            customer containing updated information
     * @return newly updated customer
     */
    public Customer updateCustomer(int id, Customer customer) {
	Customer cust = getCustomer(id);
	if (cust != null && customer != null) {
	    copy(customer, cust);
	}

	return cust;
    }

    /**
     * Partially updates a customer. A customer is updated with information
     * contained in the customer object passed as parameter. If certain
     * properties are not populated, those properties are ignored and the
     * customer retains the existing values.
     * 
     * @param id
     *            customer identifier
     * @param customer
     *            partially updated customer
     * @return updated customer
     */
    public Customer updatePartialCustomer(int id, Customer customer) {
	Customer cust = getCustomer(id);
	if (cust != null && customer != null) {
	    cust.setFirstName(customer.getFirstName() != null
		    ? customer.getFirstName() : cust.getFirstName());
	    cust.setLastName(customer.getLastName() != null
		    ? customer.getLastName() : cust.getLastName());
	    if (cust.getAddress() == null) {
		Address addr = new Address();
		cust.setAddress(addr);
	    }

	    if (customer.getAddress() != null) {
		cust.getAddress()
			.setStreet(customer.getAddress().getStreet() != null
				? customer.getAddress().getStreet()
				: cust.getAddress().getStreet());
		cust.getAddress()
			.setCity(customer.getAddress().getCity() != null
				? customer.getAddress().getCity()
				: cust.getAddress().getCity());
		cust.getAddress()
			.setState(customer.getAddress().getState() != null
				? customer.getAddress().getState()
				: cust.getAddress().getState());
		cust.getAddress()
			.setZipCode(customer.getAddress().getZipCode() != null
				? customer.getAddress().getZipCode()
				: cust.getAddress().getZipCode());
	    }
	}

	return cust;
    }

    /**
     * Copies on customer information to another.
     * 
     * @param from
     *            customer to be copied from
     * @param to
     *            customer object copied to
     */
    private void copy(Customer from, Customer to) {
	to.setFirstName(from.getFirstName());
	to.setLastName(from.getLastName());
	Address addr = to.getAddress();
	if (addr == null) {
	    addr = new Address();
	    to.setAddress(addr);
	}

	if (from.getAddress() != null) {
	    addr.setStreet(from.getAddress().getStreet());
	    addr.setCity(from.getAddress().getCity());
	    addr.setState(from.getAddress().getState());
	    addr.setZipCode(from.getAddress().getZipCode());
	}
    }
}
