package org.fabri1983.menuapp.core.service;

import java.util.Collection;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;

public interface MenuService {

	Collection<Menu> getAll();
	
	Menu getById(long menuId) throws Exception;
	
	Menu getByName(String menuName);
	
	Menu add (Menu newMenu);
	
	void delete(long menuId);
	
	Menu update(Menu modifiedMenu);

	Collection<Menu> getAllFiltered(MenuFilterStrategy filter);
}
