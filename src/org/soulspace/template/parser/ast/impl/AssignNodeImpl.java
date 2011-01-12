/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.MethodValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class AssignNodeImpl extends AbstractAstNode {

	/**
	 * 
	 */
	public AssignNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public AssignNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.ASSIGN);
	}

	public Value generateValue() {
		AstNode child = null;
		Value symbol = null;
		String name = "";

		if ((child = getChild(0)) != null
				&& child instanceof IdentifierNodeImpl) {
			name = child.getData();
			symbol = lookupSymbol(name);
		} else {
			throw new GenerateException("Expecting Identifier! Template "
					+ getTemplate() + ", line " + getLine());
		}

		if (symbol == null) {
			throw new GenerateException("Symbol not found: " + name
					+ "! Template " + getTemplate() + ", line " + getLine());
		}
		try {
			if ((child = getChild(1)) != null) {
				if (symbol.getType().equals(ValueType.STRING)) {
					// assign to string symbol
					Value rSymbol = child.generateValue();
					((StringValue) symbol).setData(((StringValue) rSymbol)
							.getData());
				} else if (symbol.getType().equals(ValueType.NUMERIC)) {
					// assign to numeric symbol
					Value rSymbol = child.generateValue();
					((NumericValue) symbol).setData(((NumericValue) rSymbol)
							.getData());
				} else if (symbol.getType().equals(ValueType.LIST)) {
					// assign to list symbol
					Value aSymbol = child.generateValue();
					if (!(aSymbol instanceof ListValue)) {
						throw new GenerateException("Symbol not of type list "
								+ child.getData() + "! Template "
								+ getTemplate() + ", line " + getLine());
					}
					((ListValue) symbol).setData(((ListValue) aSymbol)
							.getData());
				} else if (symbol.getType().equals(ValueType.MAP)) {
					// assign to map symbol
					Value aSymbol = child.generateValue();
					if (!(aSymbol instanceof MapValue)) {
						throw new GenerateException("Symbol not of type map: "
								+ child.getData() + "! Template "
								+ getTemplate() + ", line " + getLine());
					}
					((MapValue) symbol).setData(((MapValue) aSymbol).getData());
				} else if (symbol.getType().equals(ValueType.METHOD)) {
					if (child.getType().equals(AstNodeType.METHOD)) {
						MethodNode mNode = (MethodNode) child;
						((MethodValueImpl) symbol).setData(mNode.getData());
						// TODO check if adding assigned methods to the global
						// method registry is desirable
						mNode.addMethodNode(mNode);
					} else if (child.getType().equals(AstNodeType.IDENTIFIER)) {
						IdentifierNodeImpl iNode = (IdentifierNodeImpl) child;
						((MethodValueImpl) symbol).setData(iNode.getData());
					} else {
						throw new GenerateException(
								"Symbol not of type method: " + child.getData()
										+ "! Template " + getTemplate()
										+ ", line " + getLine());
					}
				}
			} else {
				throw new GenerateException(
						"Expecting something to assign! Template "
								+ getTemplate() + ", line " + getLine());
			}
		} catch (ClassCastException e) {
			throw new GenerateException("ClassCastException in template "
					+ getTemplate() + ", line " + getLine(), e);
		}
		return new StringValueImpl("");
	}

}
