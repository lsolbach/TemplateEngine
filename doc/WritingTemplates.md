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

TEXT
CODE
COMMENT

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

### Datatypes ###

The template language is a functional language.

* string
* numeric
* list
* map
* method


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

#### Numerical arithmetic operaters #### 
* `+`
* `-`
* `*`
* `/`
* `//`
* `%`

#### Numeric Relational Operators ####

* `<`
* `<=`
* `>`
* `>=`
* `==`
* `!=`

#### String Relational Operators ####

* `lt`
* `le`
* `gt`
* `ge`
* `eq`
* `ne`

#### Logical Operators ####

* `&&`
* `||`
* `!`

#### Operators on other Datatypes ####

* `+`
* `:`

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


#### While Statement #####

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


#### Foreach Statement ####

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

#### Methods on Datatypes ####

Example:
```
list values = csvLine.split(';')
```

string split(string pattern)

numeric matches(string pattern)

numeric startsWith(string start)

numeric endsWith(string end)

string toLower()

string toUpper()

string firstLower()

string firstUpper()

string replace(string substring, string replacement)

string camelCaseToUnderScore()

numeric size()

list add(any element)

numeric hasNext()

map put(string key, <any> element)

list keyList()


#### Template Methods ####

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

