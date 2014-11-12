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

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

/**
 * A symbol for storing numeric data.
 * 
 * @author soulman
 * 
 */
public class NumericValueImpl implements Value, NumericValue {

	private String data;

	/**
	 * Constructor
	 */
	public NumericValueImpl() {
		this.data = "";
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public NumericValueImpl(String data) {
		this.data = data;
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public NumericValueImpl(double data) {
		this.data = String.valueOf(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.templates.AbstractSymbol#getType()
	 */
	public ValueType getType() {
		return ValueType.NUMERIC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.INumericType#getData()
	 */
	public String getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#setData(java.lang.String
	 * )
	 */
	public void setData(String data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.INumericType#asDouble()
	 */
	public double asDouble() {
		try {
			return Double.parseDouble(data);
		} catch (NumberFormatException e) {
			throw new GenerateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.INumericType#asLong()
	 */
	public long asLong() {
		try {
			return Long.parseLong(data);
		} catch (NumberFormatException e) {
			throw new GenerateException(e);
		}
	}

	public String asString() {
		return String.valueOf(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#add(org.soulspace.template
	 * .symbols.impl.INumericType)
	 */
	public NumericValueImpl add(NumericValue arg) {
		return new NumericValueImpl(asDouble() + arg.asDouble());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#substract(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl substract(NumericValue arg) {
		return new NumericValueImpl(asDouble() - arg.asDouble());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#mult(org.soulspace.template
	 * .symbols.impl.INumericType)
	 */
	public NumericValueImpl mult(NumericValue arg) {
		return new NumericValueImpl(asDouble() * arg.asDouble());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#divide(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl divide(NumericValue divisor) {
		if (divisor.asDouble() == 0.0) {
			throw new GenerateException("Division by zero!");
		}
		return new NumericValueImpl(asDouble() / divisor.asDouble());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#divideInteger(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl divideInteger(NumericValue divisor) {
		long divLong = divisor.asLong();
		if (divLong == 0) {
			throw new GenerateException("Division by zero!");
		}
		return new NumericValueImpl((long) (asDouble() / divLong));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#modulo(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl modulo(NumericValue arg) {
		return new NumericValueImpl((asDouble() % arg.asDouble()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#less(org.soulspace.template
	 * .symbols.impl.INumericType)
	 */
	public NumericValueImpl less(NumericValue arg) {
		return asDouble() < arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#lessEqual(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl lessEqual(NumericValue arg) {
		return asDouble() <= arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#greater(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl greater(NumericValue arg) {
		return asDouble() > arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#greaterEqual(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl greaterEqual(NumericValue arg) {
		return asDouble() >= arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#equal(org.soulspace.
	 * template.symbols.impl.INumericType)
	 */
	public NumericValueImpl equal(NumericValue arg) {
		return asDouble() == arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#notEqual(org.soulspace
	 * .template.symbols.impl.INumericType)
	 */
	public NumericValueImpl notEqual(NumericValue arg) {
		return asDouble() != arg.asDouble() ? new NumericValueImpl(1) : new NumericValueImpl(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.symbols.impl.INumericType#isZero()
	 */
	public boolean isZero() {
		if (asDouble() == 0.0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#isNumeric(java.lang.
	 * String)
	 */
	public boolean isNumeric(String result) {
		try {
			Double.parseDouble(result);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.symbols.impl.INumericType#roundResult(java.lang
	 * .String)
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
