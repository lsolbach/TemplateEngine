package org.soulspace.template.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.soulspace.util.CollectionUtils;

public class TemplateEngineTask extends Task {

	private EngineContext ctx = new EngineContext();

	public void setTemplateDirs(String templateDirs) {
		String[] dirs = templateDirs.split(",");
		ctx.setTemplateDirs(CollectionUtils
				.asArrayList(dirs));
	}
	
	public void addConfiguredParam(Param param) {
		ctx.addParam(param);
	}

	public void addGenerator(Generator generator) {
		ctx.addGenerator(generator);
	}
	
	@Override
	public void execute() throws BuildException {
		try {
			ctx.callGenerators();
		} catch (Exception e) {
			throw new BuildException("error while generating!", e);
		}
	}

}
