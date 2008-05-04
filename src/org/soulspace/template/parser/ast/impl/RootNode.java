/*
 * Created on Apr 28, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class RootNode extends AstNode {

  public RootNode() {
    this(null);
  }

  public RootNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ROOT);
    initMethodTable();
  }

	public ISymbol generateSymbol() {
		if(getChild(0) != null) {
			return getChild(0).generateSymbol();
		}		
		return new StringSymbol("");
	}
}
