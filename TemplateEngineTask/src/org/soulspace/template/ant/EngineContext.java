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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineContext {

	private List<Generator> generators = new ArrayList<Generator>();
	private List<String> templateDirs;
	private Map<String, String> paramMap = new HashMap<String, String>();
	
	public List<Generator> getGenerators() {
		return generators;
	}
	
	void addGenerator(Generator generator) {
		generators.add(generator);
	}
	
	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
	}
	
	public List<String> getTemplateDirs() {
		return templateDirs;
	}

	public void setTemplateDirs(List<String> templateDirs) {
		this.templateDirs = templateDirs;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public void addParam(Param param) {
		paramMap.put(param.getName(), param.getValue());
	}

	public void callGenerators() {
		for(Generator g : generators) {
			g.generate(this);
		}
	}
	
}
