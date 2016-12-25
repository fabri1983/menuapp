package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.dropwizard.jersey.validation.ValidationErrorMessage;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.math.BigInteger;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.protocol.login.LocationView;
import org.fabri1983.menuapp.protocol.login.LoginSuccessfulView;
import org.fabri1983.menuapp.protocol.login.LoginView;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginResourceTest {

	private static final String msgInvalidEmptyUsername = "userName may not be empty";
	private static final String msgInvalidEmptyHashedPassword = "userPassHashed may not be empty";
	
	private static LoginService loginService = mock(LoginService.class);
	
	@ClassRule
    public static final ResourceTestRule resource = ResourceTestRule.builder()
    	.addResource(new LoginResource(loginService))
    	.build();
	
	@Test
	public void whenLoginInvalidUserNameThenErrorExpected() {
		LoginView loginView = new LoginView("", "pass-hashed", new LocationView(BigInteger.ZERO, BigInteger.ZERO));
		
		Response response = resource.client().target("/user/login")
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(loginView, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidEmptyUsername);
	}
	
	@Test
	public void whenLoginInvalidUserPassHashedThenErrorExpected() {
		LoginView loginView = new LoginView("dummy name", "", new LocationView(BigInteger.ZERO, BigInteger.ZERO));
		
		Response response = resource.client().target("/user/login")
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(loginView, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidEmptyHashedPassword);
	}
	
	@Test
	public void whenLoginValidUserThenTokenExpected() {
		final String token = "aslkjd8ewrkjsd89ewarkljsd";
		when(loginService.loginUser(any(), any())).thenReturn(token);
		
		LoginView loginView = new LoginView("dummy name", "pass-hashed", new LocationView(BigInteger.ZERO, BigInteger.ZERO));
		
		Response response = resource.client().target("/user/login")
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(loginView, MediaType.APPLICATION_JSON_TYPE));

		verify(loginService).loginUser(any(), any());
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		assertThat(response.readEntity(LoginSuccessfulView.class).getToken()).isEqualTo(token);
	}
	
}
