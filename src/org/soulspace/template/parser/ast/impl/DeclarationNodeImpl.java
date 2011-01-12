/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;
import org.soulspace.template.value.impl.SymbolTableImpl;

public class DeclarationNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public DeclarationNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public DeclarationNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.DECLARATION);
	}

	public Value generateValue() {
		AstNode child = null;
		Value symbol = null;
		String name = "";
		String type = getData();

		if ((child = getChild(0)) == null) {
			throw new GenerateException(
					"No identifier given for declaration! Template "
							+ getTemplate() + ", line " + getLine());
		}
		if (child instanceof IdentifierNodeImpl) {
			name = child.getData();
			symbol = lookupSymbolInBlock(name);
		} else if (child instanceof AssignNodeImpl) {
			name = child.getChild(0).getData();
			symbol = lookupSymbolInBlock(name);
		}
		if (symbol != null) {
			throw new GenerateException("Symbol already exists: " + name
					+ "! Template " + getTemplate() + ", line " + getLine());
		}

		if (type.equals("string")) {
			getSymbolTable().addStringValue(name, "");
		} else if (type.equals("numeric")) {
			getSymbolTable().addNumericValue(name, "0");
		} else if (type.equals("list")) {
			getSymbolTable().addListValue(name, new ArrayList<Value>());
		} else if (type.equals("map")) {
			getSymbolTable().addMapValue(name, new SymbolTableImpl());
		} else if(type.equals("method")) {
			getSymbolTable().addMethodValue(name, null);
		}
		if (child instanceof AssignNodeImpl) {
			// evaluate assign expression
			child.generateValue();
		}

		// TODO is returning of the created symbol useful?
		// return symbol = lookupSymbolInBlock(name);
		return new StringValueImpl("");
	}

}
