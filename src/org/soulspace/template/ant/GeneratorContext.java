package org.soulspace.template.ant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneratorContext {

	private Date timestamp = new Date();

	private String template = "";
	private String imports = "";
	private String target = "";
	private String encoding = "UTF-8";
	private Map<String, String> paramMap = new HashMap<String, String>();
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getImports() {
		return imports;
	}

	public void setImports(String imports) {
		this.imports = imports;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
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

	public String getTimestamp() {
		return timestamp.toString();
	}

}
