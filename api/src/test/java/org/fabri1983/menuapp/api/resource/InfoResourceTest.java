package org.fabri1983.menuapp.api.resource;

import static org.mockito.Mockito.when;

import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfoResourceTest {

	@Mock(answer = Answers.RETURNS_MOCKS)
	private HasBuildInfoFeature hasBuildInfo;
	@InjectMocks
	private InfoResource infoResource;
	@Mock
	private BuildInfoConfig buildInfoConfig;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();	
//	@Rule
//	public ResourceTestRule resource = ResourceTestRule.builder()
//										.addResource(infoResource)
//										.build();

	@Before
	public void setup () {
		when(hasBuildInfo.getBuildInfoConfig()).thenReturn(buildInfoConfig);
	}
	
	@Test
	public void whenRequestBuildinfoThenMessageExpected () {
//		when(buildInfoConfig.getBuildInfo()).thenReturn("ajhd239xbjsd9 dev. Build at xx:xx:xx xxxxx");
//		
//		String response = resource.client().target("/buildinfo").request().get(String.class);
//
//		assertThat(response).isEqualTo("ajhd239xbjsd9 dev. Build at xx:xx:xx xxxxx");
	}
	
	@Test
	public void whenRequestProfileThenMessageExpected () {
//		when(buildInfoConfig.getBuildProfile()).thenReturn("testingProfile");
//		
//		String response = resource.client().target("/profile").request().get(String.class);
//
//		assertThat(response).isEqualTo("testingProfile");
	}
}
