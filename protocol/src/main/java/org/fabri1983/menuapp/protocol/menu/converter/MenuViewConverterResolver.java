package org.fabri1983.menuapp.protocol.menu.converter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.protocol.exception.ProtocolValidationException;
import org.fabri1983.menuapp.protocol.menu.MenuView;
import org.fabri1983.menuapp.protocol.menu.converter.strategy.DefaultMenuViewConverterStrategy;
import org.fabri1983.menuapp.protocol.menu.converter.strategy.TimeConstraintMenuViewConverterStrategy;
import org.fabri1983.menuapp.protocol.validation.ValidationMessage;

/**
 * A {@link Menu} cannot be converted to a {@link MenuView} on <b>core</b> module since 
 * the core doesn't know anything about views. So this resolver class needs to test out 
 * which converter is the appropriate for a given menu.
 */
public class MenuViewConverterResolver {

	static List<MenuViewConverter> converters = Arrays.asList(
			new TimeConstraintMenuViewConverterStrategy(),
			new DefaultMenuViewConverterStrategy());
	
	public static MenuView convert(final Menu menu) {
		
		Optional<MenuViewConverter> converter = converters.stream()
												.filter( c -> menu.applyConverter(c.getVisitor()))
												.findFirst();
		return converter.orElseThrow(
				() -> new ProtocolValidationException(ValidationMessage.NO_CONVERTER_FOR_THE_MENU))
				.convert(menu);
	}

	public static List<MenuView> convert(Collection<Menu> menus, int limitSize) {
		return menus.stream()
				.limit(limitSize)
				.map( MenuViewConverterResolver::convert )
				.collect(Collectors.toList());
	}
}
