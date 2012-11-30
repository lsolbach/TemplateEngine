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
initialize it with a template.


Generating output
-----------------
The generation of output is started by calling one of the 'generate'
methods. The result of the generation is a 'String'.

Data Binding
------------
You can provide the template engine with external data for each call
to a 'generate' method.

Data Binding of Java Beans
--------------------------

```
  TemplateEngine te; // injected
  Order order;       // injected
  File orderTemplate = new File("OrderHtml.tmpl");

  te.loadTemplate(orderTemplate);

  DataSource orderDS = new BeanDataSourceImpl();
  orderDS.add("Order", order);
  String generated = te.generate(orderDS);

  System.out.print(generated);
```


Data Binding of XML Data
------------------------

