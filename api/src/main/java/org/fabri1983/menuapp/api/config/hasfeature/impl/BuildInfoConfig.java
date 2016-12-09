package org.fabri1983.menuapp.api.config.hasfeature.impl;

import org.hibernate.validator.constraints.NotEmpty;

public class BuildInfoConfig {

	@NotEmpty
	private String buildInfo;
	@NotEmpty
	private String buildProfile;
	
	public String getBuildInfo () {
		return buildInfo;
	}
	
	public String getBuildProfile () {
		return buildProfile;
	}
}
