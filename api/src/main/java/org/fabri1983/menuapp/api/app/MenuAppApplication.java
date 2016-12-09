package org.fabri1983.menuapp.api.app;

import org.fabri1983.menuapp.api.config.CustomConfigurationSourceProvider;
import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.api.health.DummyHealthCheck;
import org.fabri1983.menuapp.api.provider.MenuAppProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.smartmachine.couchbase.CouchbaseBundle;
import io.smartmachine.couchbase.CouchbaseClientFactory;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class MenuAppApplication extends Application<MenuAppConfiguration> {

	private Logger logger;
	
	public static void main(String[] args) throws Exception {
		new MenuAppApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<MenuAppConfiguration> bootstrap) {
		logger = LoggerFactory.getLogger(this.getClass());
		
		// we have our custom configuration provider
		bootstrap.setConfigurationSourceProvider(new CustomConfigurationSourceProvider());

		// create the bundle for dropwizard guice integration
		GuiceBundle<MenuAppConfiguration> guiceBundle = GuiceBundle.<MenuAppConfiguration>builder()
				// add yours module classes with your own injections
				.modules(new MenuAppProvider())
				// this ensures that bean creation in that package is set up automatically.
				.enableAutoConfig("org.fabri1983.menuapp.api")
				// force eager singletons creation (by default is Stage.PRODUCTION)
				.build();
		bootstrap.addBundle(guiceBundle);
		
		// couchbase bundle
		bootstrap.addBundle(new CouchbaseBundle<MenuAppConfiguration>() {
			@Override
			public CouchbaseClientFactory getCouchbaseClientFactory(MenuAppConfiguration configuration) {
				return configuration.getCouchbaseClientFactory();
			}
		});
	}

	@Override
	public void run (MenuAppConfiguration configuration, Environment environment) throws Exception {
		logger.info("Starting Menu App application");
		registerHealthChecks(environment);
		registerResources(environment);
		logger.info("Menu App application started");
	}

	private void registerHealthChecks (Environment environment) {
		environment.healthChecks().register("dummy healthcheck", new DummyHealthCheck());
		// if you defined the health check as a bean then add it this way  
//		environment.lifecycle().manage(guiceBundle.getInjector().getInstance(DummyHealthCheck.class));
	}
	
	private void registerResources(Environment environment) {
		// Nothing to do here since we use Guice for bean creation and dependency injection.
		// In case you want to manually register a resource do:
		//		environment.jersey().register(new LoginResource(...))
		//		environment.jersey().register(new TestResource(...))
		//		... etc
	}

	@Override
    public String getName() {
        return "MenuAppApplication-server";
    }
}
