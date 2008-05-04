package org.soulspace.template;

import java.util.ArrayList;

import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.SymbolTable;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.TokenizerImpl;
import org.soulspace.template.tokenizer.UnknownTokenException;

import junit.framework.TestCase;

public class AstGeneratorTest extends TestCase {

  TemplateEngineImpl te = null;
  TokenList tl = null;
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

	public void testGenAssignMapAccess() {
    String result = "";
    ISymbolTable map = new SymbolTable();
    map.addNewStringSymbol("b", "Greetings");
    st.addNewStringSymbol("a", "Hello World");
    st.addNewMapSymbol("c", map);
    st.addNewStringSymbol("d", "b");
    st.addNewListSymbol("e", new ArrayList<ISymbol>());
    ((ListSymbol) st.getSymbol("e")).addNewStringSymbol("Ju");
    ((ListSymbol) st.getSymbol("e")).addNewStringSymbol("Hu");

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
    st.addNewListSymbol("myList", new ArrayList<ISymbol>());
    ((ListSymbol) st.getSymbol("myList")).addSymbol(st.getSymbol("myMap1"));
    ((ListSymbol) st.getSymbol("myList")).addSymbol(st.getSymbol("myMap2"));

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
	
	public void testGenTypeMethodCalls() {
    String result = "";
    st.addNewStringSymbol("name", "Ludger Solbach");
    st.addNewStringSymbol("property", "address");
    st.addNewStringSymbol("class", "PhoneNumber");
    st.addNewStringSymbol("path", "org/soulspace/template/method");
    st.addNewListSymbol("e", new ArrayList<ISymbol>());
    ((ListSymbol) st.getSymbol("e")).addNewStringSymbol("Ju");
    ((ListSymbol) st.getSymbol("e")).addNewStringSymbol("Hu");
    ISymbolTable map1 = new SymbolTable();
    map1.addNewStringSymbol("Greetings", "Greetings");
    map1.addNewStringSymbol("Name", "Olli");
    st.addNewMapSymbol("myMap", map1);

    try {
	    tl = t.tokenize("<?name.toLower()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "ludger solbach", result);
    
	    tl = t.tokenize("<?name.toUpper()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "LUDGER SOLBACH", result);

	    tl = t.tokenize("<?name.startsWith('Ludger')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "1", result);    
	    
	    tl = t.tokenize("<?" +
	    		"if(myMap:Greetings.startsWith('Gr')) { " +
	    		" myMap:Greetings.toUpper() " +
	    		"} " +
	    		"?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "GREETINGS", result);    
	    
	    tl = t.tokenize("<?name.startsWith('Herbert')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "0", result);
	    
	    tl = t.tokenize("get<?property.firstUpper()?>()");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "getAddress()", result);

	    tl = t.tokenize("<?class?> <?class.firstLower()?>;");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "PhoneNumber phoneNumber;", result);

	    tl = t.tokenize("<?path.split('/')?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
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
	    System.out.println(result);
	    //assertEquals("Result", "4", result);

	    tl = t.tokenize("<?e.size()?>");
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "2", result);

	    tl = t.tokenize(
	    		"<?foreach str <- e {?>" +
	    		"<?str.toLower()?> " +
	    		"<?}?>"
	    		);
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "ju hu ", result);

	    tl = t.tokenize(
	    		"<?foreach str <- e {?>" +
	    		"<?str.toUpper()?> " +
	    		"<?}?>"
	    		);
	    root = p.parse(tl);
	    result = g.generate(root, st);
	    System.out.println(result);
	    assertEquals("Result", "JU HU ", result);

     } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
    } catch (GenerateException e) {
      e.printStackTrace();
    }    
	}
}
