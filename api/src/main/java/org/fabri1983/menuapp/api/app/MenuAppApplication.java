package org.fabri1983.menuapp.api.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.smartmachine.couchbase.CouchbaseBundle;
import io.smartmachine.couchbase.CouchbaseClientFactory;

import org.fabri1983.menuapp.api.config.CustomConfigurationSourceProvider;
import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.api.config.MenuAppProvider;
import org.fabri1983.menuapp.api.health.DummyHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.vyarus.dropwizard.guice.GuiceBundle;

public class MenuAppApplication extends Application<MenuAppConfiguration> {

	private Logger logger;
	
	public static void main(String[] args) throws Exception {
		new MenuAppApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<MenuAppConfiguration> bootstrap) {
		logger = LoggerFactory.getLogger(this.getClass());
		
		bootstrap.setConfigurationSourceProvider(new CustomConfigurationSourceProvider());

		addSwaggerBundle(bootstrap);
		
		addGuiceBundle(bootstrap);
		
		addCouchbaseBundle(bootstrap);
	}

	private void addSwaggerBundle(Bootstrap<MenuAppConfiguration> bootstrap) {
		bootstrap.addBundle(new SwaggerBundle<MenuAppConfiguration>() {
	        @Override
	        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(MenuAppConfiguration configuration) {
	            return configuration.getSwaggerBundleConfiguration();
	        }
	    });
	}
	
	private void addGuiceBundle(Bootstrap<MenuAppConfiguration> bootstrap) {
		GuiceBundle<MenuAppConfiguration> guiceBundle = GuiceBundle.<MenuAppConfiguration>builder()
				// bind all direct interfaces implemented by configuration class of the style HasXXXFeature
				.bindConfigurationInterfaces()
				// add your module class with your own injections
				.modules(new MenuAppProvider())
				// this ensures that dependency injection in that package is set up automatically
				.enableAutoConfig("org.fabri1983.menuapp.api")
				// force eager singletons creation (by default is Stage.PRODUCTION)
				.build();
		bootstrap.addBundle(guiceBundle);
	}
	
	private void addCouchbaseBundle(Bootstrap<MenuAppConfiguration> bootstrap) {
		CouchbaseBundle<MenuAppConfiguration> couchbaseBundle = new CouchbaseBundle<MenuAppConfiguration>() {
			@Override
			public CouchbaseClientFactory getCouchbaseClientFactory(MenuAppConfiguration configuration) {
				return configuration.getCouchbaseClientFactory();
			}
		};
		bootstrap.addBundle(couchbaseBundle);
	}

	@Override
	public void run(MenuAppConfiguration configuration, Environment environment) throws Exception {
		logger.info("---------> Starting Menu App application");
		registerHealthChecks(environment);
		logger.info("---------> Menu App application started");
	}

	private void registerHealthChecks(Environment environment) {
		environment.healthChecks().register("dummy healthcheck", new DummyHealthCheck());
		// if you defined the health check as a bean then add it this way  
//		environment.lifecycle().manage(guiceBundle.getInjector().getInstance(DummyHealthCheck.class));
	}

	@Override
    public String getName() {
        return "Menu App API Server";
    }
}
