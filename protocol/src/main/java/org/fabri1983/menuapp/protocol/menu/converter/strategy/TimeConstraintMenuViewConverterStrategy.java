package org.fabri1983.menuapp.protocol.menu.converter.strategy;

import org.fabri1983.menuapp.core.converter.menu.MenuConvertable;
import org.fabri1983.menuapp.core.converter.menu.visitor.MenuConverterVisitor;
import org.fabri1983.menuapp.core.converter.menu.visitor.TimeConstraintMenuTypeConverterVisitor;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.protocol.menu.MenuView;
import org.fabri1983.menuapp.protocol.menu.converter.MenuViewConverter;
import org.fabri1983.menuapp.protocol.menu.factory.MenuViewFactory;

public class TimeConstraintMenuViewConverterStrategy implements MenuViewConverter {

	private MenuConverterVisitor visitor;
	
	public TimeConstraintMenuViewConverterStrategy() {
		visitor = new TimeConstraintMenuTypeConverterVisitor();
	}
	
	@Override
	public MenuView convert(MenuConvertable menu) {
		TimeConstraintMenu sourceMenu = (TimeConstraintMenu) menu;
		return MenuViewFactory.from(sourceMenu);
	}

	@Override
	public MenuConverterVisitor getVisitor() {
		return visitor;
	}

}
