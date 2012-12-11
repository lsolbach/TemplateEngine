Design of the Template Engine
=============================

Design Goals
------------
The main design goal is to build an easy to use yet powerful template
engine.

The template language should be a **view language**, it should not be
possible to modify the underlying data model from within the templates.

For modular and elegant templates the template language should support
functional programming.

Design Overview
---------------

The Template Engine consists of some different parts.
One part is the parser for the Template Language which is essentialy a 2 pass parser.
In the first pass the template is converted to a list of tokens by the Tokenizer.
The second pass transforms the list of tokens into an abstract syntax tree (AST).

Tokenizer
---------
The Tokenizer is based heavily on Regular Expressions.

Parser
------
Recursive descent
Abstract Syntax Tree

Generator
---------
AST Nodes know how to generate themselves
Environment

Type Methods
------------


[Table of Content] (TemplateEngine.md "Table of Content")
