package org.fabri1983.menuapp.api.config;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.configuration.FileConfigurationSourceProvider;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;

/**
 * Creates a custom configuration provider which first try to load a configuration file using {@link FileConfigurationSourceProvider},
 * if it fails then fall back to @{link ResourceConfigurationSourceProvider} in order to load the file within the jar. 
 */
public class CustomConfigurationSourceProvider implements ConfigurationSourceProvider {
	
	private Logger logger;

	public CustomConfigurationSourceProvider() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
    public InputStream open(String path) throws IOException {
		try {
			return new FileConfigurationSourceProvider().open(path);
		} catch (IOException e){
        	if ("-".equals(path))
        		path = "server-config.yml";
        	else
        		logger.warn(String.format("File %s not found. Fallback to %s", path, ResourceConfigurationSourceProvider.class.getName()));
        	return new ResourceConfigurationSourceProvider().open(path);
        }
    }
}