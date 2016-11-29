package org.fabri1983.menuapp.protocol.converter;

import org.fabri1983.menuapp.core.converter.menu.DefaultMenuTypeConverterRequester;
import org.fabri1983.menuapp.core.converter.menu.MenuTypeConverterRequester;
import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

/**
 * State less, hence thread safe converter. Access it using {@link DefaultMenuViewConverterStrategy#get()}.
 */
public class DefaultMenuViewConverterStrategy implements MenuViewConverterStrategy {

	private static DefaultMenuViewConverterStrategy instance = new DefaultMenuViewConverterStrategy();
	
	private MenuTypeConverterRequester converterStrategy;
	
	private DefaultMenuViewConverterStrategy () {
		converterStrategy = new DefaultMenuTypeConverterRequester();
	}
	
	public static MenuViewConverterStrategy get () {
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
	public MenuTypeConverterRequester getStrategy () {
		return converterStrategy;
	}

}
