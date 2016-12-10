package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.core.repository.MenuRepository;
import org.fabri1983.menuapp.core.repository.implementation.InMemoryMenuRepository;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MenuRepositoryProvider {

	private MenuRepository implementation;
	
	@Inject
	public MenuRepositoryProvider (MenuDaoProvider menuDaoProvider) {
		this.implementation = new InMemoryMenuRepository(menuDaoProvider.getImplementation());
	}

	public MenuRepository getImplementation() {
		return implementation;
	}
	
}
