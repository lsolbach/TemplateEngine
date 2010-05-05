/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.SymbolTable;
import org.soulspace.template.value.impl.ValueType;

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
		// normal term generation produces a string for template output
		return generateSymbol(ValueType.STRING);
	}

	public IValue generateSymbol(ValueType returnType) {
		IValue result = null;
		setSymbolTable(new SymbolTable());
		StringBuffer sb = new StringBuffer(128);
		Iterator<IAstNode> it = getChildNodes().iterator();
		
		while (it.hasNext()) {
			IAstNode child = it.next();
			try {
				result = child.generateSymbol();
				if (result != null) {
					if(returnType.equals(ValueType.STRING)
							&& (result.getType().equals(ValueType.STRING)
									|| result.getType().equals(ValueType.NUMERIC))) {
						// only evaluate if we want a string result
						// and don't evaluate lists and maps
						sb.append(result.evaluate());
					}
				}
			} catch (GenerateException e) {
				System.out.println(sb.toString());
				throw e;
			}
		}
		if(returnType.equals(ValueType.STRING)) {
			return new StringValue(sb.toString());
		} else if(returnType.equals(ValueType.NUMERIC)) {
			return result;			
		} else if(returnType.equals(ValueType.LIST)) {
			return result;
		} else if(returnType.equals(ValueType.MAP)) {
			return result;
		}
		
		return new StringValue(sb.toString());
	}
}
