/*
 * Created on Feb 21, 2003
 *
 */
package org.soulspace.template.datasource;

import org.soulspace.template.value.SymbolTable;

/**
 * The DataSource interface defines a minimal interface for binding of
 * external data for the TemplateEngine. Implementations of this
 * interface must provide a SymbolTable for the data they contain.
 * 
 * @author Ludger Solbach
 */
public interface DataSource {
	
	/**
	 * Returns a symbol table for this data source.
	 * @return symbol table
	 */
	SymbolTable getSymbolTable();
}
