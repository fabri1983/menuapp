package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.converter.menu.visitor.MenuConverterVisitor;

public interface MenuConvertable {

	boolean applyConverter(MenuConverterVisitor converter);
	
}
