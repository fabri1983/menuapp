package org.fabri1983.menuapp.protocol.menu.converter.strategy;

import org.fabri1983.menuapp.core.converter.menu.MenuConvertable;
import org.fabri1983.menuapp.core.converter.menu.visitor.DefaultMenuTypeConverterVisitor;
import org.fabri1983.menuapp.core.converter.menu.visitor.MenuConverterVisitor;
import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.protocol.menu.MenuView;
import org.fabri1983.menuapp.protocol.menu.converter.MenuViewConverter;
import org.fabri1983.menuapp.protocol.menu.factory.MenuViewFactory;

public class DefaultMenuViewConverterStrategy implements MenuViewConverter {
	
	private MenuConverterVisitor visitor;
	
	public DefaultMenuViewConverterStrategy() {
		visitor = new DefaultMenuTypeConverterVisitor();
	}
	
	@Override
	public MenuView convert(MenuConvertable menu) {
		DefaultMenu sourceMenu = (DefaultMenu) menu;
		return MenuViewFactory.from(sourceMenu);
	}

	@Override
	public MenuConverterVisitor getVisitor() {
		return visitor;
	}

}
