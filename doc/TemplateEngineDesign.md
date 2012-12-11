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
One part is the parser for the Template Language which is essentialy a
2 pass parser. In the first pass the template is converted to a list of
tokens by the Tokenizer. The second pass with the ASTParser transforms
the list of tokens into an abstract syntax tree (AST).

Tokenizer
---------
The Tokenizer is heavily based on pattern matching with regular
expressions. There are 2 regular expressions. The first rexpression
splits the template string into the TEXT, COMMENT and CODE sections.
The second regular expression is used to split the CODE section into
single tokens. These Tokens are then classified based on the pattern
and appended to the TokenList.

Parser
------
The AstParser is a recursive descent parser that transforms the list
of Tokens into the abstract syntax tree. The Parser is handwritten to
allow some syntactic sugar. The nodes of the AST are typed and created
by the AstNodeFactory based on the type of current Token.

Generator
---------
The AstGenerator calls the generate() method on the RootNode of the
AST. Each AST Node knows how to generate itself.
Environment

Type Methods
------------


[Table of Content] (TemplateEngine.md "Table of Content")
