package org.fabri1983.menuapp.core.converter.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public class TimeConstraintMenuTypeConverterVisitor implements MenuConverterVisitor {

	@Override
	public boolean accepts(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean accepts(TimeConstraintMenu menu) {
		return true;
	}

}
