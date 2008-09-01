/*
 * Created on Apr 28, 2004
 *
 */
package org.soulspace.template.datasource;

import junit.framework.TestCase;

import org.soulspace.template.datasource.impl.BeanDataSource;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;

/**
 * 
 * @author soulman
 */
public class BeanDataSourceTest extends TestCase {

	/**
	 * Constructor for BeanDataSourceTest.
	 * @param arg0
	 */
	public BeanDataSourceTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSymbolTable() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    

    ts = new TestBean("Test");
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertEquals(TestBean.STRING_VALUE,
      ((IStringValue) st.getSymbol(TestBean.STRING_VALUE)).getData(), "Test");

	}

  public void testStringAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    String value = "Test";

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.STRING_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: StringSymbol",
      symbol instanceof IStringValue);
    assertEquals(TestBean.STRING_VALUE,
      value, ((IStringValue) symbol).getData());

  }

  public void testIntegerAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    int value = 12;
    
    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.INTEGER_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.INTEGER_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testLongAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    long value = 17;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.LONG_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.LONG_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testShortAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    short value = 24;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.SHORT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.SHORT_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testByteAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    byte value = 127;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BYTE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.BYTE_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testFloatAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    float value = 13.52f;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.FLOAT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.FLOAT_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testDoubleAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.DOUBLE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof INumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testBooleanGetAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    boolean value = true;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_GET_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof IStringValue);
    assertEquals(TestBean.BOOLEAN_GET_VALUE,
      value?"1":"0", ((IStringValue) symbol).getData());
  }

  public void testBooleanIsAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    boolean value = false;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_IS_VALUE);
    assertTrue("Correct symbol type 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof IStringValue);
    assertEquals(TestBean.BOOLEAN_IS_VALUE,
      value?"1":"0", ((IStringValue) symbol).getData());

  }

  public void testListAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof INumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testArrayAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof INumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }

  public void testObjectAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    IValue symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof INumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((INumericValue) symbol).getData());

  }


	/*
	 * Test for boolean parse(ISymbolTable, Object)
	 */
	public void testParseISymbolTableObject() {
    // TODO implement test
	}

	/*
	 * Test for boolean parse(ListSymbol, Object)
	 */
	public void testParseListSymbolObject() {
    // TODO implement test
	}

	/*
	 * Test for void insert(ISymbolTable, String, Object)
	 */
	public void testInsertISymbolTableStringObject() {
    // TODO implement test
	}

	/*
	 * Test for void insert(ListSymbol, Object)
	 */
	public void testInsertListSymbolObject() {
    // TODO implement test
	}

}
