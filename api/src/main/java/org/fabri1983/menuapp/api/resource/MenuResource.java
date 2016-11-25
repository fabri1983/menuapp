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
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.api.provider.MenuServiceProvider;
import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.service.MenuService;
import org.fabri1983.menuapp.core.view.MenuView;
import org.fabri1983.menuapp.core.view.converter.MenuViewConverterResolver;
import org.fabri1983.menuapp.protocol.factory.MenuFilteringFactory;
import org.fabri1983.menuapp.protocol.factory.MenuGroupingFactory;
import org.fabri1983.menuapp.protocol.filtering.MenuFilteredView;
import org.fabri1983.menuapp.protocol.grouping.MenuGroupView;
import org.fabri1983.menuapp.protocol.menu.MenuResponse;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

@Path("user/{user_id}/menu")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MenuResource {

	private MenuService menuService;
	private int maxAllowedResults;
	
	@Inject
	public MenuResource (MenuServiceProvider menuServiceProvider, MenuAppConfiguration configuration) {
		this.menuService = menuServiceProvider.getImplementation();
		this.maxAllowedResults = configuration.getMaxAllowedResults();
	}
	
	@GET
	@Timed
	public Response getAll ()
	{
		Collection<Menu> allMenus = menuService.getAll();
		
		List<MenuView> menuViews = allMenus.stream()
				.limit(maxAllowedResults)
				.map( menu -> MenuViewConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		MenuResponse menuResponse = MenuResponse.create(menuViews);
		return Response.status(Response.Status.OK).entity(menuResponse).build();
	}
	
	@GET
	@Timed
	@Path("/{menu_id}")
	public Response get (
			@PathParam("menu_id") long menuId) throws Exception 
	{
		Menu menu = menuService.getById(menuId);
		MenuView menuPresentation = MenuViewConverterResolver.convert(menu);
		return Response.status(Response.Status.OK).entity(menuPresentation).build();
	}
	
	@POST
	@Timed
	@Path("/filter")
	public Response filter (
			@NotNull @Valid MenuFilteredView menuFilteredView)
	{
		MenuFilter filterChain = MenuFilteringFactory.createFrom(menuFilteredView);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = Math.min(menuFilteredView.getMaxResults(), maxAllowedResults);
		
		List<MenuView> menusView = filteredMenus.stream()
				.limit(limitSize)
				.map( menu -> MenuViewConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		MenuResponse menuResponse = MenuResponse.create(menusView);
		return Response.status(Response.Status.OK).entity(menuResponse).build();
	}
	
	@POST
	@Timed
	@Path("/group")
	public MenuResponse group (
			@NotNull @Valid MenuGroupView menuGroupView)
	{
		MenuFilter filterChain = MenuGroupingFactory.createFrom(menuGroupView);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = Math.min(menuGroupView.getMaxResults(), maxAllowedResults);
		
		List<MenuView> menusPresentation = filteredMenus.stream()
				.limit(limitSize)
				.map( menu -> MenuViewConverterResolver.convert(menu) )
				.collect(Collectors.toList());
		
		return new MenuResponse(menusPresentation);
	}
	
	@DELETE
	@Timed
	@Path("/{menu_id}")
	public void delete (
			@PathParam("menu_id") long menuId)
	{
		menuService.delete(menuId);
	}
	
	@PUT
	@Timed
	@Path("/{menu_id}")
	public Response replace (
			@PathParam("menu_id") long menuId,
			@NotNull @Valid MenuView menuUpdated)
	{
		// FIXME call core api for updating requested menu id
		
		return Response.status(Response.Status.OK).build();
	}
}
