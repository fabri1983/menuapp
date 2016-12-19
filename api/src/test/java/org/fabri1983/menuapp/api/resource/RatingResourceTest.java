package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.protocol.rating.RatingAppliedView;
import org.fabri1983.menuapp.protocol.rating.RatingView;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.dropwizard.jersey.validation.ValidationErrorMessage;
import io.dropwizard.testing.junit.ResourceTestRule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RatingResourceTest {

	private static final String msgInvalidMinRating = "rating must be greater than or equal to 1";
	private static final String msgInvalidMaxRating = "rating must be less than or equal to 5";
	private static final String msgInvalidEmptyDescription = "description may not be empty";
	private static final String msgInvalidDescriptionLength = "description length must be between 5 and 255";
	
	private static RatingService ratingService = mock(RatingService.class);
	
	@ClassRule
    public static final ResourceTestRule resource = ResourceTestRule.builder()
    	.addResource(new RatingResource(ratingService))
    	.build();
	
	@Test
	public void _1_whenRatingWithInvalidMinValueThenErrorExpected () throws MalformedURLException {
		RatingView ratingView = new RatingView(0, "dummy description");
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", 1))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidMinRating);
	}
	
	@Test
	public void _2_whenRatingWithInvalidMaxValueThenErrorExpected () throws MalformedURLException {
		RatingView ratingView = new RatingView(6, "dummy description");
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", 1))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidMaxRating);
	}
	
	@Test
	public void _3_whenRatingWithEmptyDescriptionThenErrorExpected () throws MalformedURLException {
		RatingView ratingView = new RatingView(1, "");
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", 1))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidEmptyDescription, msgInvalidDescriptionLength);
	}
	
	@Test
	public void _4_whenRatingWithInvalidMinLengthDescriptionThenErrorExpected () throws MalformedURLException {
		RatingView ratingView = new RatingView(1, "a");
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", 1))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidDescriptionLength);
	}
	
	@Test
	public void _5_whenRatingWithInvalidMinLengthDescriptionThenErrorExpected () throws MalformedURLException {
		RatingView ratingView = new RatingView(1, stringOfLength(256, 'a'));
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", 1))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		assertThat(response.getStatus()).isEqualTo(422);
		
		ValidationErrorMessage msg = response.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).containsOnly(msgInvalidDescriptionLength);
	}
	
	@Test
	public void _6_whenRatingAMenuThenRatingIsApplied () throws MalformedURLException {
		int rating = 3;
		long menuId = 2L;
		Menu menuUpdated = createDefaultMenu(menuId, rating);
		when(ratingService.updateRating(menuUpdated.getId(), menuUpdated.getRating())).thenReturn(menuUpdated);
		
		RatingView ratingView = new RatingView(rating, "dummy description");
		
		Response response = resource.client().target(String.format("/user/12345/menu/%s/rate", menuId))
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(ratingView, MediaType.APPLICATION_JSON_TYPE));
		
		verify(ratingService).updateRating(menuUpdated.getId(), menuUpdated.getRating());
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		
		RatingAppliedView ratingAppliedView = response.readEntity(RatingAppliedView.class);
		assertThat(ratingAppliedView.getRating()).isEqualTo(rating);
	}
	
	private Menu createDefaultMenu (long menuId, int rating) throws MalformedURLException {
		return new DefaultMenu(menuId, "Cheapest Dinner", "Are you saving some dimes?", new URL("http://fabri1983.org/images/dinnerCheap.png"), 
				BigDecimal.valueOf(80), CurrencyType.DEFAULT_USD, rating);
	}
	

	/**
	 * Creates a string of a given capacity containing only the given character.
	 *
	 * @param length
	 *            The capacity of the string.
	 * @param replacement
	 *            Repeated character all along the string.
	 */
	public String stringOfLength (final int length, final char replacement) {
		return CharBuffer.allocate(length).toString().replace('\0', replacement);
	}
}
