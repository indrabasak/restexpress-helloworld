package com.basaki.customer.model;

import org.restexpress.plugin.hyperexpress.Linkable;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;

/**
 * 
 * The <tt>Customer</tt> represents a customer in our example.
 * @author Indra Basak
 *
 */
//required to replace token 'id' in url pattern for HATEOS
@TokenBindings({ @BindToken(value = "id", field = "id") })
public class Customer implements Linkable {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    @Override
    public String toString() {
	return "Customer [id=" + id + ", firstName=" + firstName + ", lastName="
		+ lastName + ", address=" + address + "]";
    }
}
