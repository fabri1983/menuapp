package org.fabri1983.menuapp.api.config;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;
import io.smartmachine.couchbase.CouchbaseClientFactory;

public class MenuAppConfiguration extends Configuration {

	@NotEmpty
	private String buildInfo;
	@NotEmpty
	private String profile;
	@NotEmpty
	private String importantProperty1;
	@NotEmpty
	private String importantProperty2;
	@Min(1)
	private int maxAllowedResults;
	@Valid @NotNull
	private CouchbaseClientFactory couchbaseClientFactory;
    
	public String getBuildInfo () {
		return buildInfo;
	}
	
	public String getProfile () {
		return profile;
	}
	
	public String getImportantProperty1 () {
		return importantProperty1;
	}
	
	public String getImportantProperty2 () {
		return importantProperty2;
	}
	
	public int getMaxAllowedResults () {
		return maxAllowedResults;
	}
	
	public CouchbaseClientFactory getCouchbaseClientFactory() {
		return couchbaseClientFactory;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
