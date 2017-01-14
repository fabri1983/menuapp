package org.fabri1983.menuapp.core.filtering.menu;

import org.fabri1983.menuapp.core.filtering.menu.visitor.FilterVisitor;

public interface MenuFilterable {

	boolean applyFilter(FilterVisitor filterVisitor);
	
}
