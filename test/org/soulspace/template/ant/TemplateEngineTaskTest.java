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
