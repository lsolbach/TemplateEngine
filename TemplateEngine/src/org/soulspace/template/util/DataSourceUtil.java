/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.util;

import java.util.Set;

import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;

public class DataSourceUtil {

	/**
	 * Dump the content of a data source to System.out
	 * @param ds
	 */
	public static void dump(DataSource ds) {
		dumpSymbolTable(ds.getSymbolTable(), "");
	}

	public static void dumpSymbolTable(SymbolTable table, String indent) {
		Set<String> keySet = table.getKeySet();
		for(String key : keySet) {
			System.out.println(indent + "KEY " + key);
			dumpValue(table.getSymbol(key), indent + " ");
		}
	}
	
	public static void dumpValue(Value v, String indent) {
		if(v instanceof StringValue) {
			System.out.println(indent + ((StringValue) v).getData());
		} else if(v instanceof NumericValue) {
			System.out.println(indent + ((NumericValue) v).getData());
		} else if(v instanceof ListValue) {
			System.out.println(indent + "LIST [");
			for(Value entry :((ListValue) v).getData()) {
				dumpValue(entry, indent + " ");
			}
			System.out.println(indent + "]");
		} else if(v instanceof MapValue) {
			System.out.println(indent + "MAP [");
			dumpSymbolTable(((MapValue) v).getData(), indent + " ");
			System.out.println(indent + "]");
		}
	}
}
