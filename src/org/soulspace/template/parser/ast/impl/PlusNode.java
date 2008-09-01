/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;
import org.soulspace.template.value.impl.MapValue;
import org.soulspace.template.value.impl.NumericValue;
import org.soulspace.template.value.impl.StringValue;

public class PlusNode extends AbstractAstNode {

  /**
   * 
   */
  public PlusNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public PlusNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.PLUS);
  }

	public IValue generateSymbol() {
		IValue symbol = getChild(0).generateSymbol();
		if(symbol instanceof IStringValue) {
			StringValue string = new StringValue("");
	    Iterator<IAstNode> it = getChildNodes().iterator();
	    while(it.hasNext()) {
	    	IStringValue next = asString(it.next().generateSymbol());
	    	string = string.add(next);
	    }
	    return string;
		} else if(symbol instanceof INumericValue) {
			NumericValue numeric = new NumericValue(0.0);
	    Iterator<IAstNode> it = getChildNodes().iterator();
	    while(it.hasNext()) {
	    	numeric = numeric.add(asNumeric(it.next().generateSymbol()));
	    }
			return numeric;
		} else if(symbol instanceof IListValue) {
			ListValue list = new ListValue();
	    Iterator<IAstNode> it = getChildNodes().iterator();
	    while(it.hasNext()) {
	    	IValue s = it.next().generateSymbol();
	    	if(s instanceof IListValue) {
		    	list.getData().addAll(((IListValue) s).getData());	    		
	    	}
	    }			
			return list;
		} else if(symbol instanceof IMapValue) {
			MapValue map = new MapValue();
	    Iterator<IAstNode> it = getChildNodes().iterator();
	    while(it.hasNext()) {
	    	IValue s = it.next().generateSymbol();
	    	if(s instanceof IMapValue) {
		    	map.getData().addSymbolTable(((IMapValue) s).getData());	    		
	    	}
	    }
			return map;
		} else {
			throw new GenerateException("Unknown type!");
		}
	}
}