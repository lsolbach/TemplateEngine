Template Engine
===============

presentation language and template engine

Features:

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

Runtime Requirements:
Jakarta ORO >= 2.0.8

Version 0.9.15 (01.09.2008)
---------------------------

* package refactorings, removed cycles and enhanced the structure of the dependencies
* method overloading, foreach with filters
* better error reporting
* new type methods map.keyList(), list.add(), list.hasNext(), map.put()
