package org.fabri1983.menuapp.testsuite;

import org.fabri1983.menuapp.guicefix.GuiceCompatibilityFixTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite intended to force execution order:
 *   first run the {@link GuiceCompatibilityFixTest}
 *   then run the remaining unit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		GuiceCompatibilityFixTest.class,
		ApiTestsSuite.class
	})
public class UnitTestsSuite {

	
}
