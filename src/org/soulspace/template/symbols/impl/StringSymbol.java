/*
 * Created on Feb 14, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.soulspace.template.symbols.impl;

import org.soulspace.template.symbols.ISymbol;



/**
 * A symbol representing string data.
 * 
 * @author soulman
 * 
 */
public class StringSymbol implements ISymbol {

	private String data;

  /**
   * Constructor
   */
	public StringSymbol() {
		this.data = "";
	}

  /**
   * Constructor
   * @param data
   */
	public StringSymbol(String data) {
		this.data = data;
	}
	
	/**
	 * @return String
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.templates.AbstractSymbol#getType()
	 */
	public SymbolType getType() {
		return SymbolType.STRING;
	}

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "StringSymbol value: " + data;
  }

  public String evaluate() {
    String result = getData();
    return result;
  }
  
  public boolean isSet() {
  	return data != null;
  }
  
  public boolean isEmpty() {
  	if(isSet() && data.equals("")) {
  		return true;
  	}
  	return false;	
  }

	public boolean isTrue() {
		if(data == null) {
			return false;
		}
		if(data.equals("")) {
			return false;
		}
		if(data.equals("false") || data.equals("0")) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StringSymbol other = (StringSymbol) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
}