package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class MenuAppProviderModule implements Module {

	@Override
	public void configure(Binder binder) {
		// do manual binding here
	}
	
	@Provides
	@Named("testMessage")
	public String provideMessage(MenuAppConfiguration configuration) {
		return configuration.getTestMessage();
	}

}
