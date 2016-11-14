package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.dropwizard.testing.junit.ResourceTestRule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResourceTest {

	@ClassRule
	public static final ResourceTestRule resource = ResourceTestRule.builder()
										.addResource(new TestResource("test message"))
										.build();

	@Test
	public void whenTestResourceThenDummyMessageExpected () {
		String response = resource.client().target("/test").request().get(String.class);

		assertThat(response).isEqualTo("test message");
	}
}
