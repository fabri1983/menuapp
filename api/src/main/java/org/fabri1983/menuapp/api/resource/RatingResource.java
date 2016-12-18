package org.fabri1983.menuapp.api.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.protocol.rating.RatingAppliedView;
import org.fabri1983.menuapp.protocol.rating.RatingView;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("user/{user_id}/menu/{menu_id}")
@Api(value = "RatingResource")
public class RatingResource {

	private RatingService ratingService;

	@Inject
	public RatingResource (RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Rates the requested menu", response = RatingAppliedView.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful rate of menu", response = RatingAppliedView.class),
	        @ApiResponse(code = 404, message = "Menu not found")}
	    )
	@Path("/rate")
	public Response rate(
			@PathParam("menu_id") long menuId,
			@NotNull @Valid RatingView ratingView)
	{
		Menu menuUpdated = ratingService.updateRating(menuId, ratingView.getRating());
		RatingAppliedView ratingResponse = RatingAppliedView.create(menuUpdated);
		return Response.status(Response.Status.OK).entity(ratingResponse).build();
	}
}
