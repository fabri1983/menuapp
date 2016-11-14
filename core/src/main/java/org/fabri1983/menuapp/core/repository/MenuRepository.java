package org.fabri1983.menuapp.core.repository;

import java.util.Collection;

import org.fabri1983.menuapp.core.menu.Menu;

public interface MenuRepository {

	Collection<Menu> getAll();
	
	Menu getById(long menuId);
	
	Menu getByName(String menuName);
	
	Menu add (Menu newMenu);
	
	void delete(long menuId);
	
	Menu update(Menu modifiedMenu);
}
