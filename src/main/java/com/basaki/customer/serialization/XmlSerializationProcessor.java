package com.basaki.customer.serialization;

import org.restexpress.serialization.xml.XstreamXmlProcessor;

import com.basaki.customer.model.Customer;

/**
 * 
 * The <tt>XmlSerializationProcessor</tt> is a custom XML converter which
 * replaces fully qualified class name of <tt>Customer</tt> object with
 * <tt>customer</tt> XML tag.
 * 
 * @author Indra Basak
 *
 */
public class XmlSerializationProcessor extends XstreamXmlProcessor {
    public XmlSerializationProcessor() {
	alias("customer", Customer.class);
    }
}
