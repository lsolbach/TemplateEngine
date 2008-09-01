/*
 * Created on Feb 14, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;



/**
 * A symbol representing string data.
 * 
 * @author soulman
 * 
 */
public class StringValue implements IValue, IStringValue {

	private String data;

  /**
   * Constructor
   */
	public StringValue() {
		this.data = "";
	}

  /**
   * Constructor
   * @param data
   */
	public StringValue(String data) {
		this.data = data;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#getData()
	 */
	public String getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#setData(java.lang.String)
	 */
	public void setData(String data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.templates.AbstractSymbol#getType()
	 */
	public ValueType getType() {
		return ValueType.STRING;
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
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#add(org.soulspace.template.symbols.impl.StringType)
	 */
  public StringValue add(IStringValue symbol) {
  	return new StringValue(getData() + symbol.getData());
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#add(org.soulspace.template.symbols.impl.NumericSymbol)
	 */
  public IStringValue add(INumericValue symbol) {
  	return new StringValue(getData() + symbol.evaluate());
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#isSet()
	 */
  public boolean isSet() {
  	return data != null;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#isEmpty()
	 */
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
		final StringValue other = (StringValue) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
}