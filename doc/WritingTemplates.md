Writing Templates
=================

Introduction
------------
Here is a simple template for an html order page as an introduction to
the template language. As input it takes an Order containing a list of
order items and generates a html table with a row for every order item.

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
The first line is a comment and the following lines contain the code
for the html page with embedded code in the template language.

The Anatomy of a Template
-------------------------
A template can consist of TEXT, COMMENT and CODE blocks. Any simple
text file (containing only TEXT) is a valid template. If a template
contains only TEXT, the output of the generation is a copy of the
template.

### TEXT
Any text which is not inside a delimited COMMENT or CODE block.

### COMMENT
Any text between the delimiters `<?!--` and `--?>` is considered as a
comment. Any text inside a comment will not be transformed to the
output of the generation. You can use comments to comment out CODE
blocks.

### CODE
Any text between the delimiter `<?` and `?>` (like XML processing
instructions) is considered as template code. 
You can archive dynamic output with template code and variable input
data.

Template Code
-------------
Template code can contain expressions, statements (like if or while),
variable or method declarations and method calls. 

### Expressions

Every expression except an assignment expression results in the value
of the expression being written in the generated output. A `<?2 + 2?>`
results in a `4` in the output but for the assignment
`<?numeric i = 2 + 2?>` nothing is generated. 

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

### Data Types

In the template language constants, variables and expressions are
typed. The template language defines these data types:
  
#### `string`
The `string` data type represents character data a.k.a. strings.

#### `numeric`
The `numeric` data type represents the real numbers (integer or
floating point).

#### `list`
The `list` data type represents an ordered list of elements. The elements
can be of any data type.

#### `map`
The `map` data type is an associative key/value store. The keys must be
strings, but the values can be of any data type.

#### `method`
As a functional language, methods (functions) are first class citizens
in the template language and they have their own data type `method`.
Methods can be used as return values or parameters of other methods and
they can be assigned to variables.

#### `any`
Any can be used as the type of the return value of methods but not for
method parameters or variables.

#### Literals
Numeric literals can contain integer numbers like `-20`, `0` or `42` or
floating point numbers like `-2.5` or `6.9`
String literals are enclosed in apostrophs: `'Hello World!'`

#### Boolean Values
There is no boolean datatype defined in the template language.
Use the numeric literals `0` and `1` as *FALSE* and *TRUE*.
The string constant `false` and  and the numeric `0` are considered
*FALSE* in boolean expressions. Empty lists and maps are also considered
*FALSE* in boolean expressions. Any other value is considered *TRUE*.

### Variables
You can declare and use variables in the template code. Variables have
to be declared before use.

Examples:
```
numeric x
numeric y = 3.0

string resultstring
string name = 'Joe Doe'

list resultList
map resultMap
```

Another way to provide the template with variables is by providing
external data with data binding.
See [Data Binding] (UsingTemplateEngine.md "UsingTemplateEngine")

### Operators

#### Arithmetic Operators Defined for `numeric` Data Types
 
* `+` Addition.
* `-` Substraction.
* `*` Multiplication.
* `/` Division.
* `//` Integer division.
* `%` Modulo.

#### Relational Operators Defined for `numeric` Data Types

* `<` Less.
* `<=` Less or equal.
* `>` Greater.
* `>=` Greater or equal.
* `==` Equal.
* `!=` Not Equal.

#### Relational Operators Defined for `string` Data Types

* `lt` Lexical less.
* `le` Lexical less or equal.
* `gt` Lexical greater.
* `ge` Lexical greater or equal.
* `eq` Equal.
* `ne` Not equal.

#### Logical Operators

* `&&` Logical and.
* `||` Logical or.
* `!` Logical not.

#### Other Operators

* `=` Assignment to a variable.
* `+` Concatenation of strings and lists.
* `:` Dereferencing of maps.

### Operator Precedence

Accessing Elements of Lists and Maps
------------------------------------
You can access elements of lists or maps with index or key lookups.
An element of a list is looked up with an index number in square
brackets. The index is 0 based to be consistent with other programming
languages. The first element has the index 0 and the last element has
the index list.size() - 1

