/*
 * Created on 23.05.2003
 *
 */
package org.soulspace.util;

/**
 * @author soulman
 *
 */
public class StringParameterParser {

	/**
	 * 
	 */
	public StringParameterParser() {
		super();
	}

	/**
	 * @param parameter
	 * @param defaultValue
	 * @return
	 */
	public int getInt(String parameter, int defaultValue) {
		try {
			return getInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * @param parameter
	 * @return
	 * @throws NumberFormatException
	 */
	public int getInt(String parameter) 
							throws NumberFormatException {
		return Integer.parseInt(parameter);
	}

	/**
	 * @param parameter
	 * @param defaultValue
	 * @return
	 */
	public long getLong(String parameter, long defaultValue) {
		try {
			return getLong(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * @param parameter
	 * @return
	 * @throws NumberFormatException
	 */
	public long getLong(String parameter) 
							throws NumberFormatException {
		return Long.parseLong(parameter);
	}

}