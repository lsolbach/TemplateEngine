/*
 * Created on May 4, 2004
 *
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
    long maxElapsedTime = 50;
    
    Test testCase = new TemplateEngineTest("testAstGenMethod3");
    Test timedTest = new TimedTest(testCase, maxElapsedTime);
    
    return timedTest;
  }

	public static void main(String[] args) {
    TestRunner.run(suite());
	}
}
