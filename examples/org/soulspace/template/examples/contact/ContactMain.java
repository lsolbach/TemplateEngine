/*
 * Created on Aug 14, 2005
 */
package org.soulspace.template.examples.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.impl.BeanDataSourceImpl;
import org.soulspace.template.impl.TemplateEngineImpl;

/**
 * Simple example for the use of the soulspace template engine.
 * @author soulman
 *
 */
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

		try {
			// create template engine
			TemplateEngine te = new TemplateEngineImpl();

			// load template
			String template = loadStringFromStream(ContactMain.class
					.getResourceAsStream("contact.tmpl"));
			te.loadTemplate(template);

			// setup data sources
			BeanDataSourceImpl ds1 = new BeanDataSourceImpl(c1); // contact 1
			BeanDataSourceImpl ds2 = new BeanDataSourceImpl(c2); // contact 2

			// generate with first contact
			System.out.print(te.generate(ds1));
			System.out.println("----8<----\n");

			// generate with second contact
			System.out.print(te.generate(ds2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reads a stream as string.
	 * @param is input stream
	 * @return string
	 * @throws IOException
	 */
	public static String loadStringFromStream(InputStream is)
			throws IOException {
		StringBuilder buffer = new StringBuilder(128);
		String line = null;

		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		while ((line = in.readLine()) != null) {
			buffer.append(line + "\n");
		}
		in.close();
		return buffer.toString();

	}
}
