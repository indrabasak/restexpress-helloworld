package com.basaki.customer.serialization;

import org.restexpress.response.RawResponseWrapper;
import org.restexpress.serialization.AbstractSerializationProvider;
import org.restexpress.serialization.json.JacksonJsonProcessor;

/**
 * 
 * <tt>SerializationProvider</tt> provides the XML and JSON HTTP message
 * converters for reading and writing (marshalling/unmarshalling) beans in XML
 * and JSON. If no <tt>SerializationProvider</tt> is provided, REST uses
 * <tt>DefaultSerializationProvider</tt>. It is provided in our example to strip
 * customer class package during XML serialization.
 * 
 * @author Indra Basak
 *
 */
public class SerializationProvider extends AbstractSerializationProvider {

    /**
     * Constructs a new <tt>SerializationProvider</tt> and adds default
     * <tt>JacksonJsonProcessor</tt> for JSON processing and custom
     * <tt>XmlSerializationProcessor</tt> for XML processing. If no JSON
     * processor is provides, it will throw an exception during JSON processing.
     */
    public SerializationProvider() {
	add(new JacksonJsonProcessor(), new RawResponseWrapper(), true);
	add(new XmlSerializationProcessor(), new RawResponseWrapper());
    }
}
