/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.value.impl.SymbolTableImpl;

public class TemplateFileTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// public void testLoadTemplateString() {
	// TemplateEngine te = new TemplateEngineImpl();
	// String result = "";
	// te.loadTemplate("data/unittest/b.tmpl");
	// result = te.generate(new SymbolTableImpl());
	// assertEquals("Testtext", result);
	// }

	public void testLoadTemplateFile() {
		TemplateEngine te = new TemplateEngineImpl();
		String result = "";
		try {
			te.loadTemplate(new File("data/unittest/b.tmpl"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		result = te.generate(new SymbolTableImpl());
		assertEquals("Testtext", result);
	}

	// public void testLoadTemplatesStringArray() {
	// TemplateEngine te = new TemplateEngineImpl();
	// String result = "";
	// String[] filenames = new String[] {"data/unittest/a.tmpl",
	// "data/unittest/b.tmpl"};
	// te.loadTemplates(filenames);
	// result = te.generate(new SymbolTableImpl());
	// assertEquals("Testtext", result);
	// }

	public void testLoadTemplatesFileArray() {
		TemplateEngine te = new TemplateEngineImpl();
		String result = "";
		try {
			File[] files = new File[] { new File("data/unittest/a.tmpl"), new File("data/unittest/b.tmpl") };
			te.loadTemplates(files);
			result = te.generate(new SymbolTableImpl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Testtext", result);
	}

}
