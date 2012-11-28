/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;
import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.MethodNode;
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

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		Value symbol = lookupSymbol(getData());
		if(symbol != null) {
			// evaluate indexed access (a[b])
			if(getChildNodes().size() > 0) {
				symbol = deref(symbol);
			}
			return symbol;
		}
		// lookup method too, if no symbol is found, because maybe a method parameter is required
		List<MethodNode> methodNodeList = getMethodRegistry().get(getData());
		if(methodNodeList != null) {
			return new MethodValueImpl(getData());
		}
		System.out.println(getEnvironment().printEnvironment());
		throw new GenerateException("Variable " + getData()
				+ " not found! Template " + getTemplate() + ", line "
				+ getLine());
	}

	protected Value deref(Environment environment, Value symbol) {
		setEnvironment(environment);
		return deref(symbol);
	}

	protected Value deref(Value symbol) {
		Value aSymbol = symbol;
		AstNode child = null;
		try {
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext() && aSymbol != null) {
				child = it.next();
				String ref = child.generateValue(getEnvironment()).evaluate();
				aSymbol = derefSymbol(aSymbol, ref);
			}
		} catch (GenerateException e) {
			System.out.println(e.getMessage());
		}

		return aSymbol;
	}

}