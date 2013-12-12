Template Engine
===============

The soulspace.org TemplateEngine is a template engine written in Java
(as you might have guessed). It consists of a template language and an
engine for processing the language and transforming and emitting texts.

Features:
---------
* general purpose template engine
* syntax is easy to learn
* simple things are easy, complex things are possible
* data binding for java beans (use bean objects as input) and xml files
* view language, safe by design, no modification of input java objects possible
	(if getters on objects have no side effects, which is bad design anyway)
* string, numeric, list, map and method datatypes
* arithmetic, relational and logical operators
* branching with if/else, while loops and foreach loops with filters
* declaration of variables and methods within the templates
* full recursion and overriding support for methods
* functional programming support
	* methods as first class citizens
	* closures
	* higher order methods
	* methods as return value (function builders)
* extensible library of type methods (e.g. s.split(';') or s.toLower() on strings)

Documentation
-------------
See `doc/TemplateEngine.md`.

Author/Project Lead
-------------------
Ludger Solbach

License
-------
[Eclipse Public License 1.0] (http://www.eclipse.org/legal/epl-v10.html "EPL 1.0")

Copyright
---------
© 2002-2012 Ludger Solbach

Runtime Requirements:
---------------------
Jakarta ORO >= 2.0.8 (could be refactored to use standard Java RegEx,
then there would be no external dependencies anymore. Volunteers?)

Code Repository
---------------
[https://github.com/lsolbach/TemplateEngine] (https://github.com/lsolbach/TemplateEngine)

History
-------

Version 1.0.2 (--.--.2013)
--------------------------
* changed camelCaseToUnderScore() handling for consecutive upper case characters

Version 1.0.1 (05.08.2013)
--------------------------
* refactored module.clj
* added DataSourceUtil to dump the content for debugging

Version 1.0.0 (08.03.2013)
--------------------------
* initial import to github
* switched to git
* added XmlDataSource for generic XML data binding
* added method datatype as first class citizen to enable functional programming
* added closures
* added list and map as method return types
* added *.type() method
* added list.reverse() method
* added string.replace() method
* added string.endsWith() method
* added list.clearList() map.remove() and map.clearMap() methods
* added methods to MapValue to make them more convenient
* added MethodRegistry.register() and DynamicMethodRegistryImpl.registerPackage()
* made method registries configurable
* fixed tokenizer bug for identifiers starting with keywords
* fixed tokenizer for better matching of a xml declaration
* fixed cascading of type methods
* fixed camelCaseToUnderScore() not to duplicate underscores
* suppressed string evaluation of numerics, lists and maps when not in string context
* enhanced diagnostic output on Exceptions
* refactored code for robustness, removed review issues
* refactored names of interfaces and classes
* refactored method call mechanism for template methods
* refactored symbol tables in AST nodes to a node independent value environment
* refactored and enhanced the documentation
* updated docs

Version 0.9.17 (21.09.2009)
---------------------------
* aded methods string.matches(), string.substring(), string.indexOf() and string.size()
* fixed string.firstUpper() and string.firstLower() for empty strings
* added TemplateEngine.generate() without parameters
* fixed newline handling
* refactored AstParserImpl

Version 0.9.15 (01.09.2008)
---------------------------
* package refactorings, removed cycles and enhanced the structure of the dependencies
* method overloading, foreach with filters
* better error reporting
* new type methods map.keyList(), list.add(), list.hasNext(), map.put()

Versions before (since 2002)
----------------------------
* everything else :-)
