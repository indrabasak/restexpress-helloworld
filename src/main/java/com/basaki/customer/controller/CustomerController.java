package com.basaki.customer.controller;

import static com.basaki.customer.Constants.*;

import java.util.List;

import org.restexpress.Request;
import org.restexpress.Response;

import com.basaki.customer.model.Customer;
import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.builder.DefaultUrlBuilder;
import com.strategicgains.hyperexpress.builder.TokenResolver;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import io.netty.handler.codec.http.HttpMethod;

/**
 * 
 * The <tt>CustomerController</tt> is the controller of the <tt>Customer</tt>
 * service and it is responsible for processing all customer service related
 * requests. A RestExpress controller POJO follows certain set of rules:
 * <ol>
 * <li>Any controller method exposed as a HTTP method should only take
 * <tt>org.restexpress.Request</tt> and <tt>org.restexpress.Response</tt> as
 * parameters. Here is an example of a method signature: methodName(Request
 * request, Response response)</li>
 * <li>The method modified should be public.</li>
 * <li>The method can either return a value or can be declared as void.
 * <li>The method names follows standard Java naming conventions. However if you
 * name your method as create, read, update, delete, patch, etc., it
 * Automatically maps to corresponding HTTP operations - POST, GET, PUT, DELETE,
 * and PATCH operations.</li>
 * <li>Here is an example of a method signature:
 * <p/>
 * <tt>public Customer read(Request request, Response response)</tt>
 * <p/>
 * If you have a standard method name, here is an example of declaring a route:
 * </p>
 * <tt>server.uri("/customers/{id}", controller).method(HttpMethod.GET,
		HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.PATCH)</tt></li>
 * <li>Here is another example:
 * <p/>
 * <tt>public List<Customer> readAll(Request request, Response response) <tt>
 * 
 * <p/>
 * If you use non-standard method name (not create, read, update, delete, patch,
 * etc.), you have to explicitly map your method name to a HTTP method. e.g.
 * </p>
 * <tt>server.uri("/customers", controller).action("readAll", HttpMethod.GET)</tt>
 * </li></li>
 * 
 * </ol>
 * <p/>
 * Each method in Any POJO can be a RestExpress controller.
 * 
 * @author Indra Basak
 *
 */
public class CustomerController {
    private static final UrlBuilder LOCATION_BUILDER = new DefaultUrlBuilder();

    private CustomerService service;

    public CustomerController(CustomerService worker) {
	this.service = worker;
    }

    @ApiOperation(value = "Gets a customer based on customer id",
	    notes = "Retrieves a single customer", response = Customer.class)
    @ApiParam(name = "id", required = true, value = "Required Customer id",
	    defaultValue = "Title placeholder", allowableValues = "Any integer")
    public Customer read(Request request, Response response) {
	String id = request.getHeader(CUSTOMER_ID_PARAMETER,
		"Customer id is missing!");
	Integer custId = Integer.valueOf(id);
	return service.getCustomer(custId);
    }

    @ApiOperation(value = "Gets all the customers",
	    notes = "Retrieves all customers", response = Customer.class,
	    responseContainer = "List")
    public List<Customer> readAll(Request request, Response response) {
	return service.getCustomers();
    }

    @ApiOperation(value = "Creates a new customer based on request body",
	    notes = "Creates a customer", response = Customer.class)
    public Customer create(Request request, Response response) {
	Customer customer = request.getBodyAs(Customer.class,
		"Customer details not provided!");
	Customer newCustomer = service.createCustomer(customer);

	// Construct the response for create
	response.setResponseCreated();

	// Include the Location header
	String locationPattern = request.getBaseUrl()
		+ request.getNamedUrl(HttpMethod.GET, CUSTOMER_READ_ROUTE);
	TokenResolver resolver = HyperExpress.bind(CUSTOMER_ID_PARAMETER,
		String.valueOf(newCustomer.getId()));
	response.addLocationHeader(
		LOCATION_BUILDER.build(locationPattern, resolver));

	return newCustomer;
    }

    @ApiOperation(value = "Deletes a customer based on customer id",
	    notes = "Deletes a customer", response = Customer.class)
    public Customer delete(Request request, Response response) {
	System.out.println("*** Start deleteCustomer");
	String id = request.getHeader(CUSTOMER_ID_PARAMETER,
		"Customer id is missing!");
	Integer custId = Integer.valueOf(id);
	return service.deleteCustomer(custId);
    }

    @ApiOperation(value = "Updates a customer based on customer id",
	    notes = "Updates a customer", response = Customer.class)
    public Customer update(Request request, Response response) {
	String id = request.getHeader(CUSTOMER_ID_PARAMETER,
		"Customer id is missing!");
	Integer custId = Integer.valueOf(id);
	Customer customer = request.getBodyAs(Customer.class,
		"Customer details not provided");

	return service.updateCustomer(custId, customer);
    }

    @ApiOperation(value = "Updates a customer partially based on customer id",
	    notes = "Partially updates a customer", response = Customer.class)
    public Customer patch(Request request, Response response) {
	String id = request.getHeader(CUSTOMER_ID_PARAMETER,
		"Customer id is missing!");
	Integer custId = Integer.valueOf(id);
	Customer customer = request.getBodyAs(Customer.class,
		"Customer details not provided");

	Customer cust = service.updatePartialCustomer(custId, customer);
	return cust;
    }
}
