/*
 * Created on Mar 19, 2003
 *
 */
package org.soulspace.template.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// FIXME throw away, when MDAGenerator uses the class JavaUtils

/**
 * @author soulman
 * 
 *         String helper class
 */
public class StringHelper {

	/**
	 * Splits an input string into a token list with the use of a delimiter
	 * string. Adds empty strings when two delimiters follow immediately after
	 * each other.
	 * 
	 * @param input
	 * @param delim
	 * @return List of strings
	 */
	public static List<String> split(String input, String delim) {
		ArrayList<String> array = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(input, delim, true);
		String current;
		String last = "";
		while (st.hasMoreElements()) {
			current = st.nextToken();
			if (!current.equals(delim)) {
				array.add(current);
			} else if (last.equals(delim)) {
				array.add("");
			}
			last = current;
		}
		if (last.equals(delim)) {
			array.add("");
		}
		return array;
	}

	/**
	 * Returns a substring until the first carriage return and/or line feed.
	 * Used to remove carriage return/line feed from a string.
	 * 
	 * @param input
	 *            Line string
	 * @return String
	 */
	public static String chop(String input) {
		String array[] = input.split("\r\n|\n|\r");
		return array[0];
	}

	/**
	 * Checks if the String is not null or empty
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isSet(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
