Using the TemplateEngine
========================

The TemplateEngine Interface
----------------------------

```
public interface TemplateEngine {
  void loadTemplate(String template) throws UnknownTokenException, SyntaxException;
  void loadTemplate(File templateFile) throws UnknownTokenException, SyntaxException, IOException;
  void loadTemplates(String[] templates) throws UnknownTokenException, SyntaxException;  
  void loadTemplates(File[] templateFiles) throws UnknownTokenException, SyntaxException, IOException;

  String generate() throws SyntaxException, GenerateException;
  String generate(SymbolTable symbolTable) throws SyntaxException, GenerateException;
  String generate(DataSource dataSource) throws SyntaxException, GenerateException;
}
```

Creating an instance
--------------------
An instance of the template engine can be created by calling the
constructor of the `TemplateEngineImpl` class.

Code example:
```
  TemplateEngine te = new TemplateEngineImpl();
  String template = "Hello World!"
  te.loadTemplate(template);
  System.out.println(te.generate());
```
> Hello World!

Loading templates
-----------------
Before you can generate output with the template engine you have to
initialize it with a template via the `loadTemplate()` or
`loadTemplates()` methods. You can pass a template as a `String` or
as a `File` object containing the template.

If the `loadTemplates()` methods are used all the templates are loaded
in the order of the given array. All the elements of the array form one
single template in the template engine so this can be used as a
template include or composition mechanism.

Generating output
-----------------
The generation of output is started by calling one of the 'generate()'
methods. The result of the generation is a `String` containing the
generated output. For the generation to be useful, the template engine
has to be provided with some external data.

Data Binding
------------
You can provide the template engine with external data for each call
to a `generate()` method.

### Data Binding of Java Beans ###

The `BeanDataSourceImpl` class can be used to bind Java Beans as
external data. This data source converts a bean to a map with an
entry for every property of the bean.

```
  TemplateEngine te; // injected
  Order order;       // injected
  File orderTemplate = new File("OrderHtml.tmpl");

  te.loadTemplate(orderTemplate);

  // create an empty bean data source
  DataSource orderDS = new BeanDataSourceImpl();

  // add order bean under key "Order"
  orderDS.add("Order", order);

  // generate output with the orderDS a
  String generated = te.generate(orderDS); 

  System.out.print(generated);
```

### Data Binding of XML Data ###

With the `XmlDataSourceImpl` class you can bind any XML data as external data for the generation.

```
TODO example code
```

[Table of Content] (TemplateEngine.md "Table of Content")
