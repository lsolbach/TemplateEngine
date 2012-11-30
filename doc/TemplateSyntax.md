Syntax of the Template Language
===============================

Grammar
-------

```
START = {(TEXT | XML_DECL | '<?!--', comment ,'--?>' | '<?', CODE, ?>)} ;

TEXT = ? arbitrary text ? ;

CODE = TERM ;

TERM = {STMT | EXPR} ;

STMT = IF_STMT | WHILE_STMT | FOREACH_STMT | DECL | BLOCK_STMT ;

IF_STMT = 'if', EXPR, STMT [, 'else', STMT] ;

WHILE_STMT = 'while', EXPR, STMT ;

FOREACH_STMT = 'foreach', IDENTIFIER, ['|', EXPR, ] '<-', EXPR, STMT ;

DECL_STMT = SYMBOL_DECL | METHOD_DECL ;

BLOCK_STMT = '{', {STMT}, '}' ;

SYMBOL_DECL = TYPE, IDENTIFIER [, '=', EXPR] ;

METHOD_DECL = TYPE, IDENTIFIER, '(', PARAM_DECL, ')' ;

PARAM_DECL = [SYMBOL_DECL [{, ',', SYMBOL_DECL}])];

TYPE = 'numeric' | 'string' | 'list' | 'map' | 'method' | 'any';

EXPR = ASSIGN_EXPR ;

ASSIGN_EXPR = LOGICAL_OR_EXPR, '=', LOGICAL_OR_EXPR ;

LOGICAL_OR_EXPR = LOGICAL_AND_EXPR [{, '||', LOGICAL_OR_EXPR}] ;

LOGICAL_AND_EXPR = EQUAL_EXPR [{, '&&', LOGICAL_AND_EXPR}] ;

EQUAL_EXPR = RELATIONAL_EXPR, '==', RELATIONAL_EXPR |
             RELATIONAL_EXPR, '!=', RELATIONAL_EXPR |
             RELATIONAL_EXPR, 'eq', RELATIONAL_EXPR |
             RELATIONAL_EXPR, 'ne', RELATIONAL_EXPR ;

RELATIONAL_EXPR = ADD_EXPR, '<', RELATIONAL_EXPR |
                  ADD_EXPR, '<=', RELATIONAL_EXPR |
                  ADD_EXPR, '>', RELATIONAL_EXPR |
                  ADD_EXPR, '>=', RELATIONAL_EXPR ;

ADD_EXPR = MULT_EXPR
            [{(, '+', MULT_EXPR) 
             |(, '-', MULT_EXPR)}] ;

MULT_EXPR = UNARY_EXPR
             [{(, '*', UNARY_EXPR)
              |(, '/', UNARY_EXPR)
              |(, '//', UNARY_EXPR)
              |(, '%', UNARY_EXPR)}] ;

UNARY_EXPR = [('-'|'!'), ] PRIMARY_EXPR ; 

PRIMARY_EXPR = '(', EXPR, ')' | DEREF_EXPR | ? string const ? | ? number const ? ;

DEREF_EXPR = ID_EXPR [{, ':', ID_EXPR}] [, '.' TYPE_METHOD_CALL] ;

ID_EXPR = IDENTIFIER [(, '[', EXPR, ']') | (, '(', ARG_LIST, ')')] ;

IDENTIFIER = [a-zA-Z]{[a-zA-Z0-9]} ;

TYPE_METHOD_CALL = IDENTIFIER, '(', [ARG_LIST], ')' ;

ARG_LIST = EXPR [, ',', EXPR] ;
```
