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
