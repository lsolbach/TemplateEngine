Writing Templates
=================

Introduction
------------


Template example:
```
<?!-- Comment: Template Example --?>
<html>
  <body>
  <h1>Order <?Order:Id?></h1>
  <table>
    <tr>
      <th>Item No.</th><th>Quantity</th><th>Name</th><th>Price</th><th>Sum</th>
    </tr>
<?foreach item <- Order:OrderItems {?>
    <tr>
      <td><?item:Id?></td>
      <td><?item:Quantity?></td>
      <td><?item:Name?></td>
      <td><?item:Price?></td>
      <td><?item:Sum?></td>
    </tr>
<?}?>
    </table>
  </body>
</html>
```

Anatomy of a Template
---------------------

* TEXT
* CODE
* COMMENT

Template Code
-------------


### Expressions ###

Examples:
```
2 + 2
i = 2 + 2
3 * i

'Hallo'
name = 'Joe Doe'
name = FirstName + ' ' + LastName
name
```

#### Constants ####

#### Boolean Values ####

### Data Types ###

In the template language constants, variables and expressions are typed. The template language defines these data types:
  
#### `string`
The string data type

#### `numeric`
The `numeric` data type represents the real numbers (integer or floating point).

#### `list`
The list data type represents an ordered list of elements. The elements can be of any data type.

#### `map`
The `map` data type is an associative key/value store. The keys must be strings, but the values can be of any data type.

#### `method`
As a functional language, methods (functions) are first class citizens in the template language and they have their own data type `method`.
Methods can be used as return values or parameters of other methods and they can be assigned to variables.

#### `any`

### Variables ###

Examples:
```
numeric x
numeric y = 3.0

string resultstring
string name = 'Joe Doe'

list resultList
map resultMap
```

Another way to provide the template with variables is the data binding of Java Beans.
See [Data Binding of JavaBeans] (UsingTemplateEngine.md "UsingTemplateEngine")

### Operators ###

#### Arithmetic Operators Defined for `numeric` Data Types ####
 
* `+` Addition
* `-` Substraction
* `*` Multiplication
* `/` Division
* `//` Integer division
* `%` Modulo

#### Relational Operators Defined for `numeric` Data Types ####

* `<` Less
* `<=` Less or equal
* `>` Greater
* `>=` Greater or equal
* `==` Equal
* `!=` Not Equal

#### Relational Operators Defined for `string` Data Types ####

* `lt` Lexical less
* `le` Lexical less or equal
* `gt` Lexical greater
* `ge` Lexical greater or equal
* `eq` Equal
* `ne` Not equal

#### Logical Operators ####

* `&&` Logical and
* `||` Logical or
* `!` Logical not

#### Other Operators ####

* `=` Assignment
* `+` Concatenation of strings and lists
* `:` Dereferencing of maps

Dereferencing Lists and Maps
----------------------------


Example:
```
result = resultList[0]
```

Example:
```
rc = ResultMap['Code']
```

Example:
```
rc = resultMap:Code
```


### Statements and Statement Blocks ###

Blocks of statements are delimited with `{` and `}`.

#### If Statement ####

```
if(EXPR) BLOCK_STMT [ else BLOCK_STMT]
```

Example:
```
if(i < 0) {
  printNegative(i)
} else if(i > 0) {
  printPositive(i)
} else {
  printNull(0)
}
```

#### `while` Statement #####
The `while` statement is a generic loop statement. The block of code is
evaluated repeatedly as long as the expression evaluates to `true`.

```
while(EXPR) BLOCK_STMT
```

Example:
```
numeric i = 1
while(i < 10) {
  print(i)
  i = i + 1
}
```


#### `foreach` Statement ####
The `foreach` statement is used to loop over a `list` expression.
The block of code is evaluated for every element of the list.
For every iteration the current element is assigned to the specified
variable.

An optional filter expression can be defined with the `|` operator.
List elements for which the evaluation of the filter expression returns
false are skipped.

```
foreach VAR [ | FILTER_EXPR] <- LIST_VAR BLOCK_STMT
```

Example 1:
```
foreach item <- orderItems {
  printLine(item)
}
```

Example 2:
```
foreach item | (item:Count > 10) <- orderItems {
  calculateDiscount(item)
}
```

### Methods ###


#### Template Methods ####
You can declare methods in the template code. A method declaration has a
return type, a name, a list of parameter declarations and a block of
code. 

```
numeric increment(numeric value) {
  value + 1
}

increment(2)
```
> 3

```
string countToZero(numeric value) {
  string result
  if(value > 0) {
    result = value + ', ' + countToZero(value - 1)
  } else if(value == 0) {
    result = 0
  }
  result
}

countToZero(5) 
```
> 5, 4, 3, 2 ,1, 0

Methods are declared in the order in which they are parsed. If you
declare the same method (with the same name and parameter types) more
than once, the previous method gets overridden. You can access the
previous version with a call to `super()` inside the methods code
block.

#### Methods on Datatypes ####

Example:
```
list values = csvLine.split(';')
```

##### split
 `string split(string pattern)`

##### matches
 `numeric matches(string pattern)`

##### startsWith
 `numeric startsWith(string start)`

##### endsWith
 `numeric endsWith(string end)`

##### toLower
 `string toLower()`

##### toUpper
 `string toUpper()`

##### firstLower
 `string firstLower()`

##### firstUpper
 `string firstUpper()`

##### replace
`string replace(string substring, string replacement)`

##### camelCaseToUnderScore
`string camelCaseToUnderScore()`

##### size
`numeric size()`

##### add
`list add(any element)`

##### hasNext
`numeric hasNext()`

##### put
`map put(string key, <any> element)`

##### keyList
`list keyList()`


[Table of Content] (TemplateEngine.md "Table of Content")
