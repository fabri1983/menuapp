package org.fabri1983.menuapp.testutil;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test rule providing a logger per executing test.
 * Use in your test class as follow:
 * <pre class="code"><code class="java">
 *    &#64;Rule
 *    public TestLogger logger = new TestLogger();
 *    
 *    &#64;Test
 *    public void someTest() {
 *       final Logger log = logger.getLogger();
 *       ...
 *    }
 * </code></pre>
 */
public class TestLogger implements TestRule {
	private Logger logger;

	public Logger getLogger() {
		return this.logger;
	}

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				String loggerName = description.getTestClass().getName() + '.' + description.getDisplayName();
				logger = LoggerFactory.getLogger(loggerName);
				base.evaluate();
			}
		};
	}
}
