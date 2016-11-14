package org.fabri1983.menuapp.api.config;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class MenuAppConfiguration extends Configuration {

	@NotEmpty
	private String testMessage;
	
	public String getTestMessage() {
		return testMessage;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
