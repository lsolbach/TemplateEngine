/*
 * Created on Apr 24, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class XmlDeclNodeImpl extends AbstractAstNode implements AstNode {

  public XmlDeclNodeImpl() {
    this(null);
  }

  public XmlDeclNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.XML_DECL);
  }

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		return new StringValueImpl(getData());
	}

}
