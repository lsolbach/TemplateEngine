/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;
import java.util.List;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.MethodValueImpl;

public class IdentifierNodeImpl extends AbstractAstNode {

	public IdentifierNodeImpl() {
		this(null);
	}

	public IdentifierNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.IDENTIFIER);
	}

	public Value generateValue() {

		Value symbol = lookupSymbol(getData());
		if(symbol != null) {
			// evaluate indexed access (a[b])
			symbol = lookup(symbol);
			return symbol;
		}
		// lookup method too, if no symbol is found, because maybe a method parameter is required
		List<MethodNode> methodNodeList = getMethodRegistry().get(getData());
		if(methodNodeList != null) {
			return new MethodValueImpl(getData());
		}
		throw new GenerateException("Variable " + getData()
				+ " not found! Template " + getTemplate() + ", line "
				+ getLine());
	}

	/**
	 * 
	 * @param symbol
	 * @return
	 */
	Value lookup(Value symbol) {
		Value aSymbol = symbol;
		AstNode child = null;
		try {
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext() && aSymbol != null) {
				child = it.next();
				// TODO implement direct lookup without use of deref
				aSymbol = derefSymbol(aSymbol, child.generateValue()
						.evaluate());
				if (aSymbol == null) {
					// System.out.println("Warning: Lookup for " +
					// child.getData() + " failed");
				}
			}
		} catch (GenerateException e) {
			System.out.println(e.getMessage());
		}

		return aSymbol;
	}

	/**
	 * @param symbolTable
	 * @param name
	 * @return AbstractSymbol
	 */
	Value derefSymbol(Value symbol, String name) {
		Value aSymbol = null;
		if (symbol == null) {
			aSymbol = lookupSymbol(name);
		} else if (symbol instanceof MapValue) {
			aSymbol = ((MapValue) symbol).getData().getSymbol(name);
		} else if (symbol instanceof ListValue) {
			if (isNumeric(name)) {
				// TODO necessary?
				// Get entry by index
				List<Value> list = ((ListValue) symbol).getData();
				int i = Integer.parseInt(roundResult(name));
				if (list.size() > i) {
					aSymbol = list.get(i);
				}
			}
		}

		return aSymbol;
	}

}
