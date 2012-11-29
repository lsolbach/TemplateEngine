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
  
  String generate(SymbolTable symbolTable) throws SyntaxException, GenerateException;
  String generate(DataSource dataSource) throws SyntaxException, GenerateException;
}
```

Creating an instance
--------------------

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


Generating output
-----------------


Data Binding of Java Beans
--------------------------


Data Binding of XML Data
------------------------



