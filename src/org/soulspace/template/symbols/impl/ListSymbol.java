/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.symbols.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;


/**
 * List symbol, stores entries in a List
 * 
 * @author soulman
 */
public class ListSymbol implements ISymbol {

	private List<ISymbol> data;
	private ISymbol        entry;
	private int            index;

  /**
   * Constructor
   */
	public ListSymbol() {
		this.data = new ArrayList<ISymbol>();
	}

  /**
   * Constructor
   * @param data
   */
	ListSymbol(List<ISymbol> data) {
		// Copy elements
		this.data = new ArrayList<ISymbol>();
		Iterator<ISymbol> iData = data.iterator();
		while(iData.hasNext()) {
			this.data.add(iData.next());
		}
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public SymbolType getType() {
    return SymbolType.LIST;
  }

  /**
   * Create and add a new ListSymbol
   * @param data
   */
	public void addNewListSymbol(List<ISymbol> data) {
		ListSymbol listSymbol = new ListSymbol(data);
		addSymbol(listSymbol);
	}

  /**
   * Create and add a new MapSymbol
   * @param data
   */
	public void addNewMapSymbol(ISymbolTable data) {
		MapSymbol mapSymbol = new MapSymbol(data);
		addSymbol(mapSymbol);
	}
	
  /**
   * Create and add a new NumericSymbol
   * @param data
   */
	public void addNewNumericSymbol(String data) {
		NumericSymbol numericSymbol = new NumericSymbol(data);
		addSymbol(numericSymbol);		
	}
	
  /**
   * Create and add a new StringSymbol
   * @param data
   */
	public void addNewStringSymbol(String data) {
		StringSymbol stringSymbol = new StringSymbol(data);
		addSymbol(stringSymbol);
	}

  /**
   * Add a symbol
   * @param symbol
   */
  public void addSymbol(ISymbol symbol) {
		this.data.add(symbol);
	}

	
	/**
   * Returns the list
	 * @return List
	 */
  public List<ISymbol> getData() {
		return data;
	}
	
	/**
   * Returns the current entry
	 * @return ISymbol
	 */
	public ISymbol getEntry() {
		return entry;
	}

	/**
   * Returns the current index
	 * @return long
	 */
	public int getIndex() {
		return index;
	}

  /**
   * Returns an Iterator for this list symbol
   * @return
   */
  public Iterator<ISymbol> iterateData() {
    return this.data.iterator();
  }

  /**
   * Checks for other entries.
   * @return
   */
	public boolean hasNext() {
		return (index < data.size())?true:false;
	}

  /**
   * Returns the next entry
   * @return
   */
	public Object next() {
		return data.get(index);
	}


	/**
	 * Sets the entry.
	 * @param entry The entry to set
	 */
	public void setEntry(ISymbol entry) {
		this.entry = entry;
	}

	/**
	 * Sets the index.
	 * @param index The index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

  public void setData(List<ISymbol> list) {
  	// TODO copy elements as in constructor?
  	data = list;
  }

  public String evaluate() {
    String result = "";
    if (getEntry() != null) {
      // Get current list entry in foreach iteration
      ISymbol aSymbol = getEntry();
      if (aSymbol.getType().equals(SymbolType.STRING)) {
        result = ((StringSymbol) aSymbol).getData();
      } else if (aSymbol.getType().equals(SymbolType.NUMERIC)) {
        result = ((NumericSymbol) aSymbol).getData();
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
