package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.dropwizard.testing.junit.ResourceTestRule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfoResourceTest {

	@ClassRule
	public static final ResourceTestRule resource = ResourceTestRule.builder()
										.addResource(new InfoResource("buildinfo", "profile"))
										.build();

	@Test
	public void whenRequestBuildinfoThenMessageExpected () {
		String response = resource.client().target("/buildinfo").request().get(String.class);

		assertThat(response).isEqualTo("buildinfo");
	}
	
	@Test
	public void whenRequestProfileThenMessageExpected () {
		String response = resource.client().target("/profile").request().get(String.class);

		assertThat(response).isEqualTo("profile");
	}
}
