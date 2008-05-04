package org.soulspace.template;

import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstNodeType;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.SymbolTable;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.TokenizerImpl;
import org.soulspace.template.tokenizer.UnknownTokenException;

import junit.framework.TestCase;

public class AstParserTest extends TestCase {

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

  public void testDirectArrayMapAccess() {
    try {
      tl = t.tokenize("<?if(classes[1]:name eq 'Class2') { 'true' }?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
      assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0).getType());
      assertEquals("AST has two children", 2, root.getChild(0).getChildNodes().size());
      
      tl = t.tokenize("<?valueMap['expensive']?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
//      assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0).getType());
//      assertEquals("AST has two children", 2, root.getChild(0).getChildNodes().size());

      tl = t.tokenize("<?taggedValue = Classes:TaggedValueMap['documentation']?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
//      assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0).getType());
//      assertEquals("AST has two children", 2, root.getChild(0).getChildNodes().size());

    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
      fail("SyntaxException: " + e.getMessage());
    }
  }
  
  public void testDirectMapAccessStringConst() {
    try {
  
      tl = t.tokenize("<?valueMap['expensive']?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
//      assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0).getType());
//      assertEquals("AST has two children", 2, root.getChild(0).getChildNodes().size());


    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
      fail("SyntaxException: " + e.getMessage());
    }
  }
	
  public void testDirectMapAccessStringVar() {
    try {
  
      tl = t.tokenize("<?valueMap[price]?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
//      assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0).getType());
//      assertEquals("AST has two children", 2, root.getChild(0).getChildNodes().size());

    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
      fail("SyntaxException: " + e.getMessage());
    }
  }

  public void testTypeMethods() {
    try {
      tl = t.tokenize("<?name.toLower()?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
  
      tl = t.tokenize("<?customer:name.toLower()?>");
      root = p.parseTerm(tl, null, false);
      assertEquals("AST has one child", 1, root.getChildNodes().size());
  
    } catch (UnknownTokenException e) {
      e.printStackTrace();
    } catch (SyntaxException e) {
      e.printStackTrace();
      fail("SyntaxException: " + e.getMessage());
    }
  }
}
