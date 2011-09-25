/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.MethodValue;
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

	public Value generateValue(Environment environment) {
		// FIXME split in smaller methods, this gets to complex
		setEnvironment(environment);
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
				if(child.getType().equals(AstNodeType.METHOD)) {
					MethodNode mNode = (MethodNode) child;
					MethodValue lValue = (MethodValue) symbol;
					// TODO check if adding assigned methods to the global
					// method registry is desirable
					if(!mNode.getData().equals("fn")) {
						lValue.setData(mNode.getData());
//							lValue.setMethodNode(mNode);
						lValue.setEnvironment(lValue.getEnvironment());
						mNode.addMethodNode(mNode);
//							System.out.println("Named method assignment");
//							System.out.println(getEnvironment().printEnvironment());
					} else {
						lValue.setMethodNode(mNode);
						lValue.setEnvironment(lValue.getEnvironment());
//							System.out.println("Anonymous method assignment");
//							System.out.println(getEnvironment().printEnvironment());
					}					
				} else {
					Value rValue = child.generateValue(getEnvironment());
					if (rValue == null) {
						throw new GenerateException("The value of symbol "+ child.getData()
								+ " is null! Template "
								+ getTemplate() + ", line " + getLine());
					}
					if (symbol.getType().equals(ValueType.STRING)) {
						// assign to string symbol
						((StringValue) symbol).setData(((StringValue) rValue)
								.getData());
					} else if (symbol.getType().equals(ValueType.NUMERIC)) {
						// assign to numeric symbol
						((NumericValue) symbol).setData(((NumericValue) rValue)
								.getData());
					} else if (symbol.getType().equals(ValueType.LIST)) {
						// assign to list symbol
						if (!(rValue instanceof ListValue)) {
							throw new GenerateException("The value of symbol "+ child.getData()
									+ " is not of type list but of type "
									+ rValue.getType() + "! Template "
									+ getTemplate() + ", line " + getLine());
						}
						((ListValue) symbol).setData(((ListValue) rValue)
								.getData());
					} else if (symbol.getType().equals(ValueType.MAP)) {
						// assign to map symbol
						if (!(rValue instanceof MapValue)) {
							throw new GenerateException("The value of symbol "+ child.getData()
									+ " is not of type map but of type "
									+ rValue.getType() + "! Template "
									+ getTemplate() + ", line " + getLine());
						}
						((MapValue) symbol).setData(((MapValue) rValue).getData());
					} else if (symbol.getType().equals(ValueType.METHOD)) {
						if(child.getType().equals(AstNodeType.METHOD_CALL)) {
							MethodValue lValue = (MethodValue) symbol;
							MethodValue mValue = (MethodValue) rValue;
							// copy the node and the enviroment to the new value
							lValue.setMethodNode(mValue.getMethodNode());
							lValue.setEnvironment(mValue.getEnvironment());
						} else if (child.getType().equals(AstNodeType.IDENTIFIER)) {
							IdentifierNodeImpl iNode = (IdentifierNodeImpl) child;
							((MethodValueImpl) symbol).setData(iNode.getData());
						} else {
							throw new GenerateException(
									"Symbol not of type method: Symbol " + child.getData()
											+ " Type " + child.getType()
											+ "! Template " + getTemplate()
											+ ", line " + getLine());
						}
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