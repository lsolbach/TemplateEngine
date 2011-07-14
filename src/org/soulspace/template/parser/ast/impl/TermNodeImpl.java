/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.StringValueImpl;

public class TermNodeImpl extends AbstractAstNode {

	private ValueType valueType = ValueType.STRING;

	/**
	 * 
	 */
	public TermNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public TermNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.TERM);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		// normal term generation produces a string for template output
		valueType = getValueTypeFromParent();
		return generateSymbol(environment, valueType);
	}

	public Value generateSymbol(Environment parentEnvironment, ValueType returnType) {
		setEnvironment(new EnvironmentImpl(parentEnvironment));
		this.valueType = returnType;
		Value result = null;
		StringBuffer sb = new StringBuffer(128);
		Iterator<AstNode> it = getChildNodes().iterator();

		while (it.hasNext()) {
			AstNode child = it.next();
			try {
				result = child.generateValue(getEnvironment());
				if (result != null) {
					if (returnType.equals(ValueType.STRING)
							&& (result.getType().equals(ValueType.STRING) || result
									.getType().equals(ValueType.NUMERIC))) {
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

		if (returnType.equals(ValueType.STRING)) {
			return new StringValueImpl(sb.toString());
		} else if (returnType.equals(ValueType.NUMERIC)) {
			return asNumeric(result);
		} else {
			return result;
		}

	}

	ValueType getValueType() {
		return valueType;
	}
	
	ValueType getValueTypeFromParent() {
		ValueType parentValueType = ValueType.STRING;
		AstNode parentNode = getParent();
		
		while(!(parentNode instanceof TermNodeImpl || parentNode instanceof RootNodeImpl)) {
			parentNode = parentNode.getParent();
		}
		if(parentNode instanceof TermNodeImpl) {
			parentValueType = ((TermNodeImpl) parentNode).getValueType();
		}
		return parentValueType;
	}
}
