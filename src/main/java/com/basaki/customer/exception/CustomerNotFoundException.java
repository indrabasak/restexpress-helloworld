package com.basaki.customer.exception;

/**
 * 
 * The <tt>CustomerNotFoundException</tt> is thrown when a customer is not
 * found.
 * 
 * @author Indra Basak
 *
 */
public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int id;

    public CustomerNotFoundException(String message, int id) {
	super(message);
	this.id = id;
    }

    public int getId() {
	return id;
    }
}
