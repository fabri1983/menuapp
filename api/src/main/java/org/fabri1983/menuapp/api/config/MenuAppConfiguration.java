package org.fabri1983.menuapp.api.config;

import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class MenuAppConfiguration extends Configuration {

	@NotEmpty
	private String testMessage;
	@NotEmpty
	private String importantProperty1;
	@NotEmpty
	private String importantProperty2;
	@Min(1)
	private int maxAllowedResults;
	
	public String getTestMessage() {
		return testMessage;
	}
	
	public String getImportantProperty1() {
		return importantProperty1;
	}
	
	public String getImportantProperty2() {
		return importantProperty2;
	}
	
	public int getMaxAllowedResults() {
		return maxAllowedResults;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
