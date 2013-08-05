/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template;

import junit.framework.Test;
import junit.textui.TestRunner;

import com.clarkware.junitperf.TimedTest;

/**
 * 
 * @author soulman
 */
public class TemplateEngineTimedTest {

	public static Test suite() {
		long maxElapsedTime = 250;

		Test testCase = new TemplateEngineTest("testAstGenMethod3");
		Test timedTest = new TimedTest(testCase, maxElapsedTime);

		return timedTest;
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
