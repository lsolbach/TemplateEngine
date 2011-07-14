/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.value.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

/**
 * List symbol, stores entries in a List
 * 
 * @author soulman
 */
public class ListValueImpl implements Value, ListValue {

	private List<Value> data;
	private Value entry;
	private int index;

	/**
	 * Constructor
	 */
	public ListValueImpl() {
		this.data = new ArrayList<Value>();
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public ListValueImpl(List<Value> data) {
		// Copy elements
		this.data = new ArrayList<Value>();
		Iterator<Value> iData = data.iterator();
		while (iData.hasNext()) {
			this.data.add(iData.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.templates.AbstractSymbol#getType()
	 */
	public ValueType getType() {
		return ValueType.LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#addNewListSymbol(java.util
	 * .List)
	 */
	public void addNewListValue(List<Value> data) {
		ListValueImpl listSymbol = new ListValueImpl(data);
		addValue(listSymbol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#addNewMapSymbol(org.soulspace
	 * .template.symbols.ISymbolTable)
	 */
	public void addNewMapValue(SymbolTable data) {
		MapValueImpl mapSymbol = new MapValueImpl(data);
		addValue(mapSymbol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#addNewNumericSymbol(java
	 * .lang.String)
	 */
	public void addNewNumericValue(String data) {
		NumericValueImpl numericSymbol = new NumericValueImpl(data);
		addValue(numericSymbol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#addNewStringSymbol(java
	 * .lang.String)
	 */
	public void addNewStringValue(String data) {
		StringValueImpl stringSymbol = new StringValueImpl(data);
		addValue(stringSymbol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#addSymbol(org.soulspace
	 * .template.symbols.ISymbol)
	 */
	public void addValue(Value symbol) {
		this.data.add(symbol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#getData()
	 */
	public List<Value> getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#getEntry()
	 */
	public Value getEntry() {
		return entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#getIndex()
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#size()
	 */
	public int size() {
		return data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#iterateData()
	 */
	public Iterator<Value> iterateData() {
		return this.data.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#hasNext()
	 */
	public boolean hasNext() {
		if (entry != null && index < size() - 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#next()
	 */
	public Object next() {
		return data.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#setEntry(org.soulspace.
	 * template.symbols.ISymbol)
	 */
	public void setEntry(Value entry) {
		this.entry = entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.IListType#setIndex(int)
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.IListType#setData(java.util.List)
	 */
	public void setData(List<Value> list) {
		// TODO copy elements as in constructor?
		data = list;
	}

	public String evaluate() {
		String result = "";
		if (getEntry() != null) {
			// Get current list entry in foreach iteration
			Value aSymbol = getEntry();
			if (aSymbol.getType().equals(ValueType.STRING)) {
				result = ((StringValue) aSymbol).getData();
			} else if (aSymbol.getType().equals(ValueType.NUMERIC)) {
				result = ((NumericValue) aSymbol).getData();
			}
		} else {
			// return number of elements in scalar context
			// TODO is this necessary, now we have ":_SIZE"?
			result = String.valueOf(getData().size());
		}
		return result;
	}

	public boolean isTrue() {
		return data.size() != 0;
	}

	public String asString() {
		return String.valueOf(data.size());
	}

	public long asLong() {
		return Long.valueOf(data.size());
	}

}
