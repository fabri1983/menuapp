package org.fabri1983.menuapp.api.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.HasMenuQueryFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;
import org.fabri1983.menuapp.api.config.hasfeature.impl.MenuQueryConfig;

import io.dropwizard.Configuration;
import io.smartmachine.couchbase.CouchbaseClientFactory;

public class MenuAppConfiguration extends Configuration implements HasBuildInfoFeature, HasMenuQueryFeature {

	@Valid @NotNull
	private BuildInfoConfig buildInfoConfig;
	@Valid @NotNull
	private MenuQueryConfig menuQueryConfig;	
	@Valid @NotNull
	private CouchbaseClientFactory couchbaseClientFactory;
	
	@Override
	public BuildInfoConfig getBuildInfoConfig () {
		return buildInfoConfig;
	}
	
	@Override
	public MenuQueryConfig getMenuQueryConfig () {
		return menuQueryConfig;
	}

	public CouchbaseClientFactory getCouchbaseClientFactory() {
		return couchbaseClientFactory;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
