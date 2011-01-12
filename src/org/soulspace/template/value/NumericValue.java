package org.soulspace.template.value;

public interface NumericValue extends Value {

	/**
	 * Return data
	 * @return String
	 */
	String getData();

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	void setData(String data);

	double asDouble();

	long asLong();

	NumericValue add(NumericValue arg);

	NumericValue substract(NumericValue arg);

	NumericValue mult(NumericValue arg);

	NumericValue divide(NumericValue divisor);

	NumericValue divideInteger(NumericValue divisor);

	NumericValue modulo(NumericValue arg);

	NumericValue less(NumericValue arg);

	NumericValue lessEqual(NumericValue arg);

	NumericValue greater(NumericValue arg);

	NumericValue greaterEqual(NumericValue arg);

	NumericValue equal(NumericValue arg);

	NumericValue notEqual(NumericValue arg);

	boolean isZero();

	boolean isNumeric(String result);

	String roundResult(String result);

}