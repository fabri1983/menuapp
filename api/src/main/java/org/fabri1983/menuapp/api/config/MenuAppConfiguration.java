package org.fabri1983.menuapp.api.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.smartmachine.couchbase.CouchbaseClientFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.HasMenuQueryFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;
import org.fabri1983.menuapp.api.config.hasfeature.impl.MenuQueryConfig;

public class MenuAppConfiguration extends Configuration implements HasBuildInfoFeature, HasMenuQueryFeature {

	@Valid @NotNull
	private BuildInfoConfig buildInfoConfig;
	@Valid @NotNull
	private MenuQueryConfig menuQueryConfig;	
	@Valid @NotNull
	private CouchbaseClientFactory couchbaseClientFactory;
	@JsonProperty("swaggerConfig")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;
	
	@Override
	public BuildInfoConfig getBuildInfoConfig () {
		return buildInfoConfig;
	}
	
	@Override
	public MenuQueryConfig getMenuQueryConfig () {
		return menuQueryConfig;
	}

	public CouchbaseClientFactory getCouchbaseClientFactory () {
		return couchbaseClientFactory;
	}
	
	public SwaggerBundleConfiguration getSwaggerBundleConfiguration () {
		return swaggerBundleConfiguration;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
