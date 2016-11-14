package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;

@Ignore("There is an issue related to jersey and guice. See https://github.com/HubSpot/dropwizard-guice/issues/95#issuecomment-253551467")
public class TestResourceTest {

	@ClassRule
	public static final ResourceTestRule resource = ResourceTestRule.builder()
										.addResource(new TestResource("test message"))
										.build();

	@Test
	public void testDummyResource() {
		String response = resource.client().target("/test").request().get(String.class);

		assertThat(response).isEqualTo("test message");
	}
}
