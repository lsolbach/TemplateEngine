/*
 * Created on Apr 28, 2004
 *
 */
package org.soulspace.template.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author soulman
 */
public class TestBean {

	public static final String STRING_VALUE = "StringValue";
	public static final String INTEGER_VALUE = "IntegerValue";
	public static final String LONG_VALUE = "LongValue";
	public static final String SHORT_VALUE = "ShortValue";
	public static final String BYTE_VALUE = "ByteValue";
	public static final String FLOAT_VALUE = "FloatValue";
	public static final String DOUBLE_VALUE = "DoubleValue";
	public static final String CHAR_VALUE = "CharValue";
	public static final String BOOLEAN_GET_VALUE = "BooleanGetValue";
	public static final String BOOLEAN_IS_VALUE = "BooleanIsValue";

	private String stringValue;
	private int integerValue;
	private long longValue;
	private short shortValue;
	private byte byteValue;
	private float floatValue;
	private double doubleValue;
	private char charValue;
	private boolean booleanGetValue;
	private boolean booleanIsValue;

	private List<Object> testList = new ArrayList<Object>();
	private TestBean[] testArray;
	private TestBean testObject;

	/**
	 * 
	 */
	public TestBean() {
		super();
	}

	public TestBean(String stringValue) {
		this.stringValue = stringValue;
	}

	public TestBean(int integerValue) {
		this.integerValue = integerValue;
	}

	public TestBean(long longValue) {
		this.longValue = longValue;
	}

	public TestBean(short shortValue) {
		this.shortValue = shortValue;
	}

	public TestBean(byte byteValue) {
		this.byteValue = byteValue;
	}

	public TestBean(float floatValue) {
		this.floatValue = floatValue;
	}

	public TestBean(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public TestBean(char charValue) {
		this.charValue = charValue;
	}

	/**
	 * @param value
	 */
	public TestBean(boolean value) {
		this.booleanGetValue = value;
		this.booleanIsValue = value;
	}

	/**
	 * @return
	 */
	public byte getByteValue() {
		return byteValue;
	}

	/**
	 * @return
	 */
	public char getCharValue() {
		return charValue;
	}

	/**
	 * @return
	 */
	public double getDoubleValue() {
		return doubleValue;
	}

	/**
	 * @return
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * @return
	 */
	public int getIntegerValue() {
		return integerValue;
	}

	/**
	 * @return
	 */
	public long getLongValue() {
		return longValue;
	}

	/**
	 * @return
	 */
	public short getShortValue() {
		return shortValue;
	}

	/**
	 * @return
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * @return
	 */
	public List<Object> getTestList() {
		return testList;
	}

	/**
	 * @return
	 */
	public TestBean getTestObject() {
		return testObject;
	}

	/**
	 * @param b
	 */
	public void setByteValue(byte b) {
		byteValue = b;
	}

	/**
	 * @param c
	 */
	public void setCharValue(char c) {
		charValue = c;
	}

	/**
	 * @param d
	 */
	public void setDoubleValue(double d) {
		doubleValue = d;
	}

	/**
	 * @param f
	 */
	public void setFloatValue(float f) {
		floatValue = f;
	}

	/**
	 * @param i
	 */
	public void setIntegerValue(int i) {
		integerValue = i;
	}

	/**
	 * @param l
	 */
	public void setLongValue(long l) {
		longValue = l;
	}

	/**
	 * @param s
	 */
	public void setShortValue(short s) {
		shortValue = s;
	}

	/**
	 * @param string
	 */
	public void setStringValue(String string) {
		stringValue = string;
	}

	/**
	 * @param list
	 */
	public void setTestList(List<Object> list) {
		testList = list;
	}

	/**
	 * @param bean
	 */
	public void setTestObject(TestBean bean) {
		testObject = bean;
	}

	/**
	 * @return
	 */
	public TestBean[] getTestArray() {
		return testArray;
	}

	/**
	 * @param beans
	 */
	public void setTestArray(TestBean[] beans) {
		testArray = beans;
	}

	/**
	 * @return
	 */
	public boolean getBooleanGetValue() {
		return booleanGetValue;
	}

	/**
	 * @return
	 */
	public boolean isBooleanIsValue() {
		return booleanIsValue;
	}

	/**
	 * @param b
	 */
	public void setBooleanGetValue(boolean b) {
		booleanGetValue = b;
	}

	/**
	 * @param b
	 */
	public void setBooleanIsValue(boolean b) {
		booleanIsValue = b;
	}

}
