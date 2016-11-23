package org.fabri1983.menuapp.guicefix;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This test must be run first (use a test suite for it) before any other test in order to fix a jersey2-guice issue.
 * See https://github.com/HubSpot/dropwizard-guice/issues/95#issuecomment-253551467 
 */
@Ignore("Not neeed anymore since with dropwizard-guicey its fixed")
public class GuiceCompatibilityFixTest {

    @Test
    public void testBlank () {
        assertTrue(true);
    }

    @After
    public void tearDown () throws Exception {
//        com.squarespace.jersey2.guice.JerseyGuiceUtils.reset();
    }

}
