package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.core.service.MenuService;
import org.fabri1983.menuapp.core.service.implementation.SimpleMenuService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MenuServiceProvider {

	private MenuService implementation;
	
	@Inject
	public MenuServiceProvider(MenuRepositoryProvider menuRepositoryProvider, MenuAppConfiguration configuration) {
		this.implementation = new SimpleMenuService(menuRepositoryProvider.getImplementation());
	}

	public MenuService getImplementation() {
		return implementation;
	}

}
