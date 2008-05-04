/*
 * Created on Aug 14, 2005
 */
package org.soulspace.template.examples.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.impl.BeanDataSource;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.tokenizer.UnknownTokenException;

public class ContactMain {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Contact c1 = new Contact("Duck", "Donald");
    Contact c2 = new Contact("Mouse", "Mickey");

    Address a1 = new Address("Duck Street", "17", "12345", "Ducktown");
    Address a2 = new Address("Honeymoon Lane", "2", "67890", "Mouse City");
    Address a3 = new Address("In the Ghetto", "5", "24680", "Dogington");
    Address a4 = new Address("Santa's Road", "8", "00000", "Heaven");
    
    c1.addAddress(a1);
    c1.addAddress(a2);
    c1.addAddress(a3);
    c2.addAddress(a3);
    c2.addAddress(a4);

    String template;
		try {
			// create template engine
	    TemplateEngine te = new TemplateEngineImpl();

			// load template
			template = loadStringFromStream(ContactMain.class.getResourceAsStream("contact.tmpl"));
	    te.loadTemplate(template);
	    
	    // setup data sources
	    BeanDataSource ds1 = new BeanDataSource(c1);
	    BeanDataSource ds2 = new BeanDataSource(c2);

	    // generate with first contact
	    System.out.println(te.generate(ds1));

	    System.out.println("\n>>> 8< <<<\n");

	    // generate with second contact
	    System.out.println(te.generate(ds2));
		} catch (Exception e) {
			e.printStackTrace();
		}
    
  }

	public static String loadStringFromStream(InputStream is) throws IOException {
		StringBuilder buffer = new StringBuilder(128);
    String line = null;

    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    while((line = in.readLine()) != null) {
      buffer.append(line + "\n");
    }
		in.close();
		return buffer.toString();	

	}

}
