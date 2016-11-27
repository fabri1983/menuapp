package org.fabri1983.menuapp.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;

@Path("")
@Produces(MediaType.TEXT_PLAIN)
@RequestScoped
public class InfoResource {

	private String buildInfo;
	private String profile;
	
	@Inject
	public InfoResource (@Named("buildInfo") String buildInfo, @Named("profile") String profile) {
		this.buildInfo = buildInfo;
		this.profile = profile;
	}

	@GET
    @Timed
    @Path("/buildinfo")
	public Response getBuildInfo () {
		return Response.status(Response.Status.OK).entity(buildInfo).build();
	}
	
	@GET
    @Timed
    @Path("/profile")
	public Response getProfile () {
		return Response.status(Response.Status.OK).entity(profile).build();
	}
}
