/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;

public class AstGeneratorImpl {

	public AstGeneratorImpl() {
		super();
	}

	public String generate(Environment environment, AstNode root) throws GenerateException {
		String result = root.generateValue(environment).evaluate();
		return result;
	}

}
