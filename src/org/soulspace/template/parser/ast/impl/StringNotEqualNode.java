/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class StringNotEqualNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public StringNotEqualNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringNotEqualNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_NOT_EQUAL);
  }

	public ISymbol generateSymbol() {
		StringSymbol s0 = asString(getChild(0).generateSymbol());
		StringSymbol s1 = asString(getChild(1).generateSymbol());
		if(s0 == null && s1 == null) {
	    return new NumericSymbol(0);						
		} else if(s0 == null && s1 != null) {
	    return new NumericSymbol(1);									
		} else {
	    return new NumericSymbol((!s0.equals(s1)) ? 1 : 0);			
		}
	}

}
