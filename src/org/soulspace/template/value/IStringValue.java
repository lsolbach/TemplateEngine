package org.soulspace.template.value;

public interface IStringValue extends IValue {

	/**
	 * @return String
	 */
	String getData();

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	void setData(String data);

	IStringValue add(IStringValue symbol);

	IStringValue add(INumericValue symbol);

	boolean isSet();

	boolean isEmpty();

}