/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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