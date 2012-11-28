package org.soulspace.template;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;
import org.soulspace.template.value.impl.SymbolTableImpl;

public class AstGeneratorTest extends TestCase {

	TemplateEngineImpl te = null;
	TokenList tl = null;
	Tokenizer t = null;
	AstParserImpl p = null;
	AstGeneratorImpl g = null;
	SymbolTable st = null;
	Environment env = null;
	AstNode root = null;

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

	public void testLineFeeds() {
		String result = "";

		StringBuilder sb = new StringBuilder(128);
		sb.append("Wer reitet so spät\n");
		sb.append(" durch Nacht und Wind?\n");
		sb.append("Es ist der Vater\n");
		sb.append(" mit seinem Kind");

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
		assertEquals("Result correct", sb.toString(), result);

	}

	public void testLineFeeds2() {
		String result = "";

		ListValue list = new ListValueImpl();
		list.addValue(new StringValueImpl("Ju"));
		list.addValue(new StringValueImpl("Hu"));
		st.addSymbol("e", list);

		StringBuilder sb = new StringBuilder(128);
		sb.append("<?foreach s <- e {?>");
		sb.append("get<?s?>();\n");
		sb.append("<?}?>");

		String expected = "getJu();\ngetHu();\n";

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
		assertEquals("Result correct", expected, result);

	}

	public void testLineFeeds3() {
		String result = "";

		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		StringBuilder sb = new StringBuilder(128);
		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<root>\n");
		sb.append("<\\root>");

		String expected = "<?xml version=\"1.0\"?>\n" + "<root>\n" + "<\\root>";

		tl = t.tokenize(sb.toString());
		root = p.parse(tl);
		env = new EnvironmentImpl(st);
		result = g.generate(env, root);
		System.out.println(result);
		assertEquals("Result correct", expected, result);

	}

