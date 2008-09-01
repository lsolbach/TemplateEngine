/*
 * Created on Jul 24, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;

public class DereferenceNode extends AbstractAstNode {

  public DereferenceNode() {
    this(null);
  }

  public DereferenceNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.DEREF_EXPR);
  }

	public IValue generateSymbol() {
    Iterator<IAstNode> it = getChildNodes().iterator();
    IAstNode node = null;
    IValue symbol = null;
    while(it.hasNext()) {
    	node = it.next();
    	if(node instanceof IdentifierNode) { 
	    	// dereference all identifier nodes
    		IdentifierNode idNode = (IdentifierNode) node;
	      if(symbol == null) {
	        // lookup symbol in symbol tables
	        symbol = idNode.lookupSymbol(idNode.getData());
	      } else {
	        // dereference
	        symbol = idNode.derefSymbol(symbol, idNode.getData());
	      }      
	      // array or map lookup
	      symbol = idNode.lookup(symbol);
    	}
    }
    return symbol;
	}
}
