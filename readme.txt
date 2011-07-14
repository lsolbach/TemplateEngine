Template Engine
===============

The project TemplateEngine consists of a presentation language and an
engine for processing the language and transforming and emmitting texts

Features:
---------

* simple things are easy to accomplish, really complex things are possible
* syntax is easy to learn
* data binding for java beans (use bean objects as input) and xml files
* string, numeric, list, map and method datatypes
* arithmetic, relational and logical operators
* branching with if/else
* while loops and foreach loops with filters
* declaration of variables and methods within the templates
* full recursion and overriding support for methods
* functional programming support
	* methods as first class citizens
	* closures
	* higher order methods
	* methods as return value (function builders)
* safe by design, no modification of input java objects possible
	(if getters on objects have no side effects, which is bad design anyway)
* extensible library of type methods (e.g. a.toLower() on strings)
* enables building of complex data structures
* turing complete language

Rationale:
----------


Runtime Requirements:
---------------------

Jakarta ORO >= 2.0.8 (could be refactored to use standard Java RegEx,
then there would be no external dependencies anymore. Volunteers?)


Version 1.0.0 (--.--.2011)
--------------------------

* added XmlDataSource for generic XML data binding
* added method datatype as first class citizen to enable functional programming
* added closures
* added list and map as method return types
* added *.type() method
* added list.reverse() method
* added string.replace() method
* added string.endsWith() method
* added list.clearList() and map.clearMap() methods
* added MethodRegistry.register() and DynamicMethodRegistryImpl.registerPackage()
* made method registries configurable
* refactored code for robustness, removed review issues
* fixed tokenizer bug for identifiers starting with keywords
* fixed tokenizer for better matching of a xml declaration
* suppressed string evaluation of numerics, lists and maps when not in string context
* added methods to MapValue to make them more convenient
* enhanced diagnostic output on GenerateExceptions
* refactored names of interfaces and classes

TODO
* validate string.utf8tolatin1() and string.latin1ToUtf8() methods


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
