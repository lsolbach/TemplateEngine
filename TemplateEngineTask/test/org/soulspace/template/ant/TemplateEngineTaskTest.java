/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.ant;

public class TemplateEngineTaskTest {

	public static void main(String[] args) {
		TemplateEngineTaskTest tet = new TemplateEngineTaskTest();
		tet.test1();
	}

	void test1() {
		TemplateEngineTask task = new TemplateEngineTask();
		task.setTemplateDirs("../MdaTemplates/std-templates2,../MdaTemplates/test");
		Generator gen = new Generator();
		gen.setTemplate("test");
		gen.setTarget("build/test/test.txt");
		gen.setImports("lib");
		task.addGenerator(gen);
		task.execute();
	}
}
