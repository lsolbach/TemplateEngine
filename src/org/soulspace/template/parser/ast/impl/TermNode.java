/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.SymbolTable;

public class TermNode extends AbstractAstNode {

  /**
   * 
   */
  public TermNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public TermNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.TERM);
  }

	public IValue generateSymbol() {
    setSymbolTable(new SymbolTable());
    StringBuffer sb = new StringBuffer(128);
    Iterator<IAstNode> it = getChildNodes().iterator();
    
    while(it.hasNext()) {
      IAstNode child = it.next();
      try {
    		IValue symbol = child.generateSymbol();
    		if(symbol != null) {
      		sb.append(symbol.evaluate());      			
    		}
      } catch (GenerateException e) {
        System.out.println(sb.toString());
        throw e;
      }
    }
    return new StringValue(sb.toString());
	}

}
