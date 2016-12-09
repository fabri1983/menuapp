package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class MenuAppProvider implements Module {
	
	@Override
	public void configure (Binder binder) {
		// add manual bindings here and configure them
	}
	
	@Provides
	@Named("profile")
	public String provideTestMessage (MenuAppConfiguration configuration) {
		return configuration.getProfile();
	}

	@Provides
	@Named("buildInfo")
	public String provideBuildInfo (MenuAppConfiguration configuration) {
		return configuration.getBuildInfo();
	}
}