	public void testGenAssign() {
		String result = "";
		st.addStringValue("a", "Hello World");

		try {
			tl = t.tokenize("<?" + "string b " + "b = a " + "b " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result correct", "Hello World", result);

			tl = t.tokenize("<?" + "string b = a " + "b " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result correct", "Hello World", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGenAssignMapAccess() {
		String result = "";
		SymbolTable map = new SymbolTableImpl();
		map.addStringValue("b", "Greetings");
		st.addStringValue("a", "Hello World");
		st.addMapValue("c", map);
		st.addStringValue("d", "b");
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");

		try {
			tl = t.tokenize("<?string r?>" + "<?r = c[d]?>" + "<?r?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
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
		SymbolTable map1 = new SymbolTableImpl();
		SymbolTable map2 = new SymbolTableImpl();
		map1.addStringValue("Greetings", "Greetings");
		map1.addStringValue("Name", "Olli");
		map2.addStringValue("Greetings", "Howdy");
		map2.addStringValue("Name", "Timur");
		st.addMapValue("myMap1", map1);
		st.addMapValue("myMap2", map2);
		st.addListValue("myList", new ArrayList<Value>());
		((ListValue) st.getSymbol("myList")).addValue(st.getSymbol("myMap1"));
		((ListValue) st.getSymbol("myList")).addValue(st.getSymbol("myMap2"));

		try {
			tl = t.tokenize("<?foreach m <- myList {?>"
					+ "<?m['Greetings']?> <?m['Name']?> " + "<?} ?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
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
			tl = t.tokenize("<?" + "printMessage('hallo') " + ""
					+ "string printMessage(string message) { " + "  message "
					+ "} " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "hallo", result);

			tl = t
					.tokenize("<?"
							+ "print2Messages('hallo', 'welt') "
							+ ""
							+ "string print2Messages(string message1, string message2) { "
							+ "  message1?> <?message2" + "} " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "hallo welt", result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testGenSuperMethods() {
		String result = "";
		st.addStringValue("name", "Ludger Solbach");

		try {
			tl = t.tokenize("<?" + "string printMessage(string message) { "
					+ "?> hyper <?message " + "} "
					+ "string printMessage(string message) { "
					+ "?> super <?message " + "super(message) " + "} "
					+ "string printMessage(string message) {" + "super(message) "
					+ "?> plain <?message " + "} " + "printMessage('hallo') "
					+ "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
			assertEquals("Result", " super hallo hyper hallo plain hallo",
					result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Super methods");
		}

	}

	public void testCallToNonexistingSuperMethod() {
		tl = t.tokenize("<?"
						+ "printMessages('hallo', 'welt') "
						+ ""
						+ "string printMessages(string message1, string message2) { "
						+ "  message1 + ' ' + message2 "
						+ "super() "
						+ "} "
						+ "string printMessages(string message1, string message2, string message3) { "
						+ "  message1 + ' ' + message2 + ' ' + message3" + "} "
						+ "?>");
		root = p.parse(tl);
		try {
			env = new EnvironmentImpl(st);
			g.generate(env, root);
			fail("call to nonexisting super method");
		} catch (GenerateException e) {
			// expected
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected exception");
		}

	}

	public void testPlusOperator() {
		String result = "";
		st.addNumericValue("one", "1");
		st.addNumericValue("three", "3");

		st.addStringValue("FirstName", "Ludger");
		st.addStringValue("LastName", "Solbach");

		st.addListValue("a", new ArrayList<Value>());
		((ListValue) st.getSymbol("a")).addNewStringValue("Hallo");
		((ListValue) st.getSymbol("a")).addNewStringValue("Echo");

		st.addListValue("b", new ArrayList<Value>());
		((ListValue) st.getSymbol("b")).addNewStringValue("Hallo");
		((ListValue) st.getSymbol("b")).addNewStringValue("Ludger");

		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("Greetings", "Greetings");
		map1.addStringValue("Name", "Olli");
		st.addMapValue("m1", map1);

		SymbolTable map2 = new SymbolTableImpl();
		map2.addStringValue("Goodbye", "GoodBye");
		map2.addStringValue("Name", "Timur");
		st.addMapValue("m2", map2);

		try {
			tl = t.tokenize("<?one + three?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "4", result);

			tl = t.tokenize("<?FirstName + ' ' + LastName?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "Ludger Solbach", result);

			tl = t.tokenize("<?" + "list c = a + b " + "c.size() " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "4", result);

			tl = t.tokenize("<? " + "map m = m1 + m2 " + "m.size() " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "3", result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testForeachFilter() {
		String result = "";
		st.addStringValue("name", "Ludger Solbach");

		st.addListValue("xList", new ArrayList<Value>());
		((ListValue) st.getSymbol("xList")).addNewNumericValue("1");
		((ListValue) st.getSymbol("xList")).addNewNumericValue("2");
		((ListValue) st.getSymbol("xList")).addNewNumericValue("3");
		((ListValue) st.getSymbol("xList")).addNewNumericValue("4");
		((ListValue) st.getSymbol("xList")).addNewNumericValue("5");

		try {
			tl = t.tokenize("<?" + "foreach x | (x % 2 == 1) <- xList { "
					+ " x" + "} " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
			assertEquals("Result", "135", result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testGenTypeMethodCalls() {
		String result = "";
		st.addStringValue("name", "Ludger Solbach");
		st.addStringValue("property", "address");
		st.addStringValue("class", "PhoneNumber");
		st.addStringValue("path", "org/soulspace/template/method");
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("Greetings", "Greetings");
		map1.addStringValue("Name", "Olli");
		st.addMapValue("myMap", map1);

		try {
			tl = t.tokenize("<?name.size()?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "14", result);

			tl = t.tokenize("<?name.indexOf('u')?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "1", result);
			
			tl = t.tokenize("<?name.substring(1, 4)?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "udg", result);

			tl = t.tokenize("<?name.toLower()?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "ludger solbach", result);

			tl = t.tokenize("<?name.toUpper()?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "LUDGER SOLBACH", result);

			tl = t.tokenize("<?name.startsWith('Ludger')?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "1", result);

			tl = t.tokenize("<?name.replace('u', 'o')?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "Lodger Solbach", result);

			tl = t.tokenize("<?name.replace('Lud', 'Ro')?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "Roger Solbach", result);

			tl = t.tokenize("<?" + "if(myMap:Greetings.startsWith('Gr')) { "
					+ " myMap:Greetings.toUpper() " + "} " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "GREETINGS", result);

			tl = t.tokenize("<?name.startsWith('Herbert')?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "0", result);

			tl = t.tokenize("get<?property.firstUpper()?>()");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "getAddress()", result);

			tl = t.tokenize("<?class?> <?class.firstLower()?>;");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "PhoneNumber phoneNumber;", result);

			tl = t.tokenize("<?class?> <?class.camelCaseToUnderScore().toUpper().replace('_', '-')?>;");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "PhoneNumber PHONE-NUMBER;", result);

			tl = t.tokenize("<?class?> <?class.camelCaseToUnderScore()?>;");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "PhoneNumber phone_number;", result);

			tl = t.tokenize("<?class?> <?class.camelCaseToUnderScore().camelCaseToUnderScore()?>;");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "PhoneNumber phone_number;", result);

			tl = t.tokenize("<?list parts = path.split('/') parts.size()?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "4", result);

			tl = t.tokenize("<?" + "list pkgs " + "pkgs = path.split('/') "
					+ "foreach pkg <- pkgs { " + " pkg?>\\<? " + "} " + "?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			// assertEquals("Result", "4", result);

			tl = t.tokenize("<?e.size()?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "2", result);

			tl = t.tokenize("<?foreach str <- e {?>" + "<?str.toLower()?> "
					+ "<?}?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "ju hu ", result);

			tl = t.tokenize("<?foreach str <- e {?>" + "<?str.toUpper()?> "
					+ "<?}?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "JU HU ", result);

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
		} catch (GenerateException e) {
			e.printStackTrace();
		}
	}

	public void testGenMethodReturnTypes() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		st.addListValue("d", new ArrayList<Value>());
		try {
			tl = t.tokenize("<?" +
					"list f = reflect(e)" +
					"foreach s <- f {" +
					"  s" +
					"}" +
					"" +
					"list reflect(list myList) {" +
					"  myList " +
					"}" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "JuHu", result);

			tl = t.tokenize("<?" +
					"list f = reflect(e)" +
					"foreach s <- f {" +
					"  s" +
					"}" +
					"" +
					"any reflect(list myList) {" +
					"  myList " +
					"}" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "JuHu", result);

			tl = t.tokenize("<?" +
					"list f = reflect(d)" +
					"foreach s <- f {" +
					"  s" +
					"}" +
					"" +
					"any reflect(list myList) {" +
					"	list result" +
					"	if(myList) {" +
					"		result = myList" +
					"	}" +
					"	result" +
					"}" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "", result);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testGenMethodReturnTypes2() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		st.addListValue("d", new ArrayList<Value>());
		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("Greetings", "Greetings");
		map1.addStringValue("Name", "Oli");
		st.addMapValue("map1", map1);
		SymbolTable map2 = new SymbolTableImpl();
		map2.addStringValue("Greetings", "Howdy");
		map2.addStringValue("Name", "Micha");
		st.addMapValue("map2", map2);
		st.addListValue("List", new ArrayList<Value>());
		((ListValue) st.getSymbol("List")).addValue(st.getSymbol("map1"));
		((ListValue) st.getSymbol("List")).addValue(st.getSymbol("map2"));
		try {
			tl = t.tokenize("<?" +
					"any head(list elements) {\n" +
					"	if(elements) {\n" +
					"		elements[0]\n" +
					"	}\n" +
					"}\n" +
					"\n" +
					"string name(map element) {\n" +
					"	element:Name\n" +
					"}\n" +
					"\n" +
					"name(head(List))\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "Oli", result);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testGenParameterMatching() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Ju");
		((ListValue) st.getSymbol("e")).addNewStringValue("Hu");
		try {
			tl = t.tokenize("<?\n" +
					"toString(e)\n" +
					"string toString(string arg) {\n" +
					"	arg\n" +
					"}\n" +
					"string toString(list argList) {\n" +
					"	foreach item <- argList {\n" +
					"		item\n" +
					"	}\n" +
					"}\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "JuHu", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testCodeComments() {
		String result = "";
		try {
			tl = t.tokenize("<?" +
					"/* this is a code comment */" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "", result);

			tl = t.tokenize("<?" +
					"helloWorld() " +
					"/* this is a code comment for a nice little method */" +
					"string helloWorld() {" +
					"'Hello World!'" +
					"}" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "Hello World!", result);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	public void testGenMethodValues() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Peter");
		((ListValue) st.getSymbol("e")).addNewStringValue("Mary");
		((ListValue) st.getSymbol("e")).addNewStringValue("Helmut");
		try {
			tl = t.tokenize("<?\n" +
					"method h = string toString(string arg) {\n" +
					"	arg\n" +
					"}\n" +
					"h('hello method')\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "hello method", result);

			tl = t.tokenize("<?\n" +
					"method h = string toString(string arg) {\n" +
					"	arg\n" +
					"}\n" +
					"string test(method m, string value) {\n" +
					"	m(value)\n" +
					"}\n" +
					"test(h, 'hello method')\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Result", "hello method", result);

			tl = t.tokenize("<?\n" +
					"method h = string greet(string arg) {\n" +
					"	'hello ' + arg + '!'?>\n" +
					"<?\n" +
					"}\n" +
					"string apply(list e, method m) {\n" +
					"	foreach name <- e {" +
					"		m(name)\n" +
					"	}" +
					"}\n" +
					"apply(e, h)\n" +
					"?>\n");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
			// assertEquals("Result", "hello method", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testGenMethodValues2() {
		String result = "";
		st.addListValue("e", new ArrayList<Value>());
		st.addListValue("mList", new ArrayList<Value>());
		((ListValue) st.getSymbol("e")).addNewStringValue("Peter");
		((ListValue) st.getSymbol("e")).addNewStringValue("Mary");
		((ListValue) st.getSymbol("e")).addNewStringValue("Helmut");
		try {
			tl = t.tokenize("<?\n" +
					"method m1 = string hello(string arg) {\n" +
					"	'hello ' + arg + '!'?>\n" +
					"<?\n" +
					"}\n" +
					"method m2 = string hello(string arg) {\n" +
					"	'goodbye ' + arg + '!'?>\n" +
					"<?\n" +
					"}\n" +
					"mList.add(m1)" +
					"mList.add(m2)" +
					"string apply1(list e, list methodList) {\n" +
					"	foreach name <- e {" +
					"		foreach m <- methodList {" +
					"			m(name)\n" +
					"		}" +
					"	}" +
					"}\n" +
					"string apply2(list e, list methodList) {\n" +
					"	foreach m <- methodList {" +
					"		foreach name <- e {" +
					"			m(name)\n" +
					"		}" +
					"	}" +
					"}\n" +
					"apply1(e, mList)\n" +
					"apply2(e, mList)\n" +
					"?>\n");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
//			assertEquals("Result", "hello method", result);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testGenMethodLookup() {
		String result = "";
		try {
			tl = t.tokenize("<?" +
					"string helloWorld() {" +
					"	'hello world'" +
					"}" +
					"method f = helloWorld\n" +
					"f()" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
//			assertEquals("Result", "hello method", result);

			tl = t.tokenize("<?" +
					"string hello(string name) {" +
					"	'hello ' + name" +
					"}" +
					"method f = hello\n" +
					"f('world')" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
//			assertEquals("Result", "hello method", result);

			tl = t.tokenize("<?\n" +
					"string helloWorld() {\n" +
					"	'hello world'\n" +
					"}\n" +
					"string helloWorld(string name) {\n" +
					"	'hello ' + name\n" +
					"}\n" +
					"string helloWorld(numeric count) {\n" +
					"	numeric i = 0\n" +
					"	while(i < count) {\n" +
					"		'hello world '\n" +
					"		i = i + 1" +
					"	}\n" +
					"}\n" +
					"method f = helloWorld\n" +
					"f()\n" +
					"' '\n" +
					"f('world')\n" +
					"' '\n" +
					"f(3)\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
//			assertEquals("Result", "hello method", result);

			tl = t.tokenize("<?\n" +
					"string helloWorld() {\n" +
					"	'hello world'\n" +
					"}\n" +
					"string call(method f) {\n" +
					"	f()\n" +
					"}\n" +
					"call(helloWorld)\n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			System.out.println(result);
//			assertEquals("Result", "hello method", result);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testGenClosures() {
		String result = "";
		try {
			System.out.println("Lexical scope");
			File[] files = {new File("data/unittest/m_lex.tmpl")};
			te.loadTemplates(files);
			result = te.generate();
			System.out.println(result);
			assertEquals("1:1:2:3\n1, 1, 2, 3", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testAnonMethods() {
		String result = "";
		try {
			System.out.println("Anon method");
			File[] files = {new File("data/unittest/lib.tinc"), new File("data/unittest/anon_method.tmpl")};
			te.loadTemplates(files);
			result = te.generate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testMapClosure() {
		String result = "";

		st.addListValue("myList", new ArrayList<Value>());
		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("Greetings", "Greetings");
		map1.addStringValue("Name", "Oli");
		st.addMapValue("map1", map1);
		SymbolTable map2 = new SymbolTableImpl();
		map2.addStringValue("Greetings", "Howdy");
		map2.addStringValue("Name", "Micha");
		st.addMapValue("map2", map2);
		((ListValue) st.getSymbol("myList")).addValue(st.getSymbol("map1"));
		((ListValue) st.getSymbol("myList")).addValue(st.getSymbol("map2"));
		try {
			System.out.println("Map Closure");
			File[] files = {new File("data/unittest/lib.tinc"), new File("data/unittest/map_closure.tmpl")};
			te.loadTemplates(files);
			result = te.generate(st);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testMapDereferencing() {
		String result = "";

		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("value", "20");
		SymbolTable map2 = new SymbolTableImpl();
		map2.addStringValue("value", "10");
		SymbolTable map3 = new SymbolTableImpl();
		map3.addMapValue("max", map1);
		map3.addMapValue("min", map2);
		SymbolTable map4 = new SymbolTableImpl();
		map4.addStringValue("name", "Test");
		map4.addStringValue("type", "class");
		map4.addMapValue("taggedValueMap", map3);
		st.addMapValue("element", map4);

		try {
			tl = t.tokenize("<?\n" +
					"string max = 'max'\n" +
					"element:name + ' ' + \n" +
					"element:taggedValueMap['min']  + ' ' + \n" +
					"element:taggedValueMap['min']:value  + ' ' + \n" +
					"element:taggedValueMap[max]  + ' ' + \n" +
					"element:taggedValueMap[max]:value \n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			assertEquals("Test 1 10 1 20", result);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testMapDereferencingMethod() {
		String result = "";

		SymbolTable map1 = new SymbolTableImpl();
		map1.addStringValue("value", "20");
		SymbolTable map2 = new SymbolTableImpl();
		map2.addStringValue("value", "10");
		SymbolTable map3 = new SymbolTableImpl();
		map3.addMapValue("max", map1);
		map3.addMapValue("min", map2);
		SymbolTable map4 = new SymbolTableImpl();
		map4.addStringValue("name", "Test");
		map4.addStringValue("type", "class");
		map4.addMapValue("taggedValueMap", map3);
		st.addMapValue("element", map4);

		try {
			tl = t.tokenize("<?\n" +
					"numeric hasTaggedValue(map element, string tag) { \n" +
					"	element:taggedValueMap[tag] \n" +
					"} \n" +
					"string taggedValue(map element, string tag) { \n" +
					"	element:taggedValueMap[tag]:value \n" +
					"} \n" +
					"if(hasTaggedValue(element, 'min')) { \n" +
					"	taggedValue(element, 'min') \n" +
					"} \n" +
					"?>");
			root = p.parse(tl);
			env = new EnvironmentImpl(st);
			result = g.generate(env, root);
			//assertEquals("Test 1 10 1 20", result);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	
//	public void testGenStringConversionCalls() {
//		String result = "";
//		st.addNewStringSymbol("test", new String("ÄÖÜßäöü"));
//		try {
//			tl = t.tokenize("<?" +
//					"test.utf8ToLatin1() +" +
//					"test.latin1ToUtf8()" +
//					"?>");
//			root = p.parse(tl);
//			result = g.generate(root, st);
//			assertEquals("Result", "14", result);
//		} catch (UnknownTokenException e) {
//			e.printStackTrace();
//		} catch (SyntaxException e) {
//			e.printStackTrace();
//		} catch (GenerateException e) {
//			e.printStackTrace();
//		}
//	}

}
