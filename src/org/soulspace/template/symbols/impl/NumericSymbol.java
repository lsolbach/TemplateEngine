/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.symbols.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.symbols.ISymbol;



/**
 * A symbol for storing numeric data.
 * 
 * @author soulman
 * 
 */
public class NumericSymbol implements ISymbol {

	private String data;

  /**
   * Constructor
   */
	public NumericSymbol() {
		this.data = "";
	}

  /**
   * Constructor
   * @param data
   */
	public NumericSymbol(String data) {
		this.data = data;
	}

  /**
   * Constructor
   * @param data
   */
	public NumericSymbol(double data) {
		this.data = String.valueOf(data);
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public SymbolType getType() {
    return SymbolType.NUMERIC;
  }

	/**
   * Return data
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

  public double asDouble() {
  	try {
  		return Double.parseDouble(data);
  	} catch(NumberFormatException e) {
  		throw new GenerateException(e);
  	}
  }
  
  public long asLong() {
  	try {
  		return Long.parseLong(data);
  	} catch(NumberFormatException e) {
  		throw new GenerateException(e);
  	}
  }
  
  public NumericSymbol add(NumericSymbol arg) {
  	return new NumericSymbol(asDouble() + arg.asDouble());
  }

  public NumericSymbol substract(NumericSymbol arg) {
  	return new NumericSymbol(asDouble() - arg.asDouble());
  }

  public NumericSymbol mult(NumericSymbol arg) {
  	return new NumericSymbol(asDouble() * arg.asDouble());
  }

  public NumericSymbol divide(NumericSymbol divisor) {
  	if(divisor.asDouble() == 0.0) {
  		throw new GenerateException("Division by zero!");
  	}
  	return new NumericSymbol(asDouble() / divisor.asDouble());
  }

  public NumericSymbol divideInteger(NumericSymbol divisor) {
  	long divLong = divisor.asLong();
  	if(divLong == 0) {
  		throw new GenerateException("Division by zero!");  		
  	}
  	return new NumericSymbol((long) (asDouble() / divLong));
  }

  public NumericSymbol modulo(NumericSymbol arg) {
  	return new NumericSymbol((asDouble() % arg.asDouble()));
  }

  public NumericSymbol less(NumericSymbol arg) {
  	return asDouble() < arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public NumericSymbol lessEqual(NumericSymbol arg) {
  	return asDouble() <= arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public NumericSymbol greater(NumericSymbol arg) {
  	return asDouble() > arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public NumericSymbol greaterEqual(NumericSymbol arg) {
  	return asDouble() >= arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public NumericSymbol equal(NumericSymbol arg) {
  	return asDouble() == arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public NumericSymbol notEqual(NumericSymbol arg) {
  	return asDouble() != arg.asDouble() ? new NumericSymbol(1) : new NumericSymbol(0);
  }
  
  public boolean isZero() {
  	if(asDouble() == 0.0) {
  		return true;
  	}
  	return false;
  }

  public boolean isNumeric(String result) {
    try {
      Double.parseDouble(result);
    } catch (NumberFormatException ex) {
      return false;
    }
    return true;
  }

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
