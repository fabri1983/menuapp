package org.fabri1983.menuapp.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite intended to force execution order:
 *   first run the {@link BenchmarkTestsClasses}
 *   then run the cleaning up test @{link BenchmarkJMHCleanupClass}
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		BenchmarkTestsClasses.class,
		BenchmarkJMHCleanupClass.class
	})
public class BenchmarkTestsSuite {

}
