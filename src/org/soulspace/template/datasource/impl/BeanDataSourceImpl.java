/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.datasource.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.SymbolTableImpl;

/**
 * Implementation of the DataSource interface for the data binding of
 * Java Beans.
 * Transforms a bean (hierachy) to a SymbolTable. Calls the getter
 * methods for all properties. The following mapping is used:<br>
 * Simple types (incl. String) to Scalar Symbol<br>
 * Object (except String) to MapSymbol<br>
 * Lists to ListSymbol<br>
 * Object Array to ListSymbol
 *  
 * @author Ludger Solbach
 */
public class BeanDataSourceImpl implements DataSource {

	SymbolTable symbolTable = new SymbolTableImpl();
	Map<Object, SymbolTable> cache = new HashMap<Object, SymbolTable>();
	boolean parsed = false;

	public BeanDataSourceImpl() {
		this.symbolTable = new SymbolTableImpl();
	}

	/**
	 * Converts the given Java Bean to a SymbolTable.
	 * @param rootBean
	 */
	public BeanDataSourceImpl(Object rootBean) {
		this.symbolTable = parse(rootBean);
	}

	/**
	 * Converts the given Java Bean to a SymbolTable.
	 * Uses the cached values of the provided data source.
	 * @param rootBean
	 * @param ds
	 */
	public BeanDataSourceImpl(Object rootBean, BeanDataSourceImpl ds) {
		setCache(ds.getCache());
		this.symbolTable = parse(rootBean);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.datasource.DataSource#getSymbolTable()
	 */
	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	/**
	 * Adds an object to this data source under the given key. 
	 * @param name
	 * @param bean
	 */
	public void add(String name, Object bean) {
		if (bean instanceof String) {
			symbolTable.addStringValue(name, (String) bean);
		} else if (bean instanceof Number) {
			symbolTable.addNumericValue(name, String.valueOf(bean));
		} else if (bean instanceof Map) {
			insert(symbolTable, name, bean);
		} else if (bean instanceof Collection) {
			insert(symbolTable, name, bean);
		} else {
			symbolTable.addMapValue(name, parse(bean));
		}
	}

	/**
	 * Parse an object into the symbol table of this bean data source.
	 * @param symbolTable
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected SymbolTable parse(Object object) {
		SymbolTable symbolTable = new SymbolTableImpl();
		if (object instanceof Map) {
			Map map = (Map) object;
			if(stringKeysOnly(map)) {
				// string keys, add map key/values directly into the symbol table
				for(Object key : map.keySet()) {
					String stringKey = key.toString();
					if(stringKey.isEmpty()) {
						stringKey = "EMPTY_STRING";
					}
					insert(symbolTable, key.toString(), map.get(key));
				}
			} else {
				// no string keys, insert the whole map under the key 'MAP'
				insert(symbolTable, "MAP", object);
			}
		} else if (object instanceof Collection) {
			insert(symbolTable, "LIST", object);
		} else {
			// put symbolTable into the cache first to prevent endless loops
			cache.put(object, symbolTable);

			Class<? extends Object> myClass = object.getClass();
			Method methods[] = myClass.getMethods();

			// iterate over the methods of the class of the given object
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				String methodName = method.getName();

				// we are only interested in public instance getters
				if (method.getParameterTypes().length > 0
						|| !Modifier.isPublic(method.getModifiers())
						|| Modifier.isStatic(method.getModifiers())) {
					continue;
				}

				String symbolName;
				if (methodName.startsWith("get")
						&& !methodName.equals("getClass")) {
					// standard getter
					symbolName = methodName.substring(3);
				} else if (methodName.startsWith("is")) {
					// boolean getter
					symbolName = methodName.substring(2);
				} else {
					// not a getter
					continue;
				}

				Object result = null;
				try {
					// call the getter
					result = method.invoke(object, (Object[]) null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if(result != null) {
					// add the symbol to the symbol table
					insert(symbolTable, symbolName, result);
				}
			}
		}
		return symbolTable;
	}

	/**
	 * Insert the result object to the symbol table under the given key.
	 * @param symbolTable The symbol table to add to.
	 * @param symbolName The key under which the object is added.
	 * @param result The object to add.
	 */
	@SuppressWarnings("rawtypes")
	protected void insert(SymbolTable symbolTable, String symbolName,
			Object result) {
		// add the symbol to the symbol table
		if(result == null) {
			return;
		} else if (result instanceof String) {
			// String
			symbolTable.addStringValue(symbolName, (String) result);
		} else if (result instanceof Boolean/* .TYPE */) {
			// boolean
			if (((Boolean) result).booleanValue()) {
				symbolTable.addStringValue(symbolName, "1");
			} else {
				symbolTable.addStringValue(symbolName, "0");
			}
		} else if (result instanceof Byte || result instanceof Short
				|| result instanceof Integer || result instanceof Long
				|| result instanceof Double || result instanceof Float
				|| result instanceof Character) {
			// Number
			symbolTable.addNumericValue(symbolName, String.valueOf(result));
		} else if (result instanceof Void/* .TYPE */) {
			// Void
		} else if (result instanceof Map) {
			// Map
			SymbolTable st = new SymbolTableImpl();
			Map map = (Map) result;
			for(Object key : map.keySet()) {
				if(key == null) {
					key = "NULL_KEY";
				} else if(key instanceof String && ((String) key).isEmpty()) {
					key = "EMPTY_STRING";
				}
				insert(st, key.toString(), map.get(key));
			}
			symbolTable.addMapValue(symbolName, st);
		} else if (result instanceof Collection) {
			// List
			List<Value> list = new ArrayList<Value>();
			symbolTable.addListValue(symbolName, list);
			ListValue lSymbol = (ListValue) symbolTable.getSymbol(symbolName);
			Iterator it = ((Collection) result).iterator();
			while (it.hasNext()) {
				insert(lSymbol, it.next());
			}
		} else if (result instanceof Object[]) {
			// Object Array
			// TODO add handling for arrays of base types?
			Object[] objects = (Object[]) result;
			List<Value> list = new ArrayList<Value>();
			symbolTable.addListValue(symbolName, list);
			ListValue lSymbol = (ListValue) symbolTable.getSymbol(symbolName);
			for (int i = 0; i < objects.length; i++) {
				insert(lSymbol, objects[i]);
			}
		} else if (result instanceof Object) {
			// Object
			SymbolTable sTable;
			if ((sTable = (SymbolTable) cache.get(result)) == null) {
				sTable = parse(result);
				// put it into cache first to prevent endless loops
				// cache.put(result, sTable);
			}
			symbolTable.addMapValue(symbolName, sTable);
		}
	}

	/**
	 * Inserts the object into the list value.
	 * @param listValue
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	protected void insert(ListValue listValue, Object result) {
		if(result == null) {
			return;
		} else if (result instanceof String) {
			// String
			listValue.addNewStringValue((String) result);
		} else if (result instanceof Boolean/* .TYPE */) {
			// boolean
			if (((Boolean) result).booleanValue()) {
				listValue.addNewStringValue("1");
			} else {
				listValue.addNewStringValue("0");
			}
		} else if (result instanceof Byte || result instanceof Short
				|| result instanceof Integer || result instanceof Long
				|| result instanceof Double || result instanceof Float
				|| result instanceof Character) {
			// Number
			listValue.addNewNumericValue(String.valueOf(result));
		} else if (result instanceof Void/* .TYPE */) {
			// Void
		} else if (Collection.class.isAssignableFrom(result.getClass())) {
			// List
			List<Value> list = new ArrayList<Value>();
			listValue.addNewListValue(list);
			// ListSymbol lSymbol = (ListSymbol)
			// listSymbol.getSymbol(symbolName);
			Iterator it = ((Collection) result).iterator();
			while (it.hasNext()) {
				// TODO change this! Don't use a SymbolTableImpl, use a ListSymbol.
				// Let ListSymbol implement SymbolTable (to an extend)
				// Maybe use an Interface hierarchy ISymbolContainer
				// -> SymbolTable, ISymbolList

				// parse(lSymbol, it.next());
			}
		} else if (result instanceof Object[]) {
			// Object Array
			// TODO add handling for arrays of base types
			Object[] objects = (Object[]) result;
			List<Value> list = new ArrayList<Value>();
			listValue.addNewListValue(list);
			// ListSymbol lSymbol = (ListSymbol)
			// symbolTable.getSymbol(symbolName);
			for (int i = 0; i < objects.length; i++) {
				// TODO change this! Don't use a SymbolTableImpl, use a ListSymbol.
				// Let ListSymbol implement SymbolTable (to an extend)
				// Maybe use an Interface hierarchy ISymbolContainer
				// -> SymbolTable, ISymbolList
				// insert(lSymbol, objects[i]);
			}

		} else if (result instanceof Object) {
			// Object
			SymbolTable sTable;
			if ((sTable = (SymbolTable) cache.get(result)) == null) {
				sTable = parse(result);
			}
			listValue.addNewMapValue(sTable);
		}
	}

	protected Map<Object, SymbolTable> getCache() {
		return cache;
	}
	
	protected void setCache(Map<Object, SymbolTable> cache) {
		this.cache = cache;
	}

	@SuppressWarnings("rawtypes")
	protected boolean stringKeysOnly(Map map) {
		for(Object key : map.keySet()) {
			if(!(key instanceof String)) {
				return false;
			}
		}
		return true;
	}
}
