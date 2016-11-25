package org.fabri1983.menuapp.api.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.api.provider.RatingServiceProvider;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.protocol.rating.RatingResponse;
import org.fabri1983.menuapp.protocol.rating.RatingView;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

@Path("user/{user_id}/menu/{menu_id}/rate")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class RatingResource {

	private RatingService ratingService;

	@Inject
	public RatingResource (RatingServiceProvider ratingServiceProvider) {
		this.ratingService = ratingServiceProvider.getImplementation();
	}
	
	@POST
	@Timed
	public Response rate(
			@PathParam("menu_id") long menuId,
			@NotNull @Valid RatingView ratingView)
	{
		Menu menuUpdated = ratingService.updateRating(menuId, ratingView.getRating());
		
		RatingResponse ratingResponse = RatingResponse.create(menuUpdated);
		
		return Response.status(Response.Status.OK).entity(ratingResponse).build();
	}
}
