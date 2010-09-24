Template Engine
===============

presentation language and template engine

Features:
---------

* syntax easy to learn
* string, numeric, list and map variables
* declaration of new variables within the templates
* building of complex object structures ala Perl
* numeric and string operators
* data binding for java beans, use bean objects as input
* extensible library of type methods (e.g. a.toLower() on strings)
* defineable methods with recursion support in templates
* filters for iterations
* safe by design, no modification of input java objects possible
	(if getters have no side effects, which is bad design anyway)

Runtime Requirements:
---------------------

Jakarta ORO >= 2.0.8 (could be refactored to use standard Java RegEx,
then there would be no external dependencies anymore. Volunteers?)


Version 1.0.0 (--.--.2010)
---------------------------

* made method return types list and map possible
* added string.replace() method
* added string.endsWith() method
* added string.utf8tolatin1() and string.latin1ToUtf8() methods (TODO validate)
* added list.clearList() and map.clearMap() methods
* added IMethodRegistry.register() and DynamicMethodRegistry.registerPackage()
* refactored code for robustness, removed review issues
* fixed tokenizer bug for identifiers starting with keywords
* suppressed string evaluation of numerics, lists and maps when not in string context
* enhanced diagnosis output on GenerateExceptions

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
