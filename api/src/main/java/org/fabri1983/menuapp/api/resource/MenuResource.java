package org.fabri1983.menuapp.api.resource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fabri1983.menuapp.api.provider.MenuServiceProvider;
import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.presentation.MenuPresentation;
import org.fabri1983.menuapp.core.presentation.converter.MenuPresentationConverterResolver;
import org.fabri1983.menuapp.core.service.MenuService;
import org.fabri1983.menuapp.protocol.core.MenuResponse;
import org.fabri1983.menuapp.protocol.factory.MenuFilteringFactory;
import org.fabri1983.menuapp.protocol.factory.MenuGroupingFactory;
import org.fabri1983.menuapp.protocol.filtering.MenuFilteredRequest;
import org.fabri1983.menuapp.protocol.grouping.MenuGroupRequest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

@Path("user/{user_id}/menu")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MenuResource {

	private MenuService menuService;

	@Inject
	public MenuResource (MenuServiceProvider menuServiceProvider) {
		this.menuService = menuServiceProvider.getImplementation();
	}
	
	@GET
	@Timed
	public MenuResponse getAll (
			@PathParam("user_id") @NotNull Long userId
	) {
		Collection<Menu> allMenus = menuService.getAll();
		
		// FIXME only map a max amount of menus to avoid a big response
		List<MenuPresentation> menusPresentation = allMenus.stream()
				.map( menu -> MenuPresentationConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		return new MenuResponse(menusPresentation);
	}
	
	@GET
	@Timed
	@Path("/{menu_id}")
	public MenuPresentation get (
			@PathParam("user_id") @NotNull Long userId,
			@PathParam("menu_id") @NotNull Long menuId
	) throws Exception {
		Menu menu = menuService.getById(menuId);
		MenuPresentation menuPresentation = MenuPresentationConverterResolver.convert(menu);
		return menuPresentation;
	}
	
	@POST
	@Timed
	@Path("/filter")
	public MenuResponse filter (
			@PathParam("user_id") @NotNull Long userId,
			@Valid MenuFilteredRequest menuFilteredRequest
	) {
		MenuFilter filterChain = MenuFilteringFactory.createFrom(menuFilteredRequest);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = menuFilteredRequest.getMaxResults();
		List<MenuPresentation> menusPresentation = filteredMenus.stream()
				.limit(limitSize)
				.map( menu -> MenuPresentationConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		return new MenuResponse(menusPresentation);
	}
	
	@POST
	@Timed
	@Path("/group")
	public MenuResponse group (
			@PathParam("user_id") @NotNull Long userId,
			@Valid MenuGroupRequest menuGroupRequest
	) {
		MenuFilter filterChain = MenuGroupingFactory.createFrom(menuGroupRequest);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = menuGroupRequest.getMaxResults();
		List<MenuPresentation> menusPresentation = filteredMenus.stream()
				.limit(limitSize)
				.map( menu -> MenuPresentationConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		return new MenuResponse(menusPresentation);
	}
	
	@DELETE
	@Timed
	@Path("/{menu_id}")
	public void delete (
			@PathParam("user_id") @NotNull Long userId,
			@PathParam("menu_id") @NotNull Long menuId
	) {
		menuService.delete(menuId);
	}
	
	@PUT
	@Timed
	@Path("/{menu_id}")
	public MenuPresentation replace (
			@PathParam("user_id") @NotNull Long userId,
			@PathParam("menu_id") @NotNull Long menuId,
			@Valid MenuPresentation menuUpdated
	) {
		// FIXME call core api for updating requested menu id
		
		return null;
	}
}
