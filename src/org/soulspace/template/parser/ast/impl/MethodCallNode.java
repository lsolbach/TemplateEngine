package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IMethodNode;
import org.soulspace.template.parser.ast.ISignature;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.SymbolTable;

public class MethodCallNode extends AbstractAstNode {

	public MethodCallNode() {
		super();
		setType(AstNodeType.METHOD_CALL);
	}

	public String getSignatureString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getData());
		sb.append("(");
		// TODO add argument types
		IAstNode argList = getChild(0);
		sb.append(argList.getChildCount());
		sb.append(")");
		return sb.toString();
	}

	public ISignature getSignature() {

		return null;
	}

	public String getMethodName() {
		return getData();
	}

	public IValue generateSymbol() {
		IMethodNode methodNode;

		if (getMethodName().equals("super")) {
			IAstNode node = getParent();
			boolean found = false;
			while (!found) {
				if (node != null) {
					if (node instanceof MethodNode) {
						MethodNode mNode = (MethodNode) node;
						// System.out.println("method " +
						// mNode.getMethodName());
						if (mNode.getSuperMethod() == null) {
							throw new GenerateException(
									"super() call, but there is no super method! Template " + getTemplate() + ", line " + getLine());
						}
						return mNode.callSuperMethod();
					}
					node = node.getParent();
				} else {
					throw new GenerateException(
							"super() call outside a method body! Template " + getTemplate() + ", line " + getLine());
				}
			}
		} else {
			// TODO enhance signature lookup to consult parameter types too, not only parameter count
			if ((methodNode = getMethodNode(getSignatureString())) != null) {
				// create symbol table for arguments
				ISymbolTable symbolTable = createSymbolTable(methodNode);

				// generate from the method
				return ((MethodNode) methodNode).generateSymbol(methodNode,
						symbolTable);
			} else {
				throw new GenerateException(
						"No method node found for signature "
								+ getSignatureString() + "! Template " + getTemplate() + ", line " + getLine());
			}
		}
		return new StringValue("");
	}

	/**
	 * 
	 * @param node
	 * @return
	 * @throws GenerateException
	 */
	ISymbolTable createSymbolTable(IAstNode node) throws GenerateException {
		ISymbolTable symbolTable = new SymbolTable();

		if (!(node instanceof MethodNode)) {
			throw new GenerateException("Wrong node type " + node.getType() + "! Template " + getTemplate() + ", line " + getLine());
		}

		IAstNode paramList = node.getChild(0);
		IAstNode argList = getChild(0);
		IAstNode paramNode;
		IAstNode argNode;
		String paramName;
		String paramType;
		String argData;

		if (paramList == null) {
			throw new GenerateException("Missing parameter list! Template " + getTemplate() + ", line " + getLine());
		}

		if (!(paramList instanceof ParamListNode)) {
			// check if possible and feasible
			return symbolTable;
		}

		if (paramList.getChildCount() != argList.getChildCount()) {
			throw new GenerateException("Wrong argument count in method "
					+ getData() + "! Template " + getTemplate() + ", line " + getLine());
		}

		for (int i = 0; i < paramList.getChildCount(); i++) {
			paramNode = paramList.getChild(i);
			paramType = paramNode.getData();
			paramName = paramNode.getChild(0).getData();
			argNode = argList.getChild(i);
			argData = argNode.getData();

			IValue symbol = null;
			symbolTable.addSymbol(paramName, argNode.generateSymbol());
		}

		return symbolTable;
	}

	void addSymbol(ISymbolTable symbolTable, String data) {

	}

}
