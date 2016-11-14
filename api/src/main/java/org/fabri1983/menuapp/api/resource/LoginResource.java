package org.fabri1983.menuapp.api.resource;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fabri1983.menuapp.api.provider.LoginServiceProvider;
import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.protocol.login.LoginRequest;
import org.fabri1983.menuapp.protocol.login.LoginResponse;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

@Path("user/login")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LoginResource {

	private LoginService loginService;
	
	@Inject
	public LoginResource (LoginServiceProvider loginServiceProvider) {
		this.loginService = loginServiceProvider.getImplementation();
	}
	
	@POST
	@Timed
	public LoginResponse login ( 
			@Valid LoginRequest loginRequest
	) {
		loginService.loginUser(loginRequest.getUserName(), loginRequest.getUserPassHashed());
	
		// TODO store user location with an UserService or similar service
		
		return new LoginResponse(loginRequest.getUserName(), loginRequest.getLocation(), "Logged In!");
	}
}
