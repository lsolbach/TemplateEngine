/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;



/**
 * A symbol representing string data.
 * 
 * @author soulman
 * 
 */
public class StringValueImpl implements Value, StringValue {

	private String data;

  /**
   * Constructor
   */
	public StringValueImpl() {
		this.data = "";
	}

  /**
   * Constructor
   * @param data
   */
	public StringValueImpl(String data) {
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
  public StringValueImpl add(StringValue symbol) {
  	return new StringValueImpl(getData() + symbol.getData());
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.StringType#add(org.soulspace.template.symbols.impl.NumericSymbol)
	 */
  public StringValue add(NumericValue symbol) {
  	return new StringValueImpl(getData() + symbol.evaluate());
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
		final StringValueImpl other = (StringValueImpl) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	public String asString() {
		return data;
	}

	public long asLong() {
		return Long.valueOf(data.length());
	}

}