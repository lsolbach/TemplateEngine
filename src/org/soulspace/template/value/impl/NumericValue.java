/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;



/**
 * A symbol for storing numeric data.
 * 
 * @author soulman
 * 
 */
public class NumericValue implements IValue, INumericValue {

  private String data;

  /**
   * Constructor
   */
	public NumericValue() {
		this.data = "";
	}

  /**
   * Constructor
   * @param data
   */
	public NumericValue(String data) {
		this.data = data;
	}

  /**
   * Constructor
   * @param data
   */
	public NumericValue(double data) {
		this.data = String.valueOf(data);
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public ValueType getType() {
    return ValueType.NUMERIC;
  }

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#getData()
	 */
	public String getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#setData(java.lang.String)
	 */
	public void setData(String data) {
		this.data = data;
	}

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "NumericSymbol value: " + data;
  }
  
  /**
   * 
   */
  public String evaluate() {
    String result = roundResult(getData());
    return result;
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#asDouble()
	 */
  public double asDouble() {
  	try {
  		return Double.parseDouble(data);
  	} catch(NumberFormatException e) {
  		throw new GenerateException(e);
  	}
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#asLong()
	 */
  public long asLong() {
  	try {
  		return Long.parseLong(data);
  	} catch(NumberFormatException e) {
  		throw new GenerateException(e);
  	}
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#add(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue add(INumericValue arg) {
  	return new NumericValue(asDouble() + arg.asDouble());
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#substract(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue substract(INumericValue arg) {
  	return new NumericValue(asDouble() - arg.asDouble());
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#mult(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue mult(INumericValue arg) {
  	return new NumericValue(asDouble() * arg.asDouble());
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#divide(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue divide(INumericValue divisor) {
  	if(divisor.asDouble() == 0.0) {
  		throw new GenerateException("Division by zero!");
  	}
  	return new NumericValue(asDouble() / divisor.asDouble());
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#divideInteger(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue divideInteger(INumericValue divisor) {
  	long divLong = divisor.asLong();
  	if(divLong == 0) {
  		throw new GenerateException("Division by zero!");  		
  	}
  	return new NumericValue((long) (asDouble() / divLong));
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#modulo(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue modulo(INumericValue arg) {
  	return new NumericValue((asDouble() % arg.asDouble()));
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#less(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue less(INumericValue arg) {
  	return asDouble() < arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#lessEqual(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue lessEqual(INumericValue arg) {
  	return asDouble() <= arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#greater(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue greater(INumericValue arg) {
  	return asDouble() > arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#greaterEqual(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue greaterEqual(INumericValue arg) {
  	return asDouble() >= arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#equal(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue equal(INumericValue arg) {
  	return asDouble() == arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#notEqual(org.soulspace.template.symbols.impl.INumericType)
	 */
  public NumericValue notEqual(INumericValue arg) {
  	return asDouble() != arg.asDouble() ? new NumericValue(1) : new NumericValue(0);
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#isZero()
	 */
  public boolean isZero() {
  	if(asDouble() == 0.0) {
  		return true;
  	}
  	return false;
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#isNumeric(java.lang.String)
	 */
  public boolean isNumeric(String result) {
    try {
      Double.parseDouble(result);
    } catch (NumberFormatException ex) {
      return false;
    }
    return true;
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.INumericType#roundResult(java.lang.String)
	 */
  public String roundResult(String result) {
    if (!result.equals("") && isNumeric(result)) {
      double doubleResult = Double.parseDouble(result);
      long longResult = (new Double(doubleResult)).longValue();
      if ((doubleResult - longResult) == 0) {
        return String.valueOf(longResult);
      }
    }
    return result;
  }
  
  public boolean isTrue() {
  	return !isZero();
  }
}
