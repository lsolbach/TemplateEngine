package org.soulspace.template.value;

public interface StringValue extends Value {

	/**
	 * @return String
	 */
	String getData();

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	void setData(String data);

	StringValue add(StringValue symbol);

	StringValue add(NumericValue symbol);

	boolean isSet();

	boolean isEmpty();

}