Template Style Guide
====================

Here are some general guidelines for writing maintainable templates.

Rule Number One: Adopt a consistent style and stick to it.

* Name template files with lower case letters only.
* Separate words in the filenames with a `-` for readability
* Name templates with the file extension `.tmpl`
* Name template includes with the file extension `.tinc`
* Place code of general use in library includes named `lib.tinc`

* Symbols defined in template code are camel cased and begin with a lower case letter
* External symbols are camel cased and begin with an upper case letter

When an external model is provided via data binding the following
naming conventions for methods operating on that model apply.

* Methods returning strings to be rendered should have no prefix (e.g. `string name()` instead of `string renderName()`)
* Methods defining a predicate should be prefixed with `is` or `has`, etc (e.g. `isEntity()`, `hasStereotype()`).
* Methods collecting some data from the model should be prefixed with `get` (e.g. `getEndpoint()`).
* Methods collecting a list of elements from the model should be suffixed with `List` (e.g. `list getAssociationList()`).
* Methods collecting a map of elements from the model should be suffixed with `Map` (e.g. `map getImportMap()`).

[Table of Contents] (TemplateEngine.md "Table of Contents")
