/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.value.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;


/**
 * List symbol, stores entries in a List
 * 
 * @author soulman
 */
public class ListValue implements IValue, IListValue {

	private List<IValue> data;
	private IValue       entry;
	private int          index;

  /**
   * Constructor
   */
	public ListValue() {
		this.data = new ArrayList<IValue>();
	}

  /**
   * Constructor
   * @param data
   */
	ListValue(List<IValue> data) {
		// Copy elements
		this.data = new ArrayList<IValue>();
		Iterator<IValue> iData = data.iterator();
		while(iData.hasNext()) {
			this.data.add(iData.next());
		}
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public ValueType getType() {
    return ValueType.LIST;
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#addNewListSymbol(java.util.List)
	 */
	public void addNewListSymbol(List<IValue> data) {
		ListValue listSymbol = new ListValue(data);
		addSymbol(listSymbol);
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#addNewMapSymbol(org.soulspace.template.symbols.ISymbolTable)
	 */
	public void addNewMapSymbol(ISymbolTable data) {
		MapValue mapSymbol = new MapValue(data);
		addSymbol(mapSymbol);
	}
	
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#addNewNumericSymbol(java.lang.String)
	 */
	public void addNewNumericSymbol(String data) {
		NumericValue numericSymbol = new NumericValue(data);
		addSymbol(numericSymbol);		
	}
	
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#addNewStringSymbol(java.lang.String)
	 */
	public void addNewStringSymbol(String data) {
		StringValue stringSymbol = new StringValue(data);
		addSymbol(stringSymbol);
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#addSymbol(org.soulspace.template.symbols.ISymbol)
	 */
  public void addSymbol(IValue symbol) {
		this.data.add(symbol);
	}

	
	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#getData()
	 */
  public List<IValue> getData() {
		return data;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#getEntry()
	 */
	public IValue getEntry() {
		return entry;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#getIndex()
	 */
	public int getIndex() {
		return index;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#size()
	 */
	public int size() {
		return data.size();
	}
	
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#iterateData()
	 */
  public Iterator<IValue> iterateData() {
    return this.data.iterator();
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#hasNext()
	 */
	public boolean hasNext() {
		if(entry != null && index < size() - 1) {
			return true;
		}
		return false;
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#next()
	 */
	public Object next() {
		return data.get(index);
	}


	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#setEntry(org.soulspace.template.symbols.ISymbol)
	 */
	public void setEntry(IValue entry) {
		this.entry = entry;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#setIndex(int)
	 */
	public void setIndex(int index) {
		this.index = index;
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IListType#setData(java.util.List)
	 */
  public void setData(List<IValue> list) {
  	// TODO copy elements as in constructor?
  	data = list;
  }

  public String evaluate() {
    String result = "";
    if (getEntry() != null) {
      // Get current list entry in foreach iteration
      IValue aSymbol = getEntry();
      if (aSymbol.getType().equals(ValueType.STRING)) {
        result = ((IStringValue) aSymbol).getData();
      } else if (aSymbol.getType().equals(ValueType.NUMERIC)) {
        result = ((INumericValue) aSymbol).getData();
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
  
}
