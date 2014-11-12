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
