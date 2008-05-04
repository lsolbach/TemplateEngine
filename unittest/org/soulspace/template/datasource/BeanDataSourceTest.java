/*
 * Created on Apr 28, 2004
 *
 */
package org.soulspace.template.datasource;

import junit.framework.TestCase;

import org.soulspace.template.datasource.impl.BeanDataSource;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

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
      ((StringSymbol) st.getSymbol(TestBean.STRING_VALUE)).getData(), "Test");

	}

  public void testStringAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    String value = "Test";

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.STRING_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: StringSymbol",
      symbol instanceof StringSymbol);
    assertEquals(TestBean.STRING_VALUE,
      value, ((StringSymbol) symbol).getData());

  }

  public void testIntegerAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    int value = 12;
    
    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.INTEGER_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.INTEGER_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testLongAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    long value = 17;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.LONG_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.LONG_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testShortAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    short value = 24;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.SHORT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.SHORT_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testByteAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    byte value = 127;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BYTE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.BYTE_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testFloatAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    float value = 13.52f;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.FLOAT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.FLOAT_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testDoubleAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.DOUBLE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericSymbol);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testBooleanGetAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    boolean value = true;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_GET_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof StringSymbol);
    assertEquals(TestBean.BOOLEAN_GET_VALUE,
      value?"1":"0", ((StringSymbol) symbol).getData());
  }

  public void testBooleanIsAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    boolean value = false;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_IS_VALUE);
    assertTrue("Correct symbol type 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof StringSymbol);
    assertEquals(TestBean.BOOLEAN_IS_VALUE,
      value?"1":"0", ((StringSymbol) symbol).getData());

  }

  public void testListAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericSymbol);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testArrayAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericSymbol);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

  }

  public void testObjectAttribute() {
    TestBean ts;
    BeanDataSource ds;
    ISymbolTable st;    
    ISymbol symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSource(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericSymbol);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericSymbol) symbol).getData());

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
