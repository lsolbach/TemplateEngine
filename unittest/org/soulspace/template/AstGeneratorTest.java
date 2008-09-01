package org.soulspace.template;

import java.util.ArrayList;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.SymbolTable;

import junit.framework.TestCase;

public class AstGeneratorTest extends TestCase {

  TemplateEngineImpl te = null;
  ITokenList tl = null;
  Tokenizer t = null;
  AstParserImpl p = null;
  AstGeneratorImpl g = null;
  ISymbolTable st = null;
  IAstNode root = null;
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
    te = new TemplateEngineImpl();
    t = new TokenizerImpl();
    p = new AstParserImpl();
    g = new AstGeneratorImpl();
    st = new SymbolTable();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
    te = null;
    t = null;
    p = null;
    g = null;
    st = null;
    root = null;
    super.tearDown();
	}

	public void testGenAssign() {
    String result = "";
    st.addNewStringSymbol("a", "Hello World");

    try {
	    tl = t.tokenize("<?" +
	    		"string b " +
	    		"b = a " +
	    		"b " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result correct", "Hello World", result);    
    
	    tl = t.tokenize("<?" +
	    		"string b = a " +
	    		"b " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result correct", "Hello World", result);    
    } catch (Exception e) {
      e.printStackTrace();
    }    
	}
	
	public void testGenAssignMapAccess() {
    String result = "";
    ISymbolTable map = new SymbolTable();
    map.addNewStringSymbol("b", "Greetings");
    st.addNewStringSymbol("a", "Hello World");
    st.addNewMapSymbol("c", map);
    st.addNewStringSymbol("d", "b");
    st.addNewListSymbol("e", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("e")).addNewStringSymbol("Ju");
    ((IListValue) st.getSymbol("e")).addNewStringSymbol("Hu");

    try {
	    tl = t.tokenize("<?string r?>" +
	    		"<?r = c[d]?>" +
	    		"<?r?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result correct", "Greetings", result);    
    
    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
    } catch (GenerateException e) {
      e.printStackTrace();
    }    
	}
	
	public void testGenMapAccessInLoopVar() {
    String result = "";
    ISymbolTable map1 = new SymbolTable();
    ISymbolTable map2 = new SymbolTable();
    map1.addNewStringSymbol("Greetings", "Greetings");
    map1.addNewStringSymbol("Name", "Olli");
    map2.addNewStringSymbol("Greetings", "Howdy");
    map2.addNewStringSymbol("Name", "Timur");
    st.addNewMapSymbol("myMap1", map1);
    st.addNewMapSymbol("myMap2", map2);
    st.addNewListSymbol("myList", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("myList")).addSymbol(st.getSymbol("myMap1"));
    ((IListValue) st.getSymbol("myList")).addSymbol(st.getSymbol("myMap2"));

    try {
	    tl = t.tokenize(
	    		"<?foreach m <- myList {?>" +
	    		"<?m['Greetings']?> <?m['Name']?> " +
	    		"<?} ?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "Greetings Olli Howdy Timur ", result);
    
    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
    } catch (GenerateException e) {
      e.printStackTrace();
    }    
	}
	
	public void testGenMethodCall() {
    String result = "";

    try {
	    tl = t.tokenize("<?" +
	    		"printMessage('hallo') " +
	    		"" +
	    		"string printMessage(string message) { " +
	    		"  message " +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "hallo", result);    

	    tl = t.tokenize("<?" +
	    		"print2Messages('hallo', 'welt') " +
	    		"" +
	    		"string print2Messages(string message1, string message2) { " +
	    		"  message1?> <?message2" +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "hallo welt", result);    

    
    } catch (Exception e) {
      e.printStackTrace();
    }    
		
	}
	
	public void testGenSuperMethods() {
    String result = "";
    st.addNewStringSymbol("name", "Ludger Solbach");

    try {
	    tl = t.tokenize("<?" +
	    		"string printMessage(string message) { " +
	    		"?> hyper <?message " +
	    		"} " +
	    		"string printMessage(string message) { " +
	    		"?> super <?message " +
	    		"super() " +
	    		"} " +
	    		"string printMessage(string message) {" +
	    		"super() " +
	    		"?> plain <?message " +
	    		"} " +
	    		"printMessage('hallo') " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", " super hallo hyper hallo plain hallo", result);    
    } catch (Exception e) {
      e.printStackTrace();
    }    
		
	}
	
	public void testCallToNonexistingSuperMethod() {
    String result = "";

    tl = t.tokenize("<?" +
    		"printMessages('hallo', 'welt') " +
    		"" +
    		"string printMessages(string message1, string message2) { " +
    		"  message1 + ' ' + message2 " +
    		"super() " +
    		"} " +
    		"string printMessages(string message1, string message2, string message3) { " +
    		"  message1 + ' ' + message2 + ' ' + message3" +
    		"} " +
    		"?>");
    root = p.parse(tl);
    try {
    	result = g.generate(root, st);
    	fail("call to nonexisting super method");
    } catch (GenerateException e) {
    } catch (Exception e) {
      e.printStackTrace();
    }
		
	}
	
	public void testGenTypeMethodCalls() {
    String result = "";
    st.addNewStringSymbol("name", "Ludger Solbach");
    st.addNewStringSymbol("property", "address");
    st.addNewStringSymbol("class", "PhoneNumber");
    st.addNewStringSymbol("path", "org/soulspace/template/method");
    st.addNewListSymbol("e", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("e")).addNewStringSymbol("Ju");
    ((IListValue) st.getSymbol("e")).addNewStringSymbol("Hu");
    ISymbolTable map1 = new SymbolTable();
    map1.addNewStringSymbol("Greetings", "Greetings");
    map1.addNewStringSymbol("Name", "Olli");
    st.addNewMapSymbol("myMap", map1);

    try {
	    tl = t.tokenize("<?name.toLower()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "ludger solbach", result);
    
	    tl = t.tokenize("<?name.toUpper()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "LUDGER SOLBACH", result);

	    tl = t.tokenize("<?name.startsWith('Ludger')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "1", result);    
	    
	    tl = t.tokenize("<?" +
	    		"if(myMap:Greetings.startsWith('Gr')) { " +
	    		" myMap:Greetings.toUpper() " +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "GREETINGS", result);    
	    
	    tl = t.tokenize("<?name.startsWith('Herbert')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "0", result);
	    
	    tl = t.tokenize("get<?property.firstUpper()?>()");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "getAddress()", result);

	    tl = t.tokenize("<?class?> <?class.firstLower()?>;");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "PhoneNumber phoneNumber;", result);

	    tl = t.tokenize("<?path.split('/')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "4", result);

	    tl = t.tokenize("<?" +
	    		"list pkgs " +
	    		"pkgs = path.split('/') " +
	    		"foreach pkg <- pkgs { " +
	    		" pkg?>\\<? " +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    //assertEquals("Result", "4", result);

	    tl = t.tokenize("<?e.size()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "2", result);

	    tl = t.tokenize(
	    		"<?foreach str <- e {?>" +
	    		"<?str.toLower()?> " +
	    		"<?}?>"
	    		);
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "ju hu ", result);

	    tl = t.tokenize(
	    		"<?foreach str <- e {?>" +
	    		"<?str.toUpper()?> " +
	    		"<?}?>"
	    		);
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "JU HU ", result);

    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
    } catch (GenerateException e) {
      e.printStackTrace();
    }    
	}
	
	public void testPlusOperator() {
    String result = "";
    st.addNewNumericSymbol("one", "1");
    st.addNewNumericSymbol("three", "3");
    
    st.addNewStringSymbol("FirstName", "Ludger");
    st.addNewStringSymbol("LastName", "Solbach");

    st.addNewListSymbol("a", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("a")).addNewStringSymbol("Hallo");
    ((IListValue) st.getSymbol("a")).addNewStringSymbol("Echo");

    st.addNewListSymbol("b", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("b")).addNewStringSymbol("Hallo");
    ((IListValue) st.getSymbol("b")).addNewStringSymbol("Ludger");

    ISymbolTable map1 = new SymbolTable();
    map1.addNewStringSymbol("Greetings", "Greetings");
    map1.addNewStringSymbol("Name", "Olli");
    st.addNewMapSymbol("m1", map1);

    ISymbolTable map2 = new SymbolTable();
    map2.addNewStringSymbol("Goodbye", "GoodBye");
    map2.addNewStringSymbol("Name", "Timur");
    st.addNewMapSymbol("m2", map2);

    try {
	    tl = t.tokenize("<?one + three?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "4", result);

	    tl = t.tokenize("<?FirstName + ' ' + LastName?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "Ludger Solbach", result);
	    
	    tl = t.tokenize("<?" +
	    		"list c = a + b " +
	    		"c.size() " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "4", result);

	    tl = t.tokenize("<? " +
	    		"map m = m1 + m2 " +
	    		"m.size() " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    assertEquals("Result", "3", result);

    } catch (Exception e) {
      e.printStackTrace();
    }    
	    
	}

	public void testForeachFilter() {
    String result = "";
    st.addNewStringSymbol("name", "Ludger Solbach");

    st.addNewListSymbol("xList", new ArrayList<IValue>());
    ((IListValue) st.getSymbol("xList")).addNewNumericSymbol("1");
    ((IListValue) st.getSymbol("xList")).addNewNumericSymbol("2");
    ((IListValue) st.getSymbol("xList")).addNewNumericSymbol("3");
    ((IListValue) st.getSymbol("xList")).addNewNumericSymbol("4");
    ((IListValue) st.getSymbol("xList")).addNewNumericSymbol("5");

    try {
	    tl = t.tokenize("<?" +
	    		"foreach x | (x % 2 == 1) <- xList { " +
	    		" x" +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "135", result);    
    } catch (Exception e) {
      e.printStackTrace();
    }    
		
	}
	

}
