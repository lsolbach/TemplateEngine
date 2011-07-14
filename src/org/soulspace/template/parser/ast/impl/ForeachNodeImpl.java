/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.StringValueImpl;

public class ForeachNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public ForeachNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public ForeachNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.FOREACH);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		StringBuilder sb = new StringBuilder(128);
		Environment newEnv = new EnvironmentImpl(environment);

		List<Value> list;

		if (getChildNodes().size() < 3 || getChildNodes().size() > 4) {
			throw new GenerateException("Syntax error in foreach! Template "
					+ getTemplate() + ", line " + getLine());
		}

		IdentifierNodeImpl id = (IdentifierNodeImpl) getChild(0);
		String elName = id.getData();

		if (lookupSymbolInBlock(id.getData()) != null) {
			throw new GenerateException("Symbol already exists in foreach "
					+ id.getData() + " loop! Template " + getTemplate()
					+ ", line " + getLine());
		}

		AstNode loopNode = null;
		AstNode filterNode = null;
		AstNode stmtNode = null;
		if (getChildNodes().size() == 4) {
			// with filter
			filterNode = getChild(1);
			loopNode = getChild(2);
			stmtNode = getChild(3);
		} else {
			// without filter
			loopNode = getChild(1);
			stmtNode = getChild(2);
		}

		// lookup loop variable
		Value symbol = loopNode.generateValue(getEnvironment());
		if (symbol == null) {
			// Missing Variable
			throw new GenerateException("Variable " + loopNode.getData()
					+ " in foreach " + id.getData()
					+ " loop is not initialized! Template " + getTemplate()
					+ ", line " + getLine());
		}

		// Check type of the variable
		if (!(symbol.getType() == ValueType.LIST)) {
			// Variable not of type LIST
			throw new GenerateException(
					"Expecting variable of type list in foreach "
							+ id.getData() + " loop! Template " + getTemplate()
							+ ", line " + getLine());
		}
		ListValue lSymbol = (ListValue) symbol;
		// Get List to iterate over
		list = lSymbol.getData();

		for (int i = 0; i < list.size(); i++) {
			// Set iteration variables
			lSymbol.setIndex(i);
			lSymbol.setEntry(list.get(i));
			newEnv.addValue(elName, list.get(i));

			// execute block
			if (filterNode != null) {
				// filter present
				Value exSymbol = null;
				exSymbol = filterNode.generateValue(newEnv);
				if (exSymbol != null && exSymbol.isTrue()) {
					sb.append(stmtNode.generateValue(newEnv).evaluate());
				}
			} else {
				sb.append(stmtNode.generateValue(newEnv).evaluate());
			}
		}

		// Clear 'ENTRY' reference
		lSymbol.setEntry(null);

		return new StringValueImpl(sb.toString());
	}
}
