package org.fabri1983.menuapp.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite intended to force execution order of other suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		UtilsTestsSuite.class
	})
public class UnitTestsSuite {
	
}
