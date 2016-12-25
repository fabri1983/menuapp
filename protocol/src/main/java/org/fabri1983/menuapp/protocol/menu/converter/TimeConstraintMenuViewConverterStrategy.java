package org.fabri1983.menuapp.protocol.menu.converter;

import org.fabri1983.menuapp.core.converter.menu.MenuTypeConverterRequester;
import org.fabri1983.menuapp.core.converter.menu.TimeConstraintMenuTypeConverterRequester;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

/**
 * State less, hence thread safe converter. Access it using {@link TimeConstraintMenuViewConverterStrategy#get()}.
 */
class TimeConstraintMenuViewConverterStrategy implements MenuViewConverterStrategy {

	private static TimeConstraintMenuViewConverterStrategy instance = new TimeConstraintMenuViewConverterStrategy();
	
	private MenuTypeConverterRequester converterStrategy;
	
	private TimeConstraintMenuViewConverterStrategy() {
		converterStrategy = new TimeConstraintMenuTypeConverterRequester();
	}
	
	public static MenuViewConverterStrategy get() {
		return instance;
	}
	
	@Override
	public MenuView convert(Menu menu) {
		// TODO investigate if is possible to use type inference using <T> since at the very moment
		// this method is executed by the MenuPresentationConverterResolver the subtype of menu is already known.
		
		TimeConstraintMenu sourceMenu = (TimeConstraintMenu) menu;
		return MenuView.from(sourceMenu);
	}

	@Override
	public MenuTypeConverterRequester getStrategy() {
		return converterStrategy;
	}

}
