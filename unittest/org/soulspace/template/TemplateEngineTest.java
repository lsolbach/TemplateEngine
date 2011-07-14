/*
 * Created on May 2, 2004
 *
 */
package org.soulspace.template;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.parser.ast.impl.RootNodeImpl;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.SymbolTableImpl;

/**
 * Unit test for TemplateEngine.
 * 
 * @author soulman
 */
public class TemplateEngineTest extends TestCase {

	TemplateEngineImpl te = null;
	TokenList tl = null;
	Tokenizer t = null;
	AstParserImpl p = null;
	AstGeneratorImpl g = null;
	Environment env = null;
	SymbolTable st = null;
	AstNode root = null;

	/**
	 * Constructor for TemplateEngineTest.
	 * 
	 * @param arg0
	 */
	public TemplateEngineTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		te = new TemplateEngineImpl();
		t = new TokenizerImpl();
		p = new AstParserImpl();
		g = new AstGeneratorImpl();
		st = new SymbolTableImpl();
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

	public void testParse() {
		String template = "";
		try {
			tl = t.tokenize(template);
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testTokenize() {
		try {
			tl = t
					.tokenize("<html><head></head><body><?foreach Chapter <- Chapters {?><h1><?Chapter:Heading?</h1><?}?></body></html>");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}

	}

	public void testAstHtml() {
		try {
			AstNode root = null;

			tl = t.tokenize("<html></html>");
			root = p.parseTerm(tl, null, false);
			assertNotNull("AST created", root);
			assertEquals("AST has one child", 1, root.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstEmbeddedIdentifier() {
		try {
			AstNode root = null;

			tl = t.tokenize("<html><?CONTENT?></html>");
			root = p.parseTerm(tl, null, false);
			assertNotNull("AST created", root);
			assertEquals("AST has three children", 3, root.getChildNodes()
					.size());
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root
					.getChild(1).getType());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstAssignExpr() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a = b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting ASSIGN ", AstNodeType.ASSIGN, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstLogicalOrExpr() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a || b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LOGICAL_OR", AstNodeType.LOGICAL_OR, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstLogicalOrExpr2() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?(a || b || c)?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LOGICAL_OR", AstNodeType.LOGICAL_OR, root
					.getChild(0).getType());
			assertEquals("AST has tree children", 3, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?a || b || c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LOGICAL_OR", AstNodeType.LOGICAL_OR, root
					.getChild(0).getType());
			assertEquals("AST has tree children", 3, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstLogicalAndExpr() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a && b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LOGICAL_AND", AstNodeType.LOGICAL_AND, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstLogicalAndExpr2() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a && b && c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LOGICAL_AND", AstNodeType.LOGICAL_AND, root
					.getChild(0).getType());
			assertEquals("AST has tree children", 3, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstEqualExprNumericEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a == b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting EQUAL", AstNodeType.EQUAL, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstEqualExprNumericNotEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a != b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting NOT_EQUAL", AstNodeType.NOT_EQUAL, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstEqualExprStringEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a eq b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting STRING_EQUAL", AstNodeType.STRING_EQUAL,
					root.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstEqualExprStringNotEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a ne b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting STRING_NOT_EQUAL",
					AstNodeType.STRING_NOT_EQUAL, root.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstRelExprGreater() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a > b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting GREATER", AstNodeType.GREATER, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstRelExprGreaterEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?(a >= b)?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting GREATER_EQUAL", AstNodeType.GREATER_EQUAL,
					root.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstRelExprLess() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a < b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LESS", AstNodeType.LESS, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstRelExprLessEqual() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a <= b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting LESS_EQUAL", AstNodeType.LESS_EQUAL, root
					.getChild(0).getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstAddExprPlus() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a + b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting PLUS", AstNodeType.PLUS, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?a + b + c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting PLUS", AstNodeType.PLUS, root.getChild(0)
					.getType());
			assertEquals("AST has tree children", 3, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstAddExprMinus() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a - b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting MINUS", AstNodeType.MINUS, root.getChild(0)
					.getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 2);

			tl = t.tokenize("<?a - b + c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting PLUS", AstNodeType.PLUS, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());
			assertEquals("Expecting MINUS", AstNodeType.MINUS, root.getChild(0)
					.getChild(0).getType());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstMultExprMult() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a * b?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting MULT", AstNodeType.MULT, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?a * b * c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting MULT", AstNodeType.MULT, root.getChild(0)
					.getType());
			assertEquals("AST has tree children", 3, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?a / b * c?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting MULT", AstNodeType.MULT, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());
			assertEquals("Expecting DIV", AstNodeType.DIV, root.getChild(0)
					.getChild(0).getType());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstMultExprDiv() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a / b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting DIV", AstNodeType.DIV, root.getChild(0)
					.getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 2);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstMultExprIDiv() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a // b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting IDIV", AstNodeType.IDIV, root.getChild(0)
					.getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 2);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstMultExprModulo() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?a % b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting MODULO", AstNodeType.MODULO, root.getChild(
					0).getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 2);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstUnaryExprNot() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?!b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting LOGICAL_NOT", AstNodeType.LOGICAL_NOT, root
					.getChild(0).getType());
			assertTrue("AST has one child", root.getChild(0).getChildNodes()
					.size() == 1);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstUnaryExprMinus() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?-b?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting MINUS", AstNodeType.MINUS, root.getChild(0)
					.getType());
			assertTrue("AST has one child", root.getChild(0).getChildNodes()
					.size() == 1);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstPrimaryExprParen() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?(a)?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root
					.getChild(0).getType());

			tl = t.tokenize("<?((a))?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root
					.getChild(0).getType());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstPrimaryExprBracket() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?b[a]?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root
					.getChild(0).getType());
			assertTrue("IDENTIFIER has one child", root.getChild(0)
					.getChildNodes().size() == 1);
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root
					.getChild(0).getChild(0).getType());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstPrimaryExprNumericConst() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?1.5?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting NUMERIC_CONST", AstNodeType.NUMERIC_CONST,
					root.getChild(0).getType());
			// assertTrue("AST has one child",
			// root.getChild(0).getChildNodes().size() == 1);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstPrimaryExprStringConst() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?'Hello'?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting STRING_CONST", AstNodeType.STRING_CONST,
					root.getChild(0).getType());
			// assertTrue("AST has one child",
			// root.getChild(0).getChildNodes().size() == 1);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testDirectArrayMapAccess() {
		try {
			tl = t.tokenize("<?if(classes[1]:name eq 'Class2') { 'true' }?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?valueMap['expensive']?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			// assertEquals("Expecting IF", AstNodeType.IF,
			// root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

			tl = t
					.tokenize("<?taggedValue = Classes:TaggedValueMap['documentation']?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			// assertEquals("Expecting IF", AstNodeType.IF,
			// root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstIf() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?if(a) { b }?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());
			// assertEquals("Expectin");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstIfElse() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?if (a) { b } else { c }?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0)
					.getType());
			assertTrue("AST has three children", root.getChild(0)
					.getChildNodes().size() == 3);
			// assertEquals("Expectin");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testAstForeach() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?" + "foreach x <- a { " + " b " + "}" + "?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting FOREACH", AstNodeType.FOREACH, root
					.getChild(0).getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 3);
			// assertEquals("Expectin");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstWhile() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?while(a) { b }?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting WHILE", AstNodeType.WHILE, root.getChild(0)
					.getType());
			assertTrue("AST has two children", root.getChild(0).getChildNodes()
					.size() == 2);
			// assertEquals("Expectin");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstBlock() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?{ a } { b }?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has two child", root.getChildNodes().size() == 2);
			assertEquals("Expecting TERM", AstNodeType.TERM, root.getChild(0)
					.getType());
			assertTrue("AST has 1 children", root.getChild(0).getChildNodes()
					.size() == 1);
			// assertEquals("Expecting");
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstDeclaration() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?string a?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting DECLARATION", AstNodeType.DECLARATION, root
					.getChild(0).getType());
			assertTrue("AST has 1 children", root.getChild(0).getChildNodes()
					.size() == 1);
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstBlockInBlock() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?foreach x <- a { if (x) { b } }?>");
			root = p.parseTerm(tl, null, false);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			assertEquals("Expecting FOREACH", AstNodeType.FOREACH, root
					.getChild(0).getType());
			assertTrue("AST has 2 children", root.getChild(0).getChildNodes()
					.size() == 3);
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstMethodDeclaration() {
		try {
			AstNode root = null;

			tl = t.tokenize("<?string echo(string a) { a }?>");
			root = p.parse(tl);
			assertTrue("AST has one child", root.getChildNodes().size() == 1);
			// assertEquals("Expecting IDENTIFIER", AstNodeType.METHOD,
			// root.getMethodNode("echo").getType());
			// assertEquals("AST has 1 child", 1,
			// root.getChild(0).getChildNodes().size());
			// assertEquals("Expecting ARG_LIST", AstNodeType.ARG_LIST,
			// root.getChild(0).getChild(0).getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testAstMethodCall() {
		AstNode root = null;

		tl = t.tokenize("<?string a?><?a = 'hello World'?><?echo(a)?>");
		root = p.parse(tl);
		assertTrue("AST has one child", root.getChildNodes().size() == 1);
		assertEquals("Expecting METHOD_CALL", AstNodeType.METHOD_CALL, root
				.getChild(0).getChild(2).getType());
		// assertEquals("AST has 1 child", 1,
		// root.getChild(0).getChildNodes().size());
		// assertEquals("Expecting ARG_LIST", AstNodeType.ARG_LIST,
		// root.getChild(0).getChild(0).getType());
	}

	public void testAstTypeMethodCall() {
		AstNode root = null;

		tl = t.tokenize("<?" + "string a " + "a = 'hello World' "
				+ "a.firstUpper()" + "?>");
		root = p.parse(tl);
		assertTrue("AST has one child", root.getChildNodes().size() == 1);
		assertEquals("Expecting TYPE_METHOD_CALL",
				AstNodeType.TYPE_METHOD_CALL, root.getChild(0).getChild(2)
						.getType());
		// assertEquals("AST has 1 child", 1,
		// root.getChild(0).getChildNodes().size());
		// assertEquals("Expecting ARG_LIST", AstNodeType.ARG_LIST,
		// root.getChild(0).getChild(0).getType());
	}

	public void testAstGenerator() {
		String result = "";

		tl = t.tokenize("<html></html>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "<html></html>", result);
	}

	public void testAstGenPlus() {
		String result = "";

		tl = t.tokenize("<?6.0 + 3.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "9", result);

		tl = t.tokenize("<?6.0 + -6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?-6.0 + 4.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "-2", result);

		tl = t.tokenize("<?6.0 + 0.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "6", result);
	}

	public void testAstGenMinus() {
		String result = "";

		tl = t.tokenize("<?6.0 - 3.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "3", result);

		tl = t.tokenize("<?6.0 - 6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?6.0 - -4.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "10", result);

		tl = t.tokenize("<?6.0 - 0.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "6", result);
	}

	public void testAstGenMult() {
		String result = "";

		tl = t.tokenize("<?6.0 * 3.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "18", result);

		tl = t.tokenize("<?6.0 * -6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "-36", result);

		tl = t.tokenize("<?6.0 * 0.5?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "3", result);
	}

	public void testAstGenDiv() {
		String result = "";

		tl = t.tokenize("<?6.0 / 3.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "2", result);

		tl = t.tokenize("<?6.0 / 6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);

		tl = t.tokenize("<?6.0 / 4.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1.5", result);

		try {
			tl = t.tokenize("<?6.0 / 0.0?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			fail("Generate Exception (Division by zero!) expected");
		} catch (GenerateException e) {
		}
	}

	public void testAstGenIDiv() {
		String result = "";

		tl = t.tokenize("<?6.0 // 3?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "2", result);

		tl = t.tokenize("<?6.0 // 6?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);

		tl = t.tokenize("<?6.0 // 4?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);

		tl = t.tokenize("<?6.0 // 9?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		try {
			tl = t.tokenize("<?6.0 // 3.0?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			fail("Generate Exception (Not Integer!) expected");
		} catch (GenerateException e) {
		}

		try {
			tl = t.tokenize("<?6.0 // 0.0?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			fail("Generate Exception (Division by zero!) expected");
		} catch (GenerateException e) {
		}
	}

	public void testAstGenModulo() {
		String result = "";

		tl = t.tokenize("<?6.0 % 3.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?6.0 % 6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?6.0 % -6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?3.0 % 6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "3", result);

		tl = t.tokenize("<?9.0 % 6.0?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "3", result);
	}

	public void testAstGenConsts() {
		String result = "";

		tl = t.tokenize("<?-2.5 * 3?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "-7.5", result);

		tl = t.tokenize("<?'Hallo' eq 'Hallo'?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);

		tl = t.tokenize("<?'Hallo' ne 'Hello'?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);
	}

	public void testAstGenLazyEvaluation() {
		String result = "";
		st.addStringValue("a", "0");
		st.addStringValue("b", "0");
		st.addStringValue("c", "1");
		st.addStringValue("d", "1");

		tl = t.tokenize("<?c && a && e?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "0", result);

		tl = t.tokenize("<?a || c || e?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "1", result);
	}

	public void testAstGenIdentifier() {
		String result = "";
		SymbolTable map = new SymbolTableImpl();
		map.addStringValue("b", "Greetings");
		st.addStringValue("a", "Hello World");
		st.addMapValue("c", map);
		st.addStringValue("d", "b");
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		tl = t.tokenize("<?a?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Hello World", result);

		tl = t.tokenize("<?c[d]?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Greetings", result);

		tl = t.tokenize("<?e[0]?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Ju", result);

		tl = t.tokenize("<?e[1]?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Hu", result);
	}

	public void testAstGenDecl() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		tl = t.tokenize("<?string da?><?da = 'Hello'?><?da?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Hello", result);

		tl = t.tokenize("<?numeric db?><?db = 12.5?><?db?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "12.5", result);

		tl = t.tokenize("<?list dc?><?dc = e?><?dc[0]?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Ju", result);

		st.addStringValue("da", "Schon da!");
		tl = t.tokenize("<?string da?><?da = 'Hello'?><?da?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		// FIXME validate correct behaviour
	}

	public void testAstGenIf() {
		String result = "";
		SymbolTable map = new SymbolTableImpl();
		st.addStringValue("b", "Greetings");
		st.addStringValue("a", "Hello World");
		st.addMapValue("c", map);
		st.addStringValue("d", "b");
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		tl = t.tokenize("<?if(1) { a } else { b }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Hello World", result);

		tl = t.tokenize("<?if(0) { a } else { b }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Greetings", result);
	}

	public void testAstGenIfComplex() {
		String result = "";
		SymbolTable map = new SymbolTableImpl();
		st.addStringValue("b", "Greetings");
		st.addStringValue("a", "Hello World");
		st.addMapValue("c", map);
		st.addStringValue("d", "b");
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		tl = t.tokenize("<?if(1) { a } else { b } if(0) { a } else { b }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "Hello WorldGreetings", result);
	}

	public void testAstGenForeach() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		((ListValue) st.getSymbol("e")).addNewStringValue("Bu");

		tl = t.tokenize("<?foreach x <- e { x }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "JuHuBu", result);

		tl = t.tokenize("<content><?foreach x <- e { x }?></content>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "<content>JuHuBu</content>", result);

		tl = t.tokenize("<?foreach x <- e { x } foreach x <- e { x }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "JuHuBuJuHuBu", result);
	}

	public void testAstGenWhile() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		((ListValue) st.getSymbol("e")).addNewStringValue("Bu");
		tl = t.tokenize("<?" + "numeric i " + "i = 0 " + "while(i < 3) { "
				+ "  e[i] " + "  i = i + 1 " + "}" + "?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "JuHuBu", result);
	}

	public void testAstGenTerm() {
		String result = "";
		SymbolTable map = new SymbolTableImpl();
		map.addStringValue("name", "Test");
		map.addListValue("attrs", new ArrayList<Value>());
		((ListValue) map.getSymbol("attrs")).addNewStringValue("attr1");
		((ListValue) map.getSymbol("attrs")).addNewStringValue("attr2");
		((ListValue) map.getSymbol("attrs")).addNewStringValue("attr3");

		st.addMapValue("class", map);
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		((ListValue) st.getSymbol("e")).addNewStringValue("Bu");

		tl = t.tokenize("<?numeric i i = 0 while(i < 3) { e[i] i = i + 1 }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "JuHuBu", result);
	}

	public void testAstGenDirectArrayMapAccess() {
		String result = "";

		SymbolTable m;
		SymbolTable n;
		st.addListValue("classes", new ArrayList<Value>());
		ListValue classes = ((ListValue) st.getSymbol("classes"));
		m = new SymbolTableImpl();
		m.addStringValue("name", "Class1");
		classes.addNewMapValue(m);
		m = new SymbolTableImpl();
		m.addStringValue("name", "Class2");
		classes.addNewMapValue(m);
		m = new SymbolTableImpl();
		m.addStringValue("name", "Class3");
		classes.addNewMapValue(m);
		n = new SymbolTableImpl();
		m.addMapValue("TaggedValueMap", n);

		tl = t.tokenize("<?if(classes[1]:name eq 'Class2') { 'true' }?>");
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		assertEquals("Result correct", "true", result);
	}

	public void testAstGenMethod1() {
		String result = "";
		StringBuilder sb = new StringBuilder(64);

		sb.append("<?string helloWorld() { 'Hello World' }?>\n");
		sb.append("<?helloWorld()?> \n");
		sb.append("<?helloWorld()?> \n");

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
	}

	public void testAstGenMethod2() {
		String result = "";
		StringBuilder sb = new StringBuilder(64);

		sb.append("<?string echo(string a) { a }?>\n");
		sb.append("<?echo('hello')?> \n");
		sb.append("<?echo('world')?> \n");

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
	}

	public void testAstGenMethod3() {
		String result = "";
		StringBuilder sb = new StringBuilder(64);

		sb.append("<?string cat(string a, string b) { a ?>:<?b }?>\n");
		sb.append("<?cat(x, y)?> \n");
		sb.append("<?cat('Hallo', 'cat')?> \n");

		st.addStringValue("x", "Hallo");
		st.addStringValue("y", "Welt");

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
	}

	public void testIsNumeric() {
		boolean result;

		RootNodeImpl node = new RootNodeImpl();
		result = node.isNumeric("Hallo");
		assertFalse("'Hallo' is not numeric", result);

		result = node.isNumeric("1.5");
		assertTrue("'1.5' is numeric", result);

		result = node.isNumeric("-1.5");
		assertTrue("'-1.5' is numeric", result);

		result = node.isNumeric("12");
		assertTrue("'12' is numeric", result);

	}

	public void testRoundResult() {
		String result;
		RootNodeImpl node = new RootNodeImpl();
		result = node.roundResult("1.0");
		assertEquals("Round '1.0'", "1", result);

		result = node.roundResult("-1.0");
		assertEquals("Round '-1.0'", "-1", result);

		result = node.roundResult("1.5");
		assertEquals("Don't Round '1.5'", "1.5", result);

		result = node.roundResult("-1.5");
		assertEquals("Don't Round '-1.5'", "-1.5", result);
	}

}
