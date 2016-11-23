package org.fabri1983.menuapp.api.app;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.api.health.DummyHealthCheck;
import org.fabri1983.menuapp.api.provider.MenuAppProviderModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Stage;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class MenuAppApplication extends Application<MenuAppConfiguration> {

	private static final Logger logger = LoggerFactory.getLogger(MenuAppApplication.class);
	private static String scanningPackage = "org.fabri1983.menuapp.api";
	
	public static void main(String[] args) throws Exception {
		new MenuAppApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<MenuAppConfiguration> bootstrap) {
		// create the bundle for dropwizard-guice integration
		GuiceBundle<MenuAppConfiguration> guiceBundle = GuiceBundle.<MenuAppConfiguration>builder()
				// add yours module classes with your own injections
				.modules(new MenuAppProviderModule())
				// this ensures that bean creation in that package is set up automatically.
				.enableAutoConfig(scanningPackage)
				// force eager singletons creation
				.build(Stage.PRODUCTION);
		bootstrap.addBundle(guiceBundle);
	}

	@Override
	public void run (MenuAppConfiguration configuration, Environment environment) throws Exception {
		logger.info("Starting Menu App application using configuration {}", configuration);
		registerHealthChecks(environment);
		registerResources(environment);
		logger.info("Menu App application started");
	}

	private void registerHealthChecks (Environment environment) {
		environment.healthChecks().register("dummy healthcheck", new DummyHealthCheck());
		// if you to defined the health check as a bean then grab it this way  
//		environment.lifecycle().manage(guiceBundle.getInjector().getInstance(DummyHealthCheck.class));
	}
	
	private void registerResources(Environment environment) {
		// Nothing to do here since we use Guice for bean creation and dependency injection.
		// In case you want to manually register a resource do:
		//		environment.jersey().register(new LoginResource(...))
		//		environment.jersey().register(new TestResource(...))
		//		... etc
	}

}
