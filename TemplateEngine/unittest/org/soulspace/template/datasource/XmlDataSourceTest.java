/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.datasource;

import java.io.File;

import junit.framework.TestCase;

import org.soulspace.template.datasource.impl.XmlDataSourceImpl;
import org.soulspace.template.value.SymbolTable;

public class XmlDataSourceTest extends TestCase {

	XmlDataSourceImpl xmlDS;
	
	protected void setUp() throws Exception {
		super.setUp();
		xmlDS = new XmlDataSourceImpl(new File("data/unittest/test.xml"));
	}

	protected void tearDown() throws Exception {
		xmlDS = null;
		super.tearDown();
	}

	public void testXmlDS() {
		
		SymbolTable st = xmlDS.getSymbolTable();
		assertEquals(1, st.getSymbolCount());
	}

}
