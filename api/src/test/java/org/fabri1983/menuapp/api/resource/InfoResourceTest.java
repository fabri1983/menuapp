package org.fabri1983.menuapp.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.dropwizard.testing.junit.ResourceTestRule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfoResourceTest {

	private static BuildInfoConfig buildInfoConfig;
	
	@ClassRule
    public static final ResourceTestRule resource = ResourceTestRule.builder()
    	.addResource(new InfoResource(new HasBuildInfoFeature(){
			@Override
			public BuildInfoConfig getBuildInfoConfig() {
				buildInfoConfig = mock(BuildInfoConfig.class);
				return buildInfoConfig;
			}
    	}))
    	.build();
	
	@Test
	public void whenRequestBuildinfoThenMessageExpected () {
		when(buildInfoConfig.getBuildInfo()).thenReturn("ajhd239xbjsd9 dev. Build at xx:xx:xx xxxxx");
		
		String response = resource.client().target("/buildinfo").request().get(String.class);

		assertThat(response).isEqualTo("ajhd239xbjsd9 dev. Build at xx:xx:xx xxxxx");
	}
	
	@Test
	public void whenRequestProfileThenMessageExpected () {
		when(buildInfoConfig.getBuildProfile()).thenReturn("testingProfile AA");
		
		String response = resource.client().target("/profile").request().get(String.class);

		assertThat(response).isEqualTo("testingProfile AA");
	}
}
