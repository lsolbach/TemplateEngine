Writing Functional Templates
============================

Lispy Lists
-----------

```
/* returns a list with every element of the element list but the first */
list tail(list elementList) {
	list resultList
	if(elementList) {
		numeric s = elementList.size()
		numeric i = 1
		while(i < s) {
			resultList.add(elementList[i])
			i = i + 1
		}
	}
	resultList
}
```

Higher Order Methods
--------------------

```
/* returns a list with the method m applied to every element of the elementList.*/
/* implementation of the 'map' higher order function */
list mapM(method m, list elementList) {
	list resultList
	foreach element <- elementList {
		resultList.add(m(element))
	}
	resultList
}

/* implementation of the 'fold/reduce' higher order function for numeric elements*/
numeric fold(method m, list elementList, numeric initial) {
	if(elementList) {
		m(first(elementList), fold(m, tail(elementList), initial))
	} else {
		initial
	}
}

/* implementation of the 'fold/reduce' higher order function for string elements*/
string fold(method m, list elementList, string initial) {
	if(elementList) {
		m(first(elementList), fold(m, tail(elementList), initial))
	} else {
		initial
	}
}

/* implementation of the 'fold/reduce left' higher order function for string elements*/
numeric foldLeft(method m, list elementList, numeric initial) {
	numeric result = initial
	foreach e <- elementList {
		result = m(result, e)
	}
	result
}

/* implementation of the 'fold/reduce left' higher order function for string elements*/
string foldLeft(method m, list elementList, string initial) {
	string result = initial
	foreach e <- elementList {
		result = m(result, e)
	}
	result
}
```

Closures
--------

```
/* returns a string with the first 2 arguments joined together separated by the separator */
string join(string arg1, string arg2, string separator) {
	string result
	if(arg1 ne '' && arg2 ne '') {
		result = arg1 + separator + arg2
	} else if(arg1 ne '') {
		result = arg1
	} else {
		result = arg2
	}
	result
}

/* returns a method that takes 2 strings and joins them together separated by the separator */
method cJoin(string separator) {
	/* closure */
	string s = separator
	method resultMethod = string fn(string a, string b) {
		join(a, b, s)
	}
	resultMethod
}

/* returns a string with all elements joined together separated by the separator */
string join(list elementList, string separator) {
	foldLeft(cJoin(separator), elementList, '')
}
```

[Table of Content] (TemplateEngine.md "Table of Content")
