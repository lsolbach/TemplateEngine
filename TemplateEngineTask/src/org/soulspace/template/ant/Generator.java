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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.impl.BeanDataSourceImpl;
import org.soulspace.template.impl.TemplateEngineImpl;

public class Generator {

	private GeneratorContext gCtx = new GeneratorContext();
	private TemplateEngine engine;
	
	public String getTemplate() {
		return gCtx.getTemplate();
	}

	public void setTemplate(String name) {
		gCtx.setTemplate(name);
	}
	
	public void addConfiguredParam(Param param) {
		gCtx.addParam(param);
	}

	public void setEncoding(String encoding) {
		gCtx.setEncoding(encoding);
	}

	public void setImports(String imports) {
		gCtx.setImports(imports);
	}

	public void setTarget(String target) {
		gCtx.setTarget(target);
	}

	void generate(EngineContext eCtx) {
		engine = getEngine(eCtx);
		Map<String, String> params = new HashMap<String, String>();
		params.putAll(eCtx.getParamMap());
		params.putAll(gCtx.getParamMap());

		BeanDataSourceImpl ds = new BeanDataSourceImpl();
		ds.add("Ctx", gCtx);
		ds.add("Param", params);

		try {
			String output = engine.generate(ds);
			writeFile(gCtx.getTarget(), output);
		} catch (Exception e) {
			throw new RuntimeException("Exception while processing template "
					+ gCtx.getTemplate() + "!", e);
		}
	}
	
	TemplateEngine getEngine(EngineContext eCtx) {
		if (engine != null) {
			return engine;
		}

		File[] templateDirs;
		if (eCtx.getTemplateDirs() == null) {
			throw new RuntimeException("Template directory not set");
		} else {
			templateDirs = getTemplateDirs(eCtx.getTemplateDirs());
		}
		
		try {
			engine = new TemplateEngineImpl();
			String[] importTemplateNames = null;
			if (isSet(gCtx.getImports())) {
				importTemplateNames = gCtx.getImports().split(",");
				File[] templateFiles = new File[importTemplateNames.length + 1];
				for (int i = 0; i < importTemplateNames.length; i++) {
					templateFiles[i] = locateFile(templateDirs,
							importTemplateNames[i].trim(), ".tinc");
				}
				templateFiles[importTemplateNames.length] = locateFile(
						templateDirs, gCtx.getTemplate(), ".tmpl");
				engine.loadTemplates(templateFiles);
			} else {
				engine.loadTemplate(locateFile(templateDirs, gCtx
						.getTemplate(), ".tmpl"));
			}
		} catch (Exception e) {
			engine = null;
			throw new RuntimeException(
					"Error creating a template engine for template "
							+ gCtx.getTemplate(), e);
		}

		return engine;
	}

	File[] getTemplateDirs(List<String> templateDirNames) {
		File[] templateDirs = new File[templateDirNames.size()];
		for (int i = 0; i < templateDirNames.size(); i++) {
			File file = new File(templateDirNames.get(i).trim());
			if (!file.exists() || !file.isDirectory()) {
				throw new RuntimeException("Error validating directory "
						+ templateDirNames.get(i));
			}
			templateDirs[i] = file;
		}
		return templateDirs;
	}

	File locateFile(File[] templateDirs, String basename, String extension) {
		for (File templateDir : templateDirs) {
			File file = new File(templateDir.getAbsolutePath() + "/" + basename
					+ extension);
			if (file.exists()) {
				return file;
			}
		}
		throw new RuntimeException("Error locating file " + basename
				+ extension);
	}

	boolean writeFile(String filename, String content) {
		File file = new File(filename);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					file), gCtx.getEncoding()));
			pw.print(content);
			pw.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return true;
	}

	boolean isSet(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		return true;
	}

}
