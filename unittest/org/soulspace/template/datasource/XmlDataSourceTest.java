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
