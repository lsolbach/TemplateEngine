Template Style Guide
====================

Adopt a consistent style and stick to it.

Here are some general guidelines for writing maintainable templates.

* The name of the template files have only lower case letters with a '-' between words for readability
* The file extension for templates is '.tmpl'
* The file extension for template includes is '.tinc'
* Code of general use is placed in library includes named 'lib.tinc'

* Symbols defined in template code are camel cased and begin with a lower case letter
* External symbols are camel cased and begin with an upper case letter

When an external model is provided via data binding the following naming conventions for methods operating on that model apply.

* Methods returning renderable strings have no prefix
	(e.g. name() instead of renderName())
* Methods defining a boolean predicate should be prefixed with 'is' or 'has', etc
	(e.g. isEntity(), hasStereotype())
* Methods collecting some data from the model should be prefixed with 'get'
	(e.g. getEndpoint())
* Methods collecting a list of elements from the model should be suffixed with 'List'
	(e.g. getAssociationList())
* Methods collecting a map of elements from the model should be suffixed with 'Map'
	(e.g. getImportMap())


[Table of Contents] (TemplateEngine.md "Table of Contents")
