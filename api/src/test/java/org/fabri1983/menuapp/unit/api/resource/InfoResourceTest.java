package org.fabri1983.menuapp.unit.api.resource;

import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;
import org.fabri1983.menuapp.api.resource.InfoResource;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
	public void whenRequestBuildinfoThenMessageExpected() {
		final String buildInfoString = "ajhd239xbjsd9 dev. Build at xx:xx:xx xxxxx";
		when(buildInfoConfig.getBuildInfo()).thenReturn(buildInfoString);
		
		Response response = resource.client().target("/info/buildinfo")
				.request().accept(MediaType.TEXT_PLAIN_TYPE).get();

		verify(buildInfoConfig).getBuildInfo();
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		assertThat(response.readEntity(String.class)).isEqualTo(buildInfoString);
	}
	
	@Test
	public void whenRequestProfileThenMessageExpected() {
		final String buildProfileString = "testingProfile AA";
		when(buildInfoConfig.getBuildProfile()).thenReturn(buildProfileString);
		
		Response response = resource.client().target("/info/profile")
				.request().accept(MediaType.TEXT_PLAIN_TYPE).get();

		verify(buildInfoConfig).getBuildProfile();
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		assertThat(response.readEntity(String.class)).isEqualTo(buildProfileString);
	}
	
}
