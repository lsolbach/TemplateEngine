/*
 * Created on Apr 28, 2004
 *
 */
package org.soulspace.template.datasource;

import junit.framework.TestCase;

import org.soulspace.template.datasource.impl.BeanDataSourceImpl;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;

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
    BeanDataSourceImpl ds;
    SymbolTable st;    

    ts = new TestBean("Test");
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    assertEquals(TestBean.STRING_VALUE,
      ((StringValue) st.getSymbol(TestBean.STRING_VALUE)).getData(), "Test");

	}

  public void testStringAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    String value = "Test";

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.STRING_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: StringSymbol",
      symbol instanceof StringValue);
    assertEquals(TestBean.STRING_VALUE,
      value, ((StringValue) symbol).getData());

  }

  public void testIntegerAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    int value = 12;
    
    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.INTEGER_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.INTEGER_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testLongAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    long value = 17;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.LONG_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.LONG_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testShortAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    short value = 24;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.SHORT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.SHORT_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testByteAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    byte value = 127;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BYTE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.BYTE_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testFloatAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    float value = 13.52f;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.FLOAT_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.FLOAT_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testDoubleAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.DOUBLE_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: NumericSymbol",
      symbol instanceof NumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testBooleanGetAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    boolean value = true;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_GET_VALUE);
    assertNotNull("Symbol found: ", symbol);
    assertTrue("Correct symbol type: 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof StringValue);
    assertEquals(TestBean.BOOLEAN_GET_VALUE,
      value?"1":"0", ((StringValue) symbol).getData());
  }

  public void testBooleanIsAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    boolean value = false;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    symbol = st.getSymbol(TestBean.BOOLEAN_IS_VALUE);
    assertTrue("Correct symbol type 'StringSymbol' found " + symbol.getClass().getName(),
      symbol instanceof StringValue);
    assertEquals(TestBean.BOOLEAN_IS_VALUE,
      value?"1":"0", ((StringValue) symbol).getData());

  }

  public void testListAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testArrayAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }

  public void testObjectAttribute() {
    TestBean ts;
    BeanDataSourceImpl ds;
    SymbolTable st;    
    Value symbol;

    double value = 15.32;

    ts = new TestBean(value);
    ds = new BeanDataSourceImpl(ts);
    st = ds.getSymbolTable();
    assertTrue("Correct symbol type: NumericSymbol",
      (symbol = st.getSymbol(TestBean.DOUBLE_VALUE)) instanceof NumericValue);
    assertEquals(TestBean.DOUBLE_VALUE,
      String.valueOf(value), ((NumericValue) symbol).getData());

  }


	/*
	 * Test for boolean parse(SymbolTable, Object)
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
	 * Test for void insert(SymbolTable, String, Object)
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
