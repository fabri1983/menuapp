package org.fabri1983.menuapp.core.filtering.menu.strategy;

import org.fabri1983.menuapp.core.entity.menu.Menu;

public interface MenuFilterStrategy {

	boolean accepts(Menu menu);
}
