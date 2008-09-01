package org.soulspace.template.value;

public interface INumericValue extends IValue {

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

	INumericValue add(INumericValue arg);

	INumericValue substract(INumericValue arg);

	INumericValue mult(INumericValue arg);

	INumericValue divide(INumericValue divisor);

	INumericValue divideInteger(INumericValue divisor);

	INumericValue modulo(INumericValue arg);

	INumericValue less(INumericValue arg);

	INumericValue lessEqual(INumericValue arg);

	INumericValue greater(INumericValue arg);

	INumericValue greaterEqual(INumericValue arg);

	INumericValue equal(INumericValue arg);

	INumericValue notEqual(INumericValue arg);

	boolean isZero();

	boolean isNumeric(String result);

	String roundResult(String result);

}