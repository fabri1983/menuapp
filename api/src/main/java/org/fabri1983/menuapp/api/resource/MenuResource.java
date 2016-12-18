package org.fabri1983.menuapp.api.resource;

import java.util.Collection;
import java.util.List;

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

import org.fabri1983.menuapp.api.config.hasfeature.HasMenuQueryFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.MenuQueryConfig;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.core.service.MenuService;
import org.fabri1983.menuapp.protocol.menu.MenuView;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuGroupView;
import org.fabri1983.menuapp.protocol.menu.filtering.factory.MenuFiltersFactory;
import org.fabri1983.menuapp.protocol.menu.filtering.factory.MenuGroupFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/user/{user_id}/menu")
@Api(value = "MenuResource")
public class MenuResource {

	private MenuService menuService;
	private MenuQueryConfig menuQueryConfig;
	
	@Inject
	public MenuResource (MenuService menuService, HasMenuQueryFeature hasMenuQueryFeature) {
		this.menuService = menuService;
		this.menuQueryConfig = hasMenuQueryFeature.getMenuQueryConfig();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Returns all existent menus. Capped to max # of results", response = MenuView.class, responseContainer = "List")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of all menus", response = MenuView.class, responseContainer = "List")}
	    )
	public Response getAll ()
	{
		Collection<Menu> allMenus = menuService.getAll();
		List<MenuView> menuViews = MenuView.convert(allMenus, menuQueryConfig.getMaxAllowedResults());
		return Response.status(Response.Status.OK).entity(menuViews).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Returns the requested menu", response = MenuView.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of menu", response = MenuView.class),
	        @ApiResponse(code = 404, message = "Menu not found")}
	    )
	@Path("/{menu_id}")
	public Response getSingle (
			@PathParam("menu_id") long menuId) throws Exception 
	{
		Menu menu = menuService.getById(menuId);
		MenuView menuPresentation = MenuView.convert(menu);
		return Response.status(Response.Status.OK).entity(menuPresentation).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Returns menus satisfying the filter supplied", response = MenuView.class, responseContainer = "List")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of filtered menus", response = MenuView.class, responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Invalid filter format")}
	    )
	@Path("/filter")
	public Response filter (
			@NotNull @Valid MenuFiltersView menuFilteredView)
	{
		MenuFilterStrategy filterChain = MenuFiltersFactory.createFrom(menuFilteredView);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = Math.min(menuFilteredView.getMaxResults(), menuQueryConfig.getMaxAllowedResults());
		List<MenuView> menuViews = MenuView.convert(filteredMenus, limitSize);
		
		return Response.status(Response.Status.OK).entity(menuViews).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Returns menus satisfying the group supplied", response = MenuView.class, responseContainer = "List")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of grouped menus", response = MenuView.class, responseContainer = "List"),
	        @ApiResponse(code = 400, message = "Invalid group format")}
	    )
	@Path("/group")
	public Response group (
			@NotNull @Valid MenuGroupView menuGroupView)
	{
		MenuFilterStrategy filterChain = MenuGroupFactory.createFrom(menuGroupView);
		Collection<Menu> filteredMenus = menuService.getAllFiltered(filterChain);
		
		int limitSize = Math.min(menuGroupView.getMaxResults(), menuQueryConfig.getMaxAllowedResults());
		List<MenuView> menuViews = MenuView.convert(filteredMenus, limitSize);
		
		return Response.status(Response.Status.OK).entity(menuViews).build();
	}
	
	@DELETE
	@Timed
	@ApiOperation(value = "Deletes the requested menu")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful deletion of menu"),
	        @ApiResponse(code = 404, message = "Menu not found")}
	    )
	@Path("/{menu_id}")
	public Response delete (
			@PathParam("menu_id") long menuId)
	{
		menuService.delete(menuId);
		return Response.status(Response.Status.OK).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Updates a menu")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful update of menu"),
	        @ApiResponse(code = 404, message = "Menu not found")}
	    )
	@Path("/{menu_id}")
	public Response replace (
			@PathParam("menu_id") long menuId,
			@NotNull @Valid MenuView menuUpdated)
	{
		// FIXME call core api for updating requested menu id
		
		return Response.status(Response.Status.OK).build();
	}
}
