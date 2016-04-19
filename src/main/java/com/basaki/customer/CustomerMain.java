package com.basaki.customer;

import static com.basaki.customer.Constants.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.restexpress.RestExpress;
import org.restexpress.plugin.hyperexpress.HyperExpressPlugin;
import org.restexpress.plugin.hyperexpress.Linkable;

import com.basaki.customer.controller.CustomerController;
import com.basaki.customer.controller.CustomerService;
import com.basaki.customer.model.Customer;
import com.basaki.customer.serialization.SerializationProvider;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.RelTypes;
import com.strategicgains.restexpress.plugin.metrics.MetricsPlugin;
import com.strategicgains.restexpress.plugin.swagger.SwaggerPlugin;

import io.netty.handler.codec.http.HttpMethod;

/**
 * 
 * The <tt>CustomerMain</tt> is the main entry point class to Customer service.
 * 
 * @author Indra Basak
 *
 */
public class CustomerMain {

    /**
     * Creates all different routes for customer controller.
     * <tt>CustomerController</tt> is a POJO with CRUD methods.
     * 
     * @param server
     */
    private static void defineRoutes(RestExpress server) {
	CustomerController controller = new CustomerController(
		new CustomerService());

	server.uri("/customers/{id}", controller).method(HttpMethod.GET,
		HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE)
		.name(CUSTOMER_READ_ROUTE);

	// since 'readAll' method name doesn't map to 'read'
	// use 'action' to map 'readAll' service method to HTTP GET
	server.uri("/customers", controller).action("readAll", HttpMethod.GET)
		.method(HttpMethod.POST).name(CUSTOMER_COLLECTION_READ_ROUTE);
    }

    /**
     * Adds optional RestExpress settings.
     * 
     * @param server
     */
    private static void registerOptionalSettings(RestExpress server) {
	server.setName(SERVICE_NAME);
	// default port 8081
	server.setPort(9000);
	server.setIoThreadCount(10);
	server.setExecutorThreadCount(10);
	// default serialization provider is DefaultSerializationProvider
	// using custom XML serializer to remove class package name from
	// Customer
	// object. Doesn't happen in JSON format.
	RestExpress
		.setDefaultSerializationProvider(new SerializationProvider());

    }

    /**
     * Registers optional RestExpress plugins
     * 
     * @param server
     */
    private static void registerOptionalPlugins(RestExpress server) {
	registerSwaggerPlugin(server);
	// registerHyperExpressPlugin(server);
	registerMetricsPlugin(server);
    }

    /**
     * Registers a Swagger plugin to generate Swagger JSON documentation of
     * endpoints.
     * <p/>
     * URL path is optional. Defaults to '/api-docs'
     * <p/>
     * flag is optional. 'public-route indicates annotated apis will be shown
     * <p/>
     * Swagger documentation can be viewed at http://<host>:<port>/customers
     * 
     * @param server
     */
    private static void registerSwaggerPlugin(RestExpress server) {
	new SwaggerPlugin("/api-docs").flag("public-route").register(server);
    }

    /**
     * Registers HyperExpress plugin. It adds hypermedia links to your domain
     * models in JSON. Once you turn this plugin on, XMl format will no longer
     * be supported
     * 
     * @param server
     */
    @SuppressWarnings("unused")
    private static void registerHyperExpressPlugin(RestExpress server) {
	new HyperExpressPlugin(Linkable.class).register(server);
	Map<String, String> routes = server.getRouteUrlsByName();
	HyperExpress.relationships().forCollectionOf(Customer.class)
		.rel(RelTypes.SELF, routes.get(CUSTOMER_COLLECTION_READ_ROUTE))
		.forClass(Customer.class)
		.rel(RelTypes.SELF, routes.get(CUSTOMER_READ_ROUTE));
    }

    /**
     * Registers metrics plugin metrics. Creates following metrics on all
     * routes:
     * 
     * <ol>
     * <li>currently active requests (counter)</li>
     * <li>all exceptions occurred (counter) all</li>
     * <li>times (timer, milliseconds/hours)</li>
     * <li>times by route (timer, milliseconds/hours)
     * <li>exceptions by route (counter)</li>
     * <li>counters by return status (counter)</li>
     * </ol>
     * <p/>
     * Only JMX reporter and Console reporter are turned on. You can view the
     * JMX metrics in JConsole (comes with jdk/bin). Console reporter prints out
     * metrics on the console.
     * 
     * @param server
     */
    private static void registerMetricsPlugin(RestExpress server) {
	MetricRegistry registry = new MetricRegistry();
	new MetricsPlugin(registry).register(server);

	// Once the reporter is started, all of the metrics in the registry will
	// become visible via JConsole
	final JmxReporter reporter = JmxReporter.forRegistry(registry).build();
	reporter.start();

	// report will print every minute in the console
	final ConsoleReporter conSolereporter = ConsoleReporter
		.forRegistry(registry).convertRatesTo(TimeUnit.SECONDS)
		.convertDurationsTo(TimeUnit.MILLISECONDS).build();
	conSolereporter.start(1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
	// Create a new RestExpress server. Default port is 8081
	RestExpress server = new RestExpress();
	defineRoutes(server);

	// optional
	registerOptionalSettings(server);
	registerOptionalPlugins(server);

	// binds the RestExpress Netty server to a host and port.
	// It's now ready to process incoming messages
	server.bind();

	// server shutdown hook to exit gracefully
	server.awaitShutdown();
    }
}
