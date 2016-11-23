package org.fabri1983.menuapp.protocol.menu;

import java.util.List;

import org.fabri1983.menuapp.core.view.MenuView;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuResponse {
	
	@JsonProperty
	private List<MenuView> menus;

	public MenuResponse (List<MenuView> menus) {
		this.menus = menus;
	}

	public static MenuResponse create (List<MenuView> menus) {
		return new MenuResponse(menus);
	}
	
	public List<MenuView> getMenus () {
		return menus;
	}

}
