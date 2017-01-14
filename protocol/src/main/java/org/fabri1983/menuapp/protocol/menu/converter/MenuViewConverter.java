package org.fabri1983.menuapp.protocol.menu.converter;

import org.fabri1983.menuapp.core.converter.menu.MenuConvertable;
import org.fabri1983.menuapp.core.converter.menu.visitor.MenuConverterVisitor;
import org.fabri1983.menuapp.protocol.menu.MenuView;

public interface MenuViewConverter {

	MenuView convert(MenuConvertable menu);

	MenuConverterVisitor getVisitor();
	
}