Example:
```
result = someList[0]
```
The result is set to the first element of the list someList.

Example:
```
result = someMap['Name']
```
The result is set to the value of the Name entry of someMap.

This Example is equivalent to the last example:
```
result = someMap:Name
```

The dereference operators and index access can be chained, too:
```
User:Orders[0]:OrderItems[0]:Name
```
But if the list of Orders or OrderItems is empty, you will get an error while generating.


### Statements and Statement Blocks

Blocks of statements are delimited with `{` and `}`.

#### Variable Scopes

TODO Difference between dynamic and lexical Scoping

#### `if` Statement ####
The `if` statement is used for conditional evaluation and branching.

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
evaluated repeatedly as long as the expression evaluates to TRUE.

```
while(EXPR) BLOCK_STMT
```

Example:
```
numeric i = 1
while(i < 10) {
	i + ''
	i = i + 1
}
```
> 1 2 3 4 5 6 7 8 9 

#### `foreach` Statement ####
The `foreach` statement is used to loop over a `list` expression.
The block of code is evaluated for every element of the list.
For every iteration the current element is assigned to the specified
variable.

An optional filter expression can be defined with the `|` operator.
List elements for which the evaluation of the filter expression returns
FALSE are skipped.

```
foreach VAR [| FILTER_EXPR] <- LIST_VAR BLOCK_STMT
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

### Methods
There are 2 different kinds of methods in the template language. The
first kind of methods are called Template Methods which can be declared
and implemented in the template code. The second type of methods are
methods defined on the data types of the template language.

#### Template Methods
You can declare methods in the template code. A method declaration has a
return type, a name, a list of parameter declarations and a block of
code with the implementation of the method. 

The return value of the method is the value of last expression of the
method.

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

#### Methods on Data Types
The methods defined on the data types of the template language are
written in Java. The template engine provides the following methods.

##### split
Signature: `list <string>.split(string pattern)`
* Splits a string into a list using the pattern as delimiter.

##### matches
Signature: `numeric <string>.matches(string pattern)`
* Matches the string against the regular expression pattern. Returns
  *TRUE* if there's a match, otherwise *FALSE*.

##### startsWith
Signature: `numeric <string>.startsWith(string start)`
* Returns *TRUE* if the string starts with start.

##### endsWith
Signature: `numeric <string>.endsWith(string end)`
* Returns *TRUE* if the string ends with end.

##### toLower
Signature: `string <string>.toLower()`
* Converts the whole string to lower case characters.

##### toUpper
Signature: `string <string>.toUpper()`
* Converts the whole string to upper case characters.

##### firstLower
Signature: `string <string>.firstLower()`
* Converts the first character of the string to lower case.

##### firstUpper
Signature: `string <string>.firstUpper()`
* Converts the first character of the string to upper case.

##### replace
Signature: `string <string>.replace(string substring, string replacement)`
* Replaces every occurrence of the substring in the string with the
  replacement.

##### camelCaseToUnderScore
Signature: `string <string>.camelCaseToUnderScore()`
* Converts the in word occurrences of an upper case characters to a
  combination of an underscore and the lower case character.
  E.g. `ThisSampleText` is converted to `This_sample_text`

##### size
Signatures: `numeric <string>.size()`, `numeric <list>.size()`and `numeric <map>.size()`
* Returns the size of the string, list or map value. For strings the
  size is the length of the string. For lists and maps, the size is
  the number of elements.

##### add
Signatures `list <list>.add(any element)`
* Appends the element to the list.

##### hasNext
Signature: `numeric <list>.hasNext()`
* Inside a foreach loop you can check if there are more elements in
  the list after the current entry.

##### put
Signature: `map <map>.put(string key, <any> element)`
* Adds an element to the map under the given key.

##### keyList
Signature: `list <map>.keyList()`
* Returns a list of all the keys contained in the map.
Example:
```
string csvLine = '1;Ludger;Solbach;VALUABLE_CUSTOMER'
list values = csvLine.split(';')
```

[Table of Content] (TemplateEngine.md "Table of Content")
