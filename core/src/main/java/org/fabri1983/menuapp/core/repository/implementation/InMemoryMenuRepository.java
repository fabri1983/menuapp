package org.fabri1983.menuapp.core.repository.implementation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.converter.MenuDaoPresentationConverter;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.repository.MenuRepository;

public class InMemoryMenuRepository implements MenuRepository {

	private Map<Long, Menu> menusById;
	private Map<String, Menu> menusByName;
	private MenuDao dao;
	
	public InMemoryMenuRepository (MenuDao dao) {
		menusById = new HashMap<Long, Menu>();
		menusByName = new HashMap<String, Menu>();
		this.dao = dao;
		loadMenus();
	}
	
	private void loadMenus() {
		MenuDaoPresentationConverter converter = dao.getConverter();
		List<Menu> menus = dao.getAll().stream().map( menuPresentation -> converter.convert(menuPresentation) ).collect(Collectors.toList());
		menusById = menus.stream().collect(Collectors.toMap(Menu::getId, Function.identity()));
		menusByName = menus.stream().collect(Collectors.toMap(Menu::getName, Function.identity()));
	}

	@Override
	public Collection<Menu> getAll() {
		return CollectionUtils.unmodifiableCollection(menusById.values());
	}

	@Override
	public Menu getById(long menuId) {
		// FIXME there is an implicit boxing of long parameter to Long. Better use Fast Util api or similar
		return menusById.get(menuId);
	}
	
	@Override
	public Menu getByName(String menuName) {
		// TODO if no menu already exist then use the dao to retrieve it
		return menusByName.get(menuName);
	}

	@Override
	public Menu add(Menu newMenu) {
		// TODO modify the newMenu id from an unique id provider
		menusById.put(newMenu.getId(), newMenu);
		menusByName.put(newMenu.getName(), newMenu);
		return newMenu;
	}

	@Override
	public void delete(long menuId) {
		Menu menu = getById(menuId);
		if (menu == null)
			return;
		menusById.remove(menu.getName());
		// FIXME there is an implicit boxing of long parameter to Long. Better use Fast Util api or similar
		menusById.remove(menuId);
	}

	@Override
	public Menu update(Menu modifiedMenu) {
		menusById.put(modifiedMenu.getId(), modifiedMenu);
		menusByName.put(modifiedMenu.getName(), modifiedMenu);
		return modifiedMenu;
	}

}
