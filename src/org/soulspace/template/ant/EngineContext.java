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
