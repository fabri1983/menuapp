package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.implementation.PreloadedMenuDao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MenuDaoProvider {

	private MenuDao implementation;

	@Inject
	public MenuDaoProvider () {
		this.implementation = new PreloadedMenuDao();
	}
	
	public MenuDao getImplementation() {
		return implementation;
	}
}
