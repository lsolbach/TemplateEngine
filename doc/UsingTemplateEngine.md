Using the TemplateEngine
========================

The TemplateEngine Interface
----------------------------

*TODO check in code*
```
public interface TemplateEngine {
  void loadTemplate(String template);
  void loadTemplate(File templateFile);
  void loadTemplates(String[] templates);
  void loadTemplates(File[] templateFiles);

  String generate();
  String generate(ISymbolTable symbolTable);
  String generate(IDataSource dataSource);
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

Loading templates
-----------------


Generating output
-----------------


Data Binding of Java Beans
--------------------------


Data Binding of XML Data
------------------------



