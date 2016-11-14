package org.fabri1983.menuapp.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestResource {

	private String message;
	
	@Inject
	public TestResource (@Named("testMessage") String message) {
		this.message = message;
	}
	
	@GET
    @Timed
	public String get() {
		return message;
	}
}
