package org.fabri1983.menuapp.core.view.converter;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.view.MenuView;

/**
 * State less hence thread safe converter. Access it using {@link DefaultMenuConverter#get()}.
 */
public class DefaultMenuConverter implements MenuViewConverter {

	private static DefaultMenuConverter instance = new DefaultMenuConverter();
	
	private DefaultMenuConverter () {
	}
	
	public static MenuViewConverter get () {
		return instance;
	}
	
	@Override
	public MenuView convert(Menu menu) {
		// TODO investigate if is possible to use type inference using <T> since at the point
		// this method is executed by the MenuPresentationConverterResolver the subtype of menu is known.
		
		DefaultMenu sourceMenu = (DefaultMenu) menu;
		return MenuView.from(sourceMenu);
	}

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return true;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return false;
	}

}
