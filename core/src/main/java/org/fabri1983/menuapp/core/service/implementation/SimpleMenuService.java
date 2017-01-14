package org.fabri1983.menuapp.core.service.implementation;

import java.util.Collection;
import java.util.stream.Collectors;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.core.repository.MenuRepository;
import org.fabri1983.menuapp.core.service.MenuService;

public class SimpleMenuService implements MenuService {

	MenuRepository repository;
	
	public SimpleMenuService(MenuRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Collection<Menu> getAll() {
		return repository.getAll();
	}

	@Override
	public Menu getById(long menuId) throws Exception {
		Menu menu = repository.getById(menuId);
		// FIXME use Optional<T>
		if (menu == null)
			throw new Exception(String.format("Menu with id %d doesn't exist.", menuId));
		return menu;
	}

	@Override
	public Menu getByName(String menuName) {
		return repository.getByName(menuName);
	}

	@Override
	public Menu add(Menu newMenu) {
		// FIXME validate the new menu, if not already validated on previous step
		return repository.add(newMenu);
	}

	@Override
	public void delete(long menuId) {
		repository.delete(menuId);
	}

	@Override
	public Menu update(Menu modifiedMenu) {
		// FIXME validate the modified menu, if not already validated on previous step
		return repository.update(modifiedMenu);
	}

	@Override
	public Collection<Menu> getAllFiltered(final MenuFilterStrategy filter) {
		// FIXME This is not performance friendly. The filtering should be done in the ORM layer.
		// However, if your repository/dao components as using a cache then your are on good track.
		Collection<Menu> allMenus = repository.getAll();
		
	    // taking advantage of Java 8 streams and lambda expression
		Collection<Menu> filteredMenus = allMenus.stream().filter( menu -> filter.filter(menu) ).collect(Collectors.toList());
	    
		// using apache commons collections 4
//		Collection<Menu> filteredMenus = CollectionUtils.select(allMenus, new Predicate<Menu>() {
//	        @Override
//	        public boolean evaluate(final Menu menu) {
//	            return filter.accepts(menu);
//	        }
//	    });
		
		return filteredMenus;
	}

}
