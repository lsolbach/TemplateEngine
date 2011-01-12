package org.soulspace.template.ant;

public class TemplateEngineTaskTest {

	public static void main(String[] args) {
		TemplateEngineTask task = new TemplateEngineTask();
		task.setTemplateDirs("../MdaTemplates/std-templates,../MdaTemplates/templates");
		task.addConfiguredParam(new Param("module", "Test"));
		Generator gen = new Generator();
		gen.addConfiguredParam(new Param("module", "Test"));
		gen.setTemplate("component/test");
		gen.setTarget("build/test/test.txt");
		gen.setImports("lib");
		task.addGenerator(gen);
		task.execute();
	}
}
