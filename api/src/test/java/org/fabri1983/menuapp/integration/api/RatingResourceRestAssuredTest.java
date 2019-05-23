package org.fabri1983.menuapp.integration.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.net.MalformedURLException;
import java.nio.CharBuffer;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import org.fabri1983.menuapp.protocol.rating.RatingView;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore("Must run this test at integration test phase. First you need to instantiate a dropwizard server.")
public class RatingResourceRestAssuredTest {

	static final String msgInvalidMinRating = "rating must be greater than or equal to 1";
	static final String msgInvalidMaxRating = "rating must be less than or equal to 5";
	static final String msgInvalidEmptyDescription = "description may not be empty";
	static final String msgInvalidDescriptionLength = "description length must be between 5 and 255";
	
	@BeforeClass
	public static void setup() {
		// TODO move this into a class Rule. Use dropwizard's configuration for connection settings
		RestAssured.port = 8080;
		RestAssured.basePath = "/";
		RestAssured.baseURI = "http://localhost";
	}
	
	@Test
	public void whenRatingAMenuThenRatingIsApplied() throws MalformedURLException {
		int menuId = 2;
		int rating = 1;
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", menuId)
			.body(new RatingView(rating, "dummy description"))
		.when()
			.post("/user/{user_id}/rate/menu/{menu_id}")
		.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.body(
				"menuId", equalTo(menuId),
				"rating", equalTo(rating)
		);
	}
	
	@Test
	public void whenRatingWithEmptyDescriptionThenErrorExpected() throws MalformedURLException {
		RatingView ratingView = new RatingView(1, "");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", 1)
			.body(ratingView)
		.when()
			.post("/user/{user_id}/rate/menu/{menu_id}")
		.then()
			.statusCode(422)
			.body(
				// warning: contains is order dependent
				"errors", contains(msgInvalidDescriptionLength, msgInvalidEmptyDescription)
		);
	}

	@Test
	public void whenRatingWithInvalidMaxLengthDescriptionThenErrorExpected() throws MalformedURLException {
		RatingView ratingView = new RatingView(1, stringOfLength(256, 'a'));
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", 1)
			.body(ratingView)
		.when()
			.post("/user/{user_id}/rate/menu/{menu_id}")
		.then()
			.statusCode(422)
			.body(
				"errors", contains(msgInvalidDescriptionLength)
		);
	}

	@Test
	public void whenRatingWithInvalidMaxValueThenErrorExpected() throws MalformedURLException {
		RatingView ratingView = new RatingView(6, "rating dummy description");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", 1)
			.body(ratingView)
		.when()
			.post("/user/{user_id}/rate/menu/{menu_id}")
		.then()
			.statusCode(422)
			.body(
				"errors", contains(msgInvalidMaxRating)
		);
	}

	@Test
	public void whenRatingWithInvalidMinLengthDescriptionThenErrorExpected() throws MalformedURLException {
		RatingView ratingView = new RatingView(1, "a");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", 1)
			.body(ratingView)
		.when()
			.post("/user/{user_id}/rate/menu/{menu_id}")
		.then()
			.statusCode(422)
			.body(
				"errors", contains(msgInvalidDescriptionLength)
		);
	}

	@Test
	public void whenRatingWithInvalidMinValueThenErrorExpected() throws MalformedURLException {
		RatingView ratingView = new RatingView(0, "rating dummy description");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("user_id", 12345)
			.pathParam("menu_id", 1)
			.body(ratingView)
	    .when()
	    	.post("/user/{user_id}/rate/menu/{menu_id}")
	    .then()
			.statusCode(422)
			.body(
				"errors", contains(msgInvalidMinRating)
			);
	}

	/**
	 * Creates a string of a given capacity containing only the given character.
	 *
	 * @param length
	 *            The capacity of the string.
	 * @param replacement
	 *            Repeated character all along the string.
	 */
	private String stringOfLength(final int length, final char replacement) {
		return CharBuffer.allocate(length).toString().replace('\0', replacement);
	}
}
