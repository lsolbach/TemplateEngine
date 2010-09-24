package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.method.IMethod;
import org.soulspace.template.method.IMethodRegistry;
import org.soulspace.template.method.impl.StaticMethodRegistryImpl;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;

public class TypeMethodCallNode extends AbstractAstNode {

	// FIXME make configurable
	private static IMethodRegistry methodRegistry = new StaticMethodRegistryImpl();

	public TypeMethodCallNode() {
		super();
		setType(AstNodeType.TYPE_METHOD_CALL);
	}

	public IValue generateSymbol() {
		IAstNode typeNode = getChild(0);
		IValue symbol = getSymbol(typeNode);
		if (symbol == null) {
			throw new GenerateException("Type method call " + getData()
					+ "(). Variable " + typeNode.getData()
					+ " is not initialized! Template " + getTemplate()
					+ ", line " + getLine());
		}

		IMethod method = methodRegistry.lookup(getData());
		if (method == null) {
			throw new GenerateException("Method " + getData()
					+ " not found! Template " + getTemplate() + ", line "
					+ getLine());
		}
		List<IValue> args = new ArrayList<IValue>();
		args.add(symbol);

		if (getChildCount() == 2) {
			IAstNode argListNode = getChild(1);
			for (int i = 0; i < argListNode.getChildCount(); i++) {
				IValue s = argListNode.getChild(i).generateSymbol();
				args.add(s);
			}
		}

		IValue result = method.evaluate(args);
		return result;
	}
}
