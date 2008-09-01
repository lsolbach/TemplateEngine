/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.ValueType;

public class AssignNode extends AbstractAstNode {
  
  /**
   * 
   */
  public AssignNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public AssignNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ASSIGN);
  }


	public IValue generateSymbol() {
    IAstNode child = null;
    IValue symbol = null;
    String name = "";

    if((child = getChild(0)) != null && child instanceof IdentifierNode) {
      name = child.getData();
      symbol = lookupSymbol(name);
    } else {
      throw new GenerateException("Expecting Identifier");
    }
    
    if(symbol == null) {
      throw new GenerateException("Symbol not found: " + name);
    }
    
    if((child = getChild(1)) != null) {
      if (symbol.getType().equals(ValueType.STRING)) {
      	// assign to string symbol
      	IValue rSymbol = child.generateSymbol();
        ((IStringValue) symbol).setData(((IStringValue) rSymbol).getData());
      } else if (symbol.getType().equals(ValueType.NUMERIC)) {
      	// assign to numeric symbol
      	IValue rSymbol = child.generateSymbol();
        ((INumericValue) symbol).setData(((INumericValue) rSymbol).getData());
      } else if (symbol.getType().equals(ValueType.LIST)) {
      	// assign to list symbol
      	IValue aSymbol = child.generateSymbol();
        if (!(aSymbol instanceof IListValue)) {
          throw new GenerateException("Symbol not of type list "
              + child.getData());
        }
        ((IListValue) symbol).setData(((IListValue) aSymbol).getData());
      } else if (symbol.getType().equals(ValueType.MAP)) {
      	// assign to map symbol
      	IValue aSymbol = child.generateSymbol();
        if (!(aSymbol instanceof IMapValue)) {
          throw new GenerateException("Symbol not of type map: "
              + child.getData());
        }
        ((IMapValue) symbol).setData(((IMapValue) aSymbol).getData());
      }
    } else {
      throw new GenerateException("Expecting something to assign");
    }    
    		
		return new StringValue("");
	}
  
}
