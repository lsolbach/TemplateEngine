package org.soulspace.template.examples.helloworld;

import org.soulspace.template.examples.TemplateHelper;

public class HelloWorldTemplate {

  public static void main(String[] args) {
    try {
      System.out.println(TemplateHelper.generate("Hello World!"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
