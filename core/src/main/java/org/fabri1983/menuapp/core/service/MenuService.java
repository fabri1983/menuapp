package org.fabri1983.menuapp.core.service;

import java.util.Collection;

import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.menu.Menu;

public interface MenuService {

	Collection<Menu> getAll();
	
	Menu getById(long menuId) throws Exception;
	
	Menu getByName(String menuName);
	
	Menu add (Menu newMenu);
	
	void delete(long menuId);
	
	Menu update(Menu modifiedMenu);

	Collection<Menu> getAllFiltered(MenuFilter filter);
}
